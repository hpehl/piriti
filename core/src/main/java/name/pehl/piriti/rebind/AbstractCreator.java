package name.pehl.piriti.rebind;

import java.io.PrintWriter;
import java.util.Iterator;

import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * Base class for creating deferred binding implementations. This class contains
 * some common code and behaviour.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 136 $
 */
public abstract class AbstractCreator extends LogFacade
{
    // -------------------------------------------------------- private members

    protected final GeneratorContext generatorContext;
    protected final VariableNames variableNames;
    protected final TypeContext typeContext;
    protected final TypeProcessor typeProcessor;
    protected final PropertyHandlerRegistry propertyHandlerRegistry;
    protected final String implName;


    // ----------------------------------------------------------- constructors

    public AbstractCreator(GeneratorContext generatorContext, JClassType rwType, String implName, String rwClassname,
            TreeLogger logger) throws UnableToCompleteException
    {
        super(logger);

        // Check for possible misuse:
        // GWT.create(XmlReader.class)
        JClassType rwInterface = generatorContext.getTypeOracle().findType(rwClassname);
        if (rwInterface.equals(rwType))
        {
            die("You must use a subtype of {0} in GWT.create(). E.g.,\n"
                    + "  interface ModelReader extends {0}<Model> {}\n  GWT.create(ModelReader.class);");
        }

        // Check for right interface:
        // interface FooReader extends JsonReader<Foo> {}
        JClassType[] rwTypes = rwType.getImplementedInterfaces();
        if (rwTypes.length == 0)
        {
            die("No implemented interfaces for %s", rwType.getName());
        }

        // Look for the type parameter
        JClassType type = null;
        for (JClassType t : rwTypes)
        {
            if (t.getQualifiedSourceName().equals(rwInterface.getQualifiedSourceName()))
            {
                JClassType[] typeArgs = t.isParameterized().getTypeArgs();
                if (typeArgs.length != 1)
                {
                    die("One type parameter is required for %s", t.getName());
                }
                type = typeArgs[0];
                break;
            }
        }
        if (type == null)
        {
            die("No type parameter found in %s", (Object[]) rwTypes);
        }

        // initialize
        this.generatorContext = generatorContext;
        this.variableNames = setupVariableNames();
        this.typeContext = new TypeContext(generatorContext.getTypeOracle(), type, rwType, variableNames, logger);
        this.typeProcessor = setupTypeProcessor();
        this.propertyHandlerRegistry = setupPropertyHandlerRegistry();
        this.implName = implName;

        // collect properties, id and references
        this.typeProcessor.process(typeContext);
    }


    /**
     * Method to setup the {@link VariableNames} used in this creator,
     * 
     * @return the {@link VariableNames} used in this creator,
     */
    protected VariableNames setupVariableNames()
    {
        return new VariableNames("value", "input", "builder");
    }


    /**
     * Method to setup the {@link TypeProcessor} used in this creator,
     * 
     * @return an instane of {@link DefaultTypeProcessor}
     */
    protected TypeProcessor setupTypeProcessor()
    {
        TypeProcessor pojoTypeProcessor = new PojoTypeProcessor(logger);
        TypeProcessor rwTypeProcessor = new RwTypeProcessor(logger);
        pojoTypeProcessor.setNext(rwTypeProcessor);
        return pojoTypeProcessor;
    }


    /**
     * Method to setup the {@link PropertyHandlerRegistry} used in this creator.
     * 
     * @return the {@link PropertyHandlerRegistry} used in this creator.
     */
    protected abstract PropertyHandlerRegistry setupPropertyHandlerRegistry();


    // --------------------------------------------------------- create methods

    /**
     * Creates the code for the reader / writer implementation. Therefore the
     * following methods are called:
     * <ol>
     * <li>{@link #createPackage(IndentedWriter)}
     * <li>{@link #createImports(IndentedWriter)}
     * <li>{@link #createClass(IndentedWriter)}
     * </ol>
     * 
     * @throws UnableToCompleteException
     */
    public void create() throws UnableToCompleteException
    {
        PrintWriter printWriter = generatorContext.tryCreate(logger, typeContext.getRwType().getPackage().getName(),
                implName);
        if (printWriter != null)
        {
            IndentedWriter writer = new IndentedWriter(printWriter);

            createPackage(writer);
            writer.newline();

            createImports(writer);
            writer.newline();

            createClass(writer);
            writer.newline();

            generatorContext.commit(logger, printWriter);
        }
    }


    /**
     * Creates the package declaration.
     * 
     * @param writer
     * @throws UnableToCompleteException
     */
    protected void createPackage(IndentedWriter writer) throws UnableToCompleteException
    {
        String packageName = typeContext.getRwType().getPackage().getName();
        if (packageName.length() > 0)
        {
            writer.write("package %s;", packageName);
        }
    }


