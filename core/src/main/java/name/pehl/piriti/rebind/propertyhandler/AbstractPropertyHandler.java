package name.pehl.piriti.rebind.propertyhandler;

import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.LogFacade;
import name.pehl.piriti.rebind.Modifier;
import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.TypeUtils;

import com.google.gwt.core.client.GWT;
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
     * Default implementation which always returns <code>true</code>.
     * 
     * @param writer
     * @param propertyContext
     * @return <code>true</code>
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.propertyhandler.PropertyHandler#isValid(name.pehl.piriti.rebind.IndentedWriter,
     *      name.pehl.piriti.rebind.PropertyContext)
     */
    @Override
    public boolean isValid(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        return true;
    }


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
        // TODO Generate logging code
        writer.write("// %s: %s", getClass().getSimpleName(), propertyContext);
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
            readInputAsString(writer, propertyContext);
            writer.write("if (%s != null) {", propertyContext.getVariableNames().getValueAsStringVariable());
            writer.indent();
            String converterVariable = propertyContext.getVariableNames().newVariableName("FromStringConverter");
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
            writer.outdent();
            writer.write("}");
        }
        else
        {
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
     * conversion. As most handlers supports conversion this method is empty.
     * Please overwrite for a meaningful implementation.
     * 
     * @param writer
     * @param propertyContext
     */
    protected void readInputDirectly(IndentedWriter writer, PropertyContext propertyContext)
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
        writer.write("String %s = null;", propertyContext.getVariableNames().getValueAsStringVariable());
        if (supportsConversion(writer, propertyContext))
        {
            String converterVariable = propertyContext.getVariableNames().newVariableName("WriteConverter");
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
            writer.write("%s = String.valueOf(%s);", propertyContext.getVariableNames().getValueAsStringVariable(),
                    propertyContext.getVariableNames().getValueVariable());
            writer.outdent();
            writer.write("}");
        }
        else
        {
            writer.write("%s = String.valueOf(%s);", propertyContext.getVariableNames().getValueAsStringVariable(),
                    propertyContext.getVariableNames().getValueVariable());
        }
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
        // TODO Use LogFacade
        writer.write("// Skipping property %s", propertyContext);
        writer.write("// " + reason);
        GWT.log("Skipping property " + propertyContext, null);
        GWT.log(reason, null);
    }
}
