package name.pehl.piriti.rebind;

import java.util.Iterator;

import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.xml.propertyhandler.ArrayPropertyHandler;
import name.pehl.piriti.rebind.xml.propertyhandler.CollectionPropertyHandler;
import name.pehl.piriti.rebind.xml.propertyhandler.XmlRegistryPropertyHandler;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

public abstract class AbstractReaderCreator extends AbstractCreator
{
    public AbstractReaderCreator(GeneratorContext generatorContext, JClassType rwType, String implName,
            String rwClassname, TreeLogger logger) throws UnableToCompleteException
    {
        super(generatorContext, rwType, implName, rwClassname, logger);
    }


    @Override
    protected void createImports(IndentedWriter writer) throws UnableToCompleteException
    {
        super.createImports(writer);
        writer.write("import name.pehl.piriti.commons.client.InstanceContextHolder;");
    }


    @Override
    protected void createMethods(IndentedWriter writer) throws UnableToCompleteException
    {
        createReaderMethods(writer);

        readIds(writer);
        writer.newline();

        readProperties(writer);
        writer.newline();

        readIdRefs(writer);
        writer.newline();
    }


    protected abstract void createReaderMethods(IndentedWriter writer) throws UnableToCompleteException;


    protected abstract void readList(IndentedWriter writer) throws UnableToCompleteException;


    protected abstract void readSingle(IndentedWriter writer) throws UnableToCompleteException;


    protected void readIds(IndentedWriter writer) throws UnableToCompleteException
    {
        boolean validIdField = false;
        PropertyHandler handler = null;
        PropertyContext idContext = typeContext.getId();
        if (idContext != null)
        {
            handler = propertyHandlerLookup.lookup(idContext);
            validIdField = handler != null && handler.isValid(writer, idContext);
        }

        writer.write("private %s readIds(%s %s) {", typeContext.getType().getParameterizedQualifiedSourceName(),
                typeContext.getVariableNames().getInputType(), typeContext.getVariableNames().getInputVariable());
        writer.indent();
        writer.write("if (%s != null) {", typeContext.getVariableNames().getInputVariable());
        writer.indent();
        if (validIdField)
        {
            handler.log(writer, idContext);
            handler.declare(writer, idContext);
            handler.readInput(writer, idContext, propertyHandlerLookup);
            writer.write("%s %s = this.idRef(%s);", typeContext.getType().getParameterizedQualifiedSourceName(),
                    typeContext.getVariableNames().getInstanceVariable(), idContext.getVariableNames()
                            .getValueVariable());
            writer.write("if (%s == null) {", typeContext.getVariableNames().getInstanceVariable());
            writer.indent();
            newInstance(writer);
            handler.assign(writer, idContext);
            writer.outdent();
            writer.write("}");
        }
        else
        {
            newInstance(writer);
        }

        // handle IDs in nested models
        for (Iterator<PropertyContext> iter = typeContext.getProperties().iterator(); iter.hasNext();)
        {
            PropertyContext propertyContext = iter.next();
            PropertyHandler propertyHandler = propertyHandlerLookup.lookup(propertyContext);
            if ((propertyHandler instanceof XmlRegistryPropertyHandler
                    || propertyHandler instanceof ArrayPropertyHandler || propertyHandler instanceof CollectionPropertyHandler)
                    && propertyHandler.isValid(writer, propertyContext))
            {
                writer.newline();
                handleProperty(writer, propertyHandler, propertyContext, iter.hasNext());
            }
        }

        writer.write("return %s;", typeContext.getVariableNames().getInstanceVariable());
        writer.outdent();
        writer.write("}");
        writer.write("return null;");
        writer.outdent();
        writer.write("}");
    }


    protected void readProperties(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("private %1$s readProperties(%2$s %3$s, %1$s %4$s) {", typeContext.getType()
                .getParameterizedQualifiedSourceName(), typeContext.getVariableNames().getInputType(), typeContext
                .getVariableNames().getInputVariable(), typeContext.getVariableNames().getInstanceVariable());
        writer.indent();
        writer.write("if (%s != null) {", typeContext.getVariableNames().getInputVariable());
        writer.indent();

        handleProperties(writer);

        writer.outdent();
        writer.write("}");
        writer.write("return %s;", typeContext.getVariableNames().getInstanceVariable());
        writer.outdent();
        writer.write("}");
    }


    protected void readIdRefs(IndentedWriter writer) throws UnableToCompleteException
    {
        writer.write("private %1$s readIdRefs(%2$s %3$s, %1$s %4$s) {", typeContext.getType()
                .getParameterizedQualifiedSourceName(), typeContext.getVariableNames().getInputType(), typeContext
                .getVariableNames().getInputVariable(), typeContext.getVariableNames().getInstanceVariable());
        writer.indent();
        writer.write("if (%s != null) {", typeContext.getVariableNames().getInputVariable());
        writer.indent();

        handleIdRefs(writer);

        writer.outdent();
        writer.write("}");
        writer.write("return %s;", typeContext.getVariableNames().getInstanceVariable());
        writer.outdent();
        writer.write("}");
    }


    @Override
    protected void handleProperty(IndentedWriter writer, PropertyHandler propertyHandler,
            PropertyContext propertyContext, boolean hasNext) throws UnableToCompleteException
    {
        propertyHandler.log(writer, propertyContext);
        propertyHandler.declare(writer, propertyContext);
        propertyHandler.readInput(writer, propertyContext, propertyHandlerLookup);
        propertyHandler.assign(writer, propertyContext);
    }
}
