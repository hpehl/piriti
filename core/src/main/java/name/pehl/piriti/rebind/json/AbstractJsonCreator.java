package name.pehl.piriti.rebind.json;

import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.rebind.AbstractCreator;
import name.pehl.piriti.rebind.IndentedWriter;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * Common creator for {@linkplain JsonReader}s and {@linkplain JsonWriter}s.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 137 $
 */
public abstract class AbstractJsonCreator extends AbstractCreator
{
    // --------------------------------------------------------- initialization

    public AbstractJsonCreator(GeneratorContext generatorContext, JClassType rwType, String implName,
            String rwClassname, TreeLogger logger) throws UnableToCompleteException
    {
        super(generatorContext, rwType, implName, rwClassname, logger);
    }


    // --------------------------------------------------------- create methods

    @Override
    protected void createImports(IndentedWriter writer) throws UnableToCompleteException
    {
        super.createImports(writer);
        writer.write("import com.google.gwt.core.client.JsonUtils;");
        writer.write("import com.google.gwt.json.client.*;");
        writer.write("import name.pehl.piriti.json.client.*;");
        writer.write("import name.pehl.totoe.json.client.*;");
    }


    @Override
    protected void createMemberVariables(IndentedWriter writer) throws UnableToCompleteException
    {
        super.createMemberVariables(writer);
        writer.write("private JsonRegistry jsonRegistry;");
    }


    @Override
    protected void createConstructorBody(IndentedWriter writer)
    {
        super.createConstructorBody(writer);
        writer.write("this.jsonRegistry = JsonGinjector.INJECTOR.getJsonRegistry();");
        writer.write("this.jsonRegistry.register(%s.class, this);", typeContext.getType().getQualifiedSourceName());
    }
}
