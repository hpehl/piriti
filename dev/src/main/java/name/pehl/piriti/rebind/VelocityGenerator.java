package name.pehl.piriti.rebind;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import name.pehl.piriti.rebind.type.PojoTypeProcessor;
import name.pehl.piriti.rebind.type.RwTypeProcessor;
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
import com.google.gwt.core.ext.typeinfo.TypeOracle;

/**
 * Abstract Generator which delegates to a {@link VelocityCreator}. The
 * {@link VelocityCreator} is responsible for generating the code.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 745 $
 */
public abstract class VelocityGenerator extends Generator
{
    // -------------------------------------------------------------- constants

    private static final String VELOCITY_PROPERTIES = "name/pehl/piriti/rebind/velocity.properties";

    // -------------------------------------------------------- private members

    private JClassType rwType;
    private JClassType type;


    // --------------------------------------------------------- public methods

    @Override
    public String generate(TreeLogger treeLogger, GeneratorContext context, String rwInterface)
            throws UnableToCompleteException
    {
        Logger.get().setup(treeLogger);
        GeneratorContextHolder.get().setup(context);
        validateTypes(treeLogger, context, rwInterface);

        String implName = rwType.getName().replace('.', '_') + "Impl";
        String packageName = rwType.getPackage().getName();
        PrintWriter printWriter = GeneratorContextHolder.get().getContext()
                .tryCreate(Logger.get().getTreeLogger(), rwType.getPackage().getName(), implName);
        if (printWriter != null)
        {
            // collect properties, id and references
            TypeProcessor typeProcessor = new PojoTypeProcessor();
            typeProcessor.setNext(new RwTypeProcessor());
            TypeContext typeContext = new TypeContext(type, rwType);
            typeProcessor.process(typeContext);

            // setup velocity context
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("TypeUtils", TypeUtils.class);
            velocityContext.put("typeContext", typeContext);
            velocityContext.put("implName", implName);
            populateVelocityContext(velocityContext);

            // merge template
            VelocityEngine velocityEngine = createVelocityEngine();
            velocityEngine.mergeTemplate(getTemplateName(), "UTF-8", velocityContext, printWriter);
            GeneratorContextHolder.get().getContext().commit(Logger.get().getTreeLogger(), printWriter);
        }
        return packageName + "." + implName;
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
     */
    protected void populateVelocityContext(VelocityContext velocityContext)
    {
    }


    // --------------------------------------------------------- helper methods

    private void validateTypes(TreeLogger treeLogger, GeneratorContext context, String rwInterface)
            throws UnableToCompleteException
    {
        TypeOracle typeOracle = context.getTypeOracle();
        assert (typeOracle != null);

        // interfaceType is the reader / writer interface from Piriti, thus
        // either JsonReader<T>, JsonWriter<T>, XmlReader<T> or XmlWriter<T>.
        // rwInterface is the user defined interface which extends interfaceType
        JClassType interfaceType = typeOracle.findType(getInterfaceName());
        if (interfaceType == null)
        {
            Logger.get().die("Unable to find metadata for type " + getInterfaceName());
        }
        rwType = typeOracle.findType(rwInterface);
        if (rwType == null)
        {
            Logger.get().die("Unable to find metadata for type " + rwInterface);
        }

        // Check for possible misuse: GWT.create(XmlReader.class)
        if (interfaceType == rwType)
        {
            Logger.get()
                    .die("You must use a subtype of %1$s in GWT.create(). E.g.,\n"
                            + "  interface ModelReader extends %1$s<Model> {}\n  ModelReader reader = GWT.create(ModelReader.class);",
                            interfaceType.getSimpleSourceName());
        }

        // Check for right interface: interface FooReader extends
        // JsonReader<Foo> {}
        JClassType[] implementedInterfaces = rwType.getImplementedInterfaces();
        if (implementedInterfaces.length == 0)
        {
            Logger.get().die("No implemented interfaces for %s", rwType.getSimpleSourceName());
        }

        // Check type parameter(s)
        for (JClassType t : implementedInterfaces)
        {
            if (t.getQualifiedSourceName().equals(interfaceType.getQualifiedSourceName()))
            {
                JClassType[] typeArgs = t.isParameterized().getTypeArgs();
                if (typeArgs.length != 1)
                {
                    Logger.get().die("One type parameter is required for %s", t.getName());
                }
                type = typeArgs[0];
                break;
            }
        }
        if (type == null)
        {
            Logger.get().die("No type parameter found in %s", (Object[]) implementedInterfaces);
        }
        if (type.isParameterized() != null)
        {
            Logger.get().die("Type parameters for the model are not supported!");
        }
    }


    /**
     * Creates the Velocity engine with the properties from
     * {@value #VELOCITY_PROPERTIES}
     * 
     * @return the intialized Velocity engine.
     * @throws UnableToCompleteException
     */
    private VelocityEngine createVelocityEngine() throws UnableToCompleteException
    {
        VelocityEngine engine = null;
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(VELOCITY_PROPERTIES);
        Properties properties = new Properties();
        try
        {
            properties.load(inputStream);
            engine = new VelocityEngine(properties);
        }
        catch (IOException e)
        {
            Logger.get().die("Cannot load velocity properties from " + VELOCITY_PROPERTIES);
        }
        return engine;
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
