package name.pehl.piriti.rebind;

import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

public abstract class AbstractWriterCreator extends AbstractCreator
{
    public AbstractWriterCreator(GeneratorContext generatorContext, JClassType rwType, String implName,
            String rwClassname, TreeLogger logger) throws UnableToCompleteException
    {
        super(generatorContext, rwType, implName, rwClassname, logger);
        // TODO Auto-generated constructor stub
    }


    @Override
    protected void createMethods(IndentedWriter writer) throws UnableToCompleteException
    {
        createWriterMethods(writer);
        writer.newline();
    }


    protected abstract void createWriterMethods(IndentedWriter writer) throws UnableToCompleteException;


    @Override
    protected void handleProperty(IndentedWriter writer, PropertyHandler propertyHandler,
            PropertyContext propertyContext, boolean hasNext) throws UnableToCompleteException
    {
        propertyHandler.log(writer, propertyContext);
        propertyHandler.declare(writer, propertyContext);
        propertyHandler.readProperty(writer, propertyContext);
        propertyHandler.markupStart(writer, propertyContext);
        propertyHandler.writeValue(writer, propertyContext, propertyHandlerLookup);
        propertyHandler.markupEnd(writer, propertyContext);
        if (hasNext)
        {
            writeSeperator(writer, propertyContext);
        }
    }


    protected void writeSeperator(IndentedWriter writer, PropertyContext propertyContext)
    {
    }
}