    /**
     * Adds some common import statements.
     * 
     * @param writer
     * @throws UnableToCompleteException
     */
    protected void createImports(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("import java.util.ArrayList;");
        writer.write("import java.util.Iterator;");
        writer.write("import java.util.HashMap;");
        writer.write("import java.util.List;");
        writer.write("import java.util.Map;");
        writer.write("import java.util.Set;");
        writer.write("import static java.util.logging.Level.*;");
        writer.write("import java.util.logging.Logger;");
        writer.write("import com.google.gwt.core.client.GWT;");
        writer.write("import name.pehl.piriti.converter.client.*;");
    }


    /**
     * Creates the reader / writer class. Therefore the following methods are
     * called:
     * <ol>
     * <li>{@link #createMemberVariables(IndentedWriter)}
     * <li>{@link #createConstructor(IndentedWriter)}
     * <li>{@link #createMethods(IndentedWriter)}
     * </ol>
     * 
     * @param writer
     * @throws UnableToCompleteException
     */
    protected void createClass(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public class %s implements %s {", implName, typeContext.getRwType().getQualifiedSourceName());
        writer.indent();

        writer.write("private static Logger logger = Logger.getLogger(\"%s\");", typeContext.getRwType()
                .getQualifiedSourceName());
        writer.newline();

        createMemberVariables(writer);
        writer.newline();

        createConstructor(writer);
        writer.newline();

        createMethods(writer);
        writer.newline();

        writer.outdent();
        writer.write("}");
    }


    /**
     * Declares the member variables. The following code is generated by this
     * method:
     * 
     * <pre>
     * private ConverterRegistry converterRegistry;
     * private Map&lt;String, T&gt; idMap;
     * </pre>
     * 
     * @param writer
     */
    protected void createMemberVariables(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("private ConverterRegistry converterRegistry;");
        writer.write("private Map<String,%s> idMap;", typeContext.getType().getQualifiedSourceName());
    }


    /**
     * Creates a public no-arg constructor. The body of the constructor is
     * created by {@link #createConstructorBody(IndentedWriter)}.
     * 
     * @param writer
     * @throws UnableToCompleteException
     */
    protected void createConstructor(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("public %s() {", implName);
        writer.indent();
        createConstructorBody(writer);
        writer.outdent();
        writer.write("}");
    }


    /**
     * Creates the constructor body. The following code is generated by this
     * method:
     * 
     * <pre>
     * this.converterRegistry = ConverterGinjector.INJECTOR.getConverterRegistry();
     * this.idMap = new HashMap&lt;String, T&gt;();
     * </pre>
     * 
     * @param writer
     */
    protected void createConstructorBody(IndentedWriter writer)
    {
        writer.write("this.converterRegistry = ConverterGinjector.INJECTOR.getConverterRegistry();");
        writer.write("this.idMap = new HashMap<String,%s>();", typeContext.getType().getQualifiedSourceName());
    }


    /**
     * Subclasses must implement this method to create the actual code.
     * 
     * @param writer
     * @throws UnableToCompleteException
     */
    protected abstract void createMethods(IndentedWriter writer) throws UnableToCompleteException;


    // ---------------------------------------------- type / properties methods

    /**
     * Iterates over {@link TypeContext#getProperties()}. For each
     * {@link PropertyContext} a {@link PropertyHandler} is searched in the
     * {@link PropertyHandlerRegistry}. If found and
     * {@link PropertyHandler#isValid(IndentedWriter, PropertyContext)} ==
     * <code>true</code>,
     * {@link #handleProperty(IndentedWriter, PropertyHandler, PropertyContext, boolean)}
     * is called.
     * 
     * @param writer
     * @throws UnableToCompleteException
     */
    protected void handleProperties(IndentedWriter writer) throws UnableToCompleteException
    {
        for (Iterator<PropertyContext> iter = typeContext.getProperties().iterator(); iter.hasNext();)
        {
            PropertyContext propertyContext = iter.next();
            debug("Processing PropertyContext %s", propertyContext);
            PropertyHandler propertyHandler = propertyHandlerRegistry.findPropertyHandler(propertyContext);
            if (propertyHandler != null && propertyHandler.isValid(writer, propertyContext))
            {
                writer.newline();
                handleProperty(writer, propertyHandler, propertyContext, iter.hasNext());
            }
        }
    }


    /**
     * Concrete creators must implement this method to handle one property.
     * 
     * @param writer
     * @param propertyHandler
     * @param propertyContext
     * @param hasNext
     * @throws UnableToCompleteException
     */
    protected abstract void handleProperty(IndentedWriter writer, PropertyHandler propertyHandler,
            PropertyContext propertyContext, boolean hasNext) throws UnableToCompleteException;
}
