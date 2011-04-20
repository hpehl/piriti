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
    // -------------------------------------------------------- private members

    protected String converterVariable;


    // ----------------------------------------------------------- constructors

    public AbstractPropertyHandler(TreeLogger logger)
    {
        super(logger);
    }


    // ---------------------------------------------------- overwritten methods

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
        converterVariable = propertyContext.getVariableNames().newVariableName("Converter");
        if (propertyContext.useCustomConverter())
        {
            writer.write("Converter<%1$s> %2$s = GWT.create(%3$s.class);", propertyContext.getType()
                    .getQualifiedSourceName(), converterVariable, propertyContext.getConverter().getName());
        }
        else
        {
            writer.write("Converter<%1$s> %2$s = converterRegistry.get(%1$s.class);", propertyContext.getType()
                    .getQualifiedSourceName(), converterVariable);
        }
    }


    // ---------------------------------------------------------- read property

    /**
     * If the native flag is set,
     * {@link #readInputNatively(IndentedWriter, PropertyContext)} is called.
     * Otherwise {@link #readInputAsString(IndentedWriter, PropertyContext)} is
     * called. If a converter was registered for the property the string value
     * is converted to the properties type.
     * 
     * @param writer
     * @param propertyContext
     * @param propertyHandlerLookup
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#readInput(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.PropertyContext,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry)
     */
    @Override
    public void readInput(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerLookup propertyHandlerLookup) throws UnableToCompleteException
    {
        if (propertyContext.isNative())
        {
            readInputNatively(writer, propertyContext);
        }
        else
        {
            // First try to read the input as string and convert using a
            // specified or registered converter.
            readInputAsString(writer, propertyContext);
            writer.write("if (%s != null && %s != null) {", propertyContext.getVariableNames()
                    .getValueAsStringVariable(), converterVariable);
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
            // Second try to read the input directly
            readInputDirectly(writer, propertyContext);
            writer.outdent();
            writer.write("}");
        }
    }


    /**
     * Responsible to read the property as string and assigning it to
     * {@link PropertyContext#getValueAsStringVariable()}.
     * <p>
     * Empty implementation
     * 
     * @param writer
     * @param propertyContext
     */
    protected void readInputAsString(IndentedWriter writer, PropertyContext propertyContext)
    {
    }


    /**
     * Responsible to read the property direclty from the input without
     * conversion.
     * <p>
     * Empty implementation
     * 
     * @param writer
     * @param propertyContext
     */
    protected void readInputDirectly(IndentedWriter writer, PropertyContext propertyContext)
    {
    }


    /**
     * Responsible to assign the property to the native JSON / XML instance in
     * case the property was marked with {@code @}Native.
     * <p>
     * Empty implementation
     * 
     * @param writer
     * @param propertyContext
     */
    protected void readInputNatively(IndentedWriter writer, PropertyContext propertyContext)
    {
    }


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
     * Empty implementation
     * 
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#markupStart(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.PropertyContext)
     */
    @Override
    public void markupStart(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
    }


    /**
     * If conversion is supported a custom or registered converter is used to
     * convert the property to string.
     * <p>
     * If conversion is not supported {@link String#valueOf(Object))} is used.
     * 
     * @param writer
     * @param propertyContext
     * @param propertyHandlerLookup
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#writeValue(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.PropertyContext,
     *      name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry)
     */
    @Override
    public void writeValue(IndentedWriter writer, PropertyContext propertyContext,
            PropertyHandlerLookup propertyHandlerLookup) throws UnableToCompleteException
    {
        if (propertyContext.useCustomConverter())
        {
            writer.write("if (%s != null) {", converterVariable);
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
            writeValueAsString(writer, propertyContext);
            writer.outdent();
            writer.write("}");
            writer.write("else {");
            writer.indent();
            if (propertyContext.getType().isPrimitive() == null)
            {
                // if null, append default value
                writer.write("if (%s == null) {", propertyContext.getVariableNames().getValueVariable());
                writer.indent();
                writer.write("%s.append(\"%s\");", propertyContext.getVariableNames().getBuilderVariable(),
                        defaultValue());
                writer.outdent();
                writer.write("}");
                writer.write("else {");
                writer.indent();
                writeValueDirectly(writer, propertyContext);
                writer.outdent();
                writer.write("}");
            }
            else
            {
                writeValueDirectly(writer, propertyContext);
            }
            writer.outdent();
            writer.write("}");
        }
        else
        {
            if (propertyContext.getType().isPrimitive() == null)
            {
                // if null, append default value
                writer.write("if (%s == null) {", propertyContext.getVariableNames().getValueVariable());
                writer.indent();
                writer.write("%s.append(\"%s\");", propertyContext.getVariableNames().getBuilderVariable(),
                        defaultValue());
                writer.outdent();
                writer.write("}");
                writer.write("else {");
                writer.indent();
                writeValueDirectly(writer, propertyContext);
                writer.outdent();
                writer.write("}");
            }
            else
            {
                writeValueDirectly(writer, propertyContext);
            }
        }
    }


    /**
     * Responsible to write the property as string.
     * <p>
     * Empty implementation
     * 
     * @param writer
     * @param propertyContext
     */
    protected void writeValueAsString(IndentedWriter writer, PropertyContext propertyContext)
    {
    }


    /**
     * Responsible to write the property directly (i.e. as the properties type).
     * <p>
     * Empty implementation
     * 
     * @param writer
     * @param propertyContext
     */
    protected void writeValueDirectly(IndentedWriter writer, PropertyContext propertyContext)
    {
    }


    protected String defaultValue()
    {
        return "null";
    }


    /**
     * Empty implementation
     * 
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#markupEnd(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.PropertyContext)
     */
    @Override
    public void markupEnd(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
    }


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
