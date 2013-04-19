package name.pehl.piriti.rebind;

import java.io.PrintWriter;

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
import com.google.inject.Guice;
import com.google.inject.Injector;

public abstract class AbstractReaderWriterGenerator extends Generator
{
    private JClassType rwType;
    private JClassType type;

    @Override
    public String generate(TreeLogger treeLogger, GeneratorContext generatorContext, String rwInterface)
            throws UnableToCompleteException
    {
        TypeOracle typeOracle = generatorContext.getTypeOracle();
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
            typeProcessor.process(typeContext);

            // setup velocity context
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("TypeUtils", TypeUtils.class);
            velocityContext.put("typeContext", typeContext);
            velocityContext.put("implName", implName);
            velocityContext.put("readerType", getReaderType(typeOracle));
            velocityContext.put("writerType", getWriterType(typeOracle));
            velocityContext.put("format", getFormat());

            // merge template
            VelocityEngine velocityEngine = injector.getInstance(VelocityEngine.class);
            velocityEngine.mergeTemplate(getTemplateName(), "UTF-8", velocityContext, printWriter);
            generatorContext.commit(treeLogger, printWriter);
        }
        return packageName + "." + implName;
    }

    protected abstract String getFormat();

    protected abstract String getInterfaceClassName();

    protected abstract Class<?> getReaderClass();

    protected abstract Class<?> getWriterClass();

    private JClassType getReaderType(TypeOracle typeOracle)
    {
        return typeOracle.findType(getReaderClass().getName());
    }

    private JClassType getWriterType(TypeOracle typeOracle)
    {
        return typeOracle.findType(getWriterClass().getName());
    }

    private String getTemplateName()
    {
        return "name/pehl/piriti/rebind/readerwriter.vm";
    }

    private void validateTypes(Logger logger, TypeOracle typeOracle, String rwInterface)
            throws UnableToCompleteException
    {
        JClassType interfaceType = typeOracle.findType(getInterfaceClassName());
        assert interfaceType != null;

        rwType = typeOracle.findType(rwInterface);
        if (rwType == null)
        {
            logger.die("Unable to find metadata for type " + rwInterface);
        }
        assert rwType != null;

        // Check for possible misuse: GWT.create(JsonReaderWriter.class)
        if (interfaceType == rwType)
        {
            logger.die(
                    "You must use a subtype of %1$s in GWT.create(). E.g.,\n"
                            + "  interface ModelSerializers extends %1$s<Model> {}\n  ModelReader reader = GWT.create(ModelSerializers.class);",
                    interfaceType.getSimpleSourceName());
        }

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
}
