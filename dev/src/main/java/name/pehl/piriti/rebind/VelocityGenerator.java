package name.pehl.piriti.rebind;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import name.pehl.piriti.rebind.property.PropertyContext;
import name.pehl.piriti.rebind.type.TypeContext;
import name.pehl.piriti.rebind.type.TypeProcessor;
import name.pehl.piriti.rebind.type.TypeUtils;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Abstract Generator which delegates to a {@link VelocityCreator}. The
 * {@link VelocityCreator} is responsible for generating the code.
 *
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 745 $
 */
public abstract class VelocityGenerator extends Generator
{
    // -------------------------------------------------------- private members

    private JClassType rwType;
    private JClassType type;


    // --------------------------------------------------------- public methods

    @Override
    public String generate(TreeLogger treeLogger, GeneratorContext generatorContext, String rwInterface)
            throws UnableToCompleteException
    {
        TypeOracle typeOracle = generatorContext.getTypeOracle();
        assert typeOracle != null;
        validateTypes(new Logger(treeLogger), typeOracle, rwInterface);

        String implName = rwType.getName().replace('.', '_') + "Impl";
        String packageName = rwType.getPackage().getName();
        PrintWriter printWriter = generatorContext.tryCreate(treeLogger, rwType.getPackage().getName(), implName);
        if (printWriter != null)
        {
            Injector injector = Guice.createInjector(new RebindModule(treeLogger, generatorContext));

            // collect properties, id and references
            TypeProcessor typeProcessor = injector.getInstance(TypeProcessor.class);
            TypeContext typeContext = new TypeContext(typeOracle, type, rwType);
            List<JClassType> paramTypes = computeTypeChain(type);
            typeProcessor.process(typeContext);

            // setup velocity context
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("TypeUtils", TypeUtils.class);
            velocityContext.put("typeContext", typeContext);
            velocityContext.put("typeOracle", typeOracle);
            velocityContext.put("implName", implName);
            velocityContext.put("paramTypes", paramTypes);
            velocityContext.put("concreteTypesMap", getConcreteTypes(typeOracle, typeContext));
            populateVelocityContext(velocityContext, typeContext, typeOracle);

            // merge template
            VelocityEngine velocityEngine = injector.getInstance(VelocityEngine.class);
            velocityEngine.mergeTemplate(getTemplateName(), "UTF-8", velocityContext, printWriter);
            generatorContext.commit(treeLogger, printWriter);
        }
        return packageName + "." + implName;
    }

    private Map<String, Set<String>> getConcreteTypes(TypeOracle typeOracle, TypeContext typeContext)
    {
        Map<String, Set<String>> propertiesConcreteTypes = new HashMap<String, Set<String>>();
        for (PropertyContext property : typeContext.getProperties())
        {
            Set<String> concreteTypes = new HashSet<String>();
            for (JClassType type : property.getConcreteTypes())
            {
                String concreteType = TypeUtils.getReaderOrWriterImplQualifiedName(typeOracle, type,
                        getInterfaceName());
                if (concreteType != null)
                {
                    concreteTypes.add(concreteType);
                }
            }
            propertiesConcreteTypes.put(property.getPathOrName(), concreteTypes);
        }

        return propertiesConcreteTypes;
    }

    private List<JClassType> computeTypeChain(JClassType type) {
        List<JClassType> typesChain = null;
        JParameterizedType parameterizedType = type.isParameterized();
        if (parameterizedType != null) {
            typesChain = new ArrayList<JClassType>();
            assert parameterizedType.getTypeArgs().length == 1;
            computeTypeChain(parameterizedType.getTypeArgs()[0], typesChain);
        }

        return typesChain;
    }

    private void computeTypeChain(JClassType jClassType, List<JClassType> typesChain) {
        typesChain.add(jClassType);

        JParameterizedType parameterizedType = jClassType.isParameterized();
        if (parameterizedType != null) {
            assert parameterizedType.getTypeArgs().length == 1;
            computeTypeChain(parameterizedType.getTypeArgs()[0], typesChain);
        }
    }


    /**
     * Gives subclasses a chance to put extra information into the velocity
     * context. This method is empty. The veloity context passed as paramter
     * already contains the following data:
     * <ul>
     * <li>TypeUtils.class
     * <li>An {@link TypeContext} instance for the requested reader / writer and
     * relevant model
     * <li>The name of the implementation class which should be generated
     * </ul>
     *
     * @param velocityContext
     * @param typeContext
     * @param typeOracle
     */
    protected void populateVelocityContext(VelocityContext velocityContext, TypeContext typeContext, TypeOracle typeOracle)
    {
    }


    // --------------------------------------------------------- helper methods

    private void validateTypes(Logger logger, TypeOracle typeOracle, String rwInterface)
            throws UnableToCompleteException
    {
        // interfaceType is the reader / writer interface from Piriti, thus
        // either JsonReader<T>, JsonWriter<T>, XmlReader<T> or XmlWriter<T>.
        // rwInterface is the user defined interface which extends interfaceType
        JClassType interfaceType = typeOracle.findType(getInterfaceName());
        if (interfaceType == null)
        {
            logger.die("Unable to find metadata for type " + getInterfaceName());
            return; // to satisfy the null pointer checks of the IDE
        }
        rwType = typeOracle.findType(rwInterface);
        if (rwType == null)
        {
            logger.die("Unable to find metadata for type " + rwInterface);
        }

        // Check for possible misuse: GWT.create(XmlReader.class)
        if (interfaceType == rwType)
        {
            logger.die(
                    "You must use a subtype of %1$s in GWT.create(). E.g.,\n"
                            + "  interface ModelReader extends %1$s<Model> {}\n  ModelReader reader = GWT.create(ModelReader.class);",
                    interfaceType.getSimpleSourceName());
        }

        // Check for right interface: interface FooReader extends
        // JsonReader<Foo> {}
        JClassType[] implementedInterfaces = rwType.getImplementedInterfaces();
        if (implementedInterfaces.length == 0)
        {
            logger.die("No implemented interfaces for %s", rwType.getSimpleSourceName());
        }

        type = findImplementedType(logger, interfaceType, implementedInterfaces);

        if (type == null)
        {
            logger.die("No type parameter found in %s", (Object[]) implementedInterfaces);
        }
    }

    private JClassType findImplementedType(Logger logger, JClassType interfaceType, JClassType[] implementedInterfaces)
            throws UnableToCompleteException
    {
        JClassType type = null;
        for (JClassType t : implementedInterfaces)
        {
            if (t.getImplementedInterfaces().length > 0)
            {
                type = findImplementedType(logger, interfaceType, t.getImplementedInterfaces());
                if (type != null)
                    break;
            }

            if (t.getQualifiedSourceName().equals(interfaceType.getQualifiedSourceName()))
            {
                JClassType[] typeArgs = t.isParameterized().getTypeArgs();
                if (typeArgs.length != 1)
                {
                    logger.die("One type parameter is required for %s", t.getName());
                }
                type = typeArgs[0];
                break;
            }
        }

        return type;
    }


    // ------------------------------------------------------- abstract methods

    /**
     * Must return Piritis interface name this generator should generate an
     * implementation for. Thus either {@code JsonReader<T>},
     * {@code JsonWriter<T>}, {@code XmlReader<T>} or {@code XmlWriter<T>}
     *
     * @return the interface this generator should generate an implementation
     *         for.
     */
    protected abstract String getInterfaceName();


    /**
     * Returns the velocity template which should be merged.
     *
     * @return the velocity template which should be merged.
     */
    protected abstract String getTemplateName();
}
