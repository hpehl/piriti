package name.pehl.piriti.rebind.propertyhandler;

import java.util.logging.Level;

import name.pehl.piriti.rebind.CodeGeneration;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.LogFacade;
import name.pehl.piriti.rebind.Modifier;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.TypeUtils;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JType;

/**
 * Abstract base class for {@linkplain PropertyHandler}s with default
 * implementations and common code.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public abstract class AbstractPropertyHandler extends LogFacade implements PropertyHandler
{
    // ----------------------------------------------------------- constructors

    public AbstractPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    // ---------------------------------------------------- overwritten methods

    /**
     * Most handlers will support conversion, so this method returns always
     * <code>true</code>. Please overwrite to disable converters.
     * 
     * @param writer
     * @param propertyContext
     * @return <code>true</code>
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#supportsConversion(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.PropertyContext)
     */
    @Override
    public boolean supportsConversion(IndentedWriter writer, PropertyContext propertyContext)
            throws UnableToCompleteException
    {
        return true;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void log(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        CodeGeneration.log(writer, Level.FINE, "Processing %s: %s", getClass().getSimpleName(), propertyContext);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void declare(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("%s %s = null;", propertyContext.getType().getParameterizedQualifiedSourceName(), propertyContext
                .getVariableNames().getValueVariable());
    }


    // ---------------------------------------------------------- read property

    /**
     * If conversion is supported
     * {@link #readInputAsString(IndentedWriter, PropertyContext)} is called to
     * read the property as string. Then a custom or registered converter is
     * used to convert the string to the proerties type.
     * <p>
     * If conversion is not supported
     * {@link #readInputDirectly(IndentedWriter, PropertyContext)} is called.
     * 
     * @param writer
     * @param propertyContext
     * @param propertyHandlerRegistry
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#readInput(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.PropertyContext,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry)
     */
    @Override
    public void readInput(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerRegistry propertyHandlerRegistry) throws UnableToCompleteException
    {
        if (supportsConversion(writer, propertyContext))
        {
            CodeGeneration.log(writer, Level.FINE, "Conversion is supported for %s. Try to use configured converter.",
                    propertyContext);
            readInputAsString(writer, propertyContext);
            writer.write("if (%s != null) {", propertyContext.getVariableNames().getValueAsStringVariable());
            writer.indent();
            String converterVariable = propertyContext.getVariableNames().newVariableName("FromStringConverter");
            if (propertyContext.useCustomConverter())
            {
                writer.write("Converter<%1$s> %2$s = GWT.create(%3$s.class);", propertyContext.getType()
                        .getQualifiedSourceName(), converterVariable, propertyContext.getConverter().getName());
                CodeGeneration.log(writer, Level.FINE, "Using custom converter %s for %s", propertyContext
                        .getConverter().getName(), propertyContext);
            }
            else
            {
                writer.write("Converter<%1$s> %2$s = converterRegistry.get(%1$s.class);", propertyContext.getType()
                        .getQualifiedSourceName(), converterVariable);
                CodeGeneration.log(writer, Level.FINE, "Using converter registry for %s", propertyContext);
            }
            writer.write("if (%s != null) {", converterVariable);
            writer.indent();
            if (propertyContext.getFormat() != null)
            {
                writer.write("%s = %s.convert(%s, \"%s\");", propertyContext.getVariableNames().getValueVariable(),
                        converterVariable, propertyContext.getVariableNames().getValueAsStringVariable(),
                        propertyContext.getFormat());
            }
            else
            {
                writer.write("%s = %s.convert(%s, null);", propertyContext.getVariableNames().getValueVariable(),
                        converterVariable, propertyContext.getVariableNames().getValueAsStringVariable());
            }
            writer.outdent();
            writer.write("}");
            writer.write("else {");
            writer.indent();
            CodeGeneration.log(writer, Level.SEVERE, "No conversion found for %s!. Property cannot be mapped.",
                    propertyContext);
            writer.outdent();
            writer.write("}");
            writer.outdent();
            writer.write("}");
        }
        else
        {
            CodeGeneration.log(writer, Level.FINE,
                    "Conversion is not supported for %s. Try to direclty read from input.", propertyContext);
            readInputDirectly(writer, propertyContext);
        }
    }


    /**
     * Responsible to read the property as string and assigning it to
     * {@link PropertyContext#getValueAsStringVariable()}.
     * 
     * @param writer
     * @param propertyContext
     */
    protected abstract void readInputAsString(IndentedWriter writer, PropertyContext propertyContext);


    /**
     * Responsible to read the property direclty from the input without
     * conversion.
     * 
     * @param writer
     * @param propertyContext
     */
    protected abstract void readInputDirectly(IndentedWriter writer, PropertyContext propertyContext);


    // ----------------------------------------------------------------- assign

    /**
     * {@inheritDoc}
     * <p>
     * The assignement is only done if
     * {@link PropertyContext#getValueVariable()} is not null.
     */
    @Override
    public void assign(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        writer.write("if (%s != null) {", propertyContext.getVariableNames().getValueVariable());
        writer.indent();
        if (propertyContext.getSetter() == null)
        {
            if (propertyContext.getTypeContext().isGxt())
            {
                assignGxt(writer, propertyContext);
            }
            else
            {
                assignFieldOrSetter(writer, propertyContext);
            }
        }
        else
        {
            writer.write("%1$s setter = GWT.create(%1$s.class);", propertyContext.getSetter().getName());
            writer.write("setter.set(model, %s);", propertyContext.getVariableNames().getValueVariable());
        }
        writer.outdent();
        writer.write("}");
    }


    protected void assignFieldOrSetter(IndentedWriter writer, PropertyContext propertyContext)
    {
        JField field = TypeUtils.findField(propertyContext.getTypeContext().getType(), propertyContext.getName());
        if (field == null)
        {
            String reason = String.format("Cannot assign %s: No field found in %s.", propertyContext.getName(),
                    propertyContext.getTypeContext().getType().getQualifiedSourceName());
            skipProperty(writer, propertyContext, reason);
        }

        JClassType enclosingType = field.getEnclosingType();
        boolean samePackage = propertyContext.getTypeContext().getRwType().getPackage() == enclosingType.getPackage();
        if (!field.isFinal() && (field.isPublic() || samePackage && (field.isDefaultAccess() || field.isProtected())))
        {
            writer.write("model.%s = %s;", propertyContext.getName(), propertyContext.getVariableNames()
                    .getValueVariable());
        }
        else
        {
            JMethod setter = null;
            JType parameter = propertyContext.isPrimitive() ? propertyContext.getPrimitiveType() : propertyContext
                    .getType();
            if (samePackage)
            {
                setter = TypeUtils.findSetter(propertyContext.getTypeContext().getType(), propertyContext.getName(),
                        parameter, Modifier.DEFAULT, Modifier.PROTECTED, Modifier.PUBLIC);
            }
            else
            {
                setter = TypeUtils.findSetter(propertyContext.getTypeContext().getType(), propertyContext.getName(),
                        parameter, Modifier.PUBLIC);
            }
            if (setter != null)
            {
                writer.write("model.%s(%s);", setter.getName(), propertyContext.getVariableNames().getValueVariable());
            }
            else
            {
                String reason = String.format("Cannot assign %s: No accessible field or setter found in %s.",
                        propertyContext.getName(), propertyContext.getTypeContext().getType().getQualifiedSourceName());
                skipProperty(writer, propertyContext, reason);
            }
        }
    }


    protected void assignGxt(IndentedWriter writer, PropertyContext propertyContext)
    {
        writer.write("model.set(\"%s\", %s);", propertyContext.getName(), propertyContext.getVariableNames()
                .getValueVariable());
    }


    // ---------------------------------------------------------- read property

    /**
     * {@inheritDoc}
     */
    @Override
    public void readProperty(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        if (propertyContext.getGetter() == null)
        {
            if (propertyContext.getTypeContext().isGxt())
            {
                readGxt(writer, propertyContext);
            }
            else
            {
                readFieldOrSetter(writer, propertyContext);
            }
        }
        else
        {
            writer.write("%1$s getter = GWT.create(%1$s.class);", propertyContext.getGetter().getName());
            writer.write("%s = getter.get(model);", propertyContext.getVariableNames().getValueVariable());
        }
    }


    protected void readFieldOrSetter(IndentedWriter writer, PropertyContext propertyContext)
    {
        JField field = TypeUtils.findField(propertyContext.getTypeContext().getType(), propertyContext.getName());
        if (field == null)
        {
            String reason = String.format("Cannot read %s: No accessible field found in %s.",
                    propertyContext.getName(), propertyContext.getTypeContext().getType().getQualifiedSourceName());
            skipProperty(writer, propertyContext, reason);
        }

        JClassType enclosingType = field.getEnclosingType();
        boolean samePackage = propertyContext.getTypeContext().getRwType().getPackage() == enclosingType.getPackage();
        if (!field.isFinal() && (field.isPublic() || samePackage && (field.isDefaultAccess() || field.isProtected())))
        {
            writer.write("%s = model.%s;", propertyContext.getVariableNames().getValueVariable(),
                    propertyContext.getName());
        }
        else
        {
            JMethod getter = null;
            JType returnType = propertyContext.isPrimitive() ? propertyContext.getPrimitiveType() : propertyContext
                    .getType();
            if (samePackage)
            {
                getter = TypeUtils.findGetter(propertyContext.getTypeContext().getType(), propertyContext.getName(),
                        returnType, Modifier.DEFAULT, Modifier.PROTECTED, Modifier.PUBLIC);
            }
            else
            {
                getter = TypeUtils.findGetter(propertyContext.getTypeContext().getType(), propertyContext.getName(),
                        returnType, Modifier.PUBLIC);
            }
            if (getter != null)
            {
                writer.write("%s = model.%s();", propertyContext.getVariableNames().getValueVariable(),
                        getter.getName());
            }
            else
            {
                String reason = String.format("Cannot read %s: No accessible field or setter found in %s.",
                        propertyContext.getName(), propertyContext.getTypeContext().getType().getQualifiedSourceName());
                skipProperty(writer, propertyContext, reason);
            }
        }
    }


    protected void readGxt(IndentedWriter writer, PropertyContext propertyContext)
    {
        writer.write("%s = model.get(\"%s\");", propertyContext.getVariableNames().getValueVariable(),
                propertyContext.getName());
    }


    /**
     * If conversion is supported a custom or registered converter is used to
     * convert the property to string.
     * <p>
     * If conversion is not supported {@link String#valueOf(Object))} is used.
     * 
     * @param writer
     * @param propertyContext
     * @param propertyHandlerRegistry
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#writeValue(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.PropertyContext,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry)
     */
    @Override
    public void writeValue(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerRegistry propertyHandlerRegistry) throws UnableToCompleteException
    {
        if (supportsConversion(writer, propertyContext))
        {
            CodeGeneration.log(writer, Level.FINE, "Conversion is supported for %s. Try to use configured converter.",
                    propertyContext);
            writer.write("String %s = null;", propertyContext.getVariableNames().getValueAsStringVariable());
            String converterVariable = propertyContext.getVariableNames().newVariableName("WriteConverter");
            if (propertyContext.useCustomConverter())
            {
                writer.write("Converter<%1$s> %2$s = GWT.create(%3$s.class);", propertyContext.getType()
                        .getQualifiedSourceName(), converterVariable, propertyContext.getConverter().getName());
                CodeGeneration.log(writer, Level.FINE, "Using custom converter %s for %s", propertyContext
                        .getConverter().getName(), propertyContext);
            }
            else
            {
                writer.write("Converter<%1$s> %2$s = converterRegistry.get(%1$s.class);", propertyContext.getType()
                        .getQualifiedSourceName(), converterVariable);
                CodeGeneration.log(writer, Level.FINE, "Using converter registry for %s", propertyContext);
            }
            writer.write("if (%s != null) {", converterVariable);
            // Use the registered converter
            writer.indent();
            if (propertyContext.getFormat() != null)
            {
                writer.write("%s = %s.serialize(%s, \"%s\");", propertyContext.getVariableNames()
                        .getValueAsStringVariable(), converterVariable, propertyContext.getVariableNames()
                        .getValueVariable(), propertyContext.getFormat());
            }
            else
            {
                writer.write("%s = %s.serialize(%s, null);", propertyContext.getVariableNames()
                        .getValueAsStringVariable(), converterVariable, propertyContext.getVariableNames()
                        .getValueVariable());
            }
            writer.outdent();
            writer.write("}");
            writer.write("else {");
            // Fall back to toString()
            writer.indent();
            CodeGeneration.log(writer, Level.WARNING, "No conversion found for %s!. Falling back to String.valueOf.",
                    propertyContext);
            writer.write("%s = String.valueOf(%s);", propertyContext.getVariableNames().getValueAsStringVariable(),
                    propertyContext.getVariableNames().getValueVariable());
            writer.outdent();
            writer.write("}");
            writeValueAsString(writer, propertyContext);
        }
        else
        {
            CodeGeneration.log(writer, Level.FINE, "Conversion is not supported for %s. Using String.valueOf().",
                    propertyContext);
            writeValueDirectly(writer, propertyContext);
        }
    }


    /**
     * Responsible to write the property as string.
     * 
     * @param writer
     * @param propertyContext
     */
    protected abstract void writeValueAsString(IndentedWriter writer, PropertyContext propertyContext);


    /**
     * Responsible to write the property directly (i.e. as the properties type).
     * 
     * @param writer
     * @param propertyContext
     */
    protected abstract void writeValueDirectly(IndentedWriter writer, PropertyContext propertyContext);


    // --------------------------------------------------------- helper methods

    /**
     * Generates code comments if a property was skipped (contains the reason
     * why the field was skipped)
     * 
     * @param writer
     * @param propertyContext
     * @param reason
     */
    protected void skipProperty(IndentedWriter writer, PropertyContext propertyContext, String reason)
    {
        warn("Skipping property %s. Reason: %s.", propertyContext, reason);
        CodeGeneration.log(writer, Level.WARNING, "Skipping property %s. Reason: %s.", propertyContext, reason);
    }
}
