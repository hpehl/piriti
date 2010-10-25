package name.pehl.piriti.rebind;

import name.pehl.piriti.client.property.NoopPropertyGetter;
import name.pehl.piriti.client.property.NoopPropertySetter;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.VariableNames;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JConstructor;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameter;
import com.google.gwt.core.ext.typeinfo.JType;

/**
 * Contains utility method for the code generation
 * 
 * @author $Author$
 * @version $Date$ $Revision: 527
 *          $
 */
public final class CodeGeneration
{
    /**
     * Private constructor to ensure that the class acts as a true utility class
     * i.e. it isn't instantiable and extensible.
     */
    private CodeGeneration()
    {
    }


    // ----------------------------------------------------- assignment methods

    /**
     * Writes the assignment based on {@link PropertyContext#getPropertyStyle()}
     * . The assignement is only done if some value was read from JSON / XML.
     * 
     * @param writer
     * @param propertyContext
     */
    public static void assign(IndentedWriter writer, PropertyContext propertyContext)
    {
        writer.write("if (%s != null) {", propertyContext.getVariableNames().getValueVariable());
        writer.indent();
        if (propertyContext.getSetter() == NoopPropertySetter.class)
        {
            if (propertyContext.isGxt())
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


    private static void assignFieldOrSetter(IndentedWriter writer, PropertyContext propertyContext)
    {
        boolean samePackage = propertyContext.getReaderOrWriter().getPackage() == propertyContext.getClazz()
                .getPackage();
        JField field = null;
        if (samePackage)
        {
            field = TypeUtils.findField(propertyContext.getClazz(), propertyContext.getName(), Modifier.DEFAULT,
                    Modifier.PROTECTED, Modifier.PUBLIC);
        }
        else
        {
            field = TypeUtils.findField(propertyContext.getClazz(), propertyContext.getName(), Modifier.PUBLIC);
        }
        if (field != null && !field.isFinal())
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
                setter = TypeUtils.findSetter(propertyContext.getClazz(), propertyContext.getName(), parameter,
                        Modifier.DEFAULT, Modifier.PROTECTED, Modifier.PUBLIC);
            }
            else
            {
                setter = TypeUtils.findSetter(propertyContext.getClazz(), propertyContext.getName(), parameter,
                        Modifier.PUBLIC);
            }
            if (setter != null)
            {
                writer.write("model.%s(%s);", setter.getName(), propertyContext.getVariableNames().getValueVariable());
            }
            else
            {
                String reason = String.format("Cannot assign %s: No accessible field or setter found in %s.",
                        propertyContext.getName(), propertyContext.getClazz().getQualifiedSourceName());
                skipProperty(writer, propertyContext, reason);
            }
        }
    }


    private static void assignGxt(IndentedWriter writer, PropertyContext propertyContext)
    {
        writer.write("model.set(\"%s\", %s);", propertyContext.getName(), propertyContext.getVariableNames()
                .getValueVariable());
    }


    // ------------------------------------------------------------- read field

    public static void readField(IndentedWriter writer, PropertyContext propertyContext)
    {
        if (propertyContext.getGetter() == NoopPropertyGetter.class)
        {
            if (propertyContext.isGxt())
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


    private static void readFieldOrSetter(IndentedWriter writer, PropertyContext propertyContext)
    {
        boolean samePackage = propertyContext.getReaderOrWriter().getPackage() == propertyContext.getClazz()
                .getPackage();
        JField field = null;
        if (samePackage)
        {
            field = TypeUtils.findField(propertyContext.getClazz(), propertyContext.getName(), Modifier.DEFAULT,
                    Modifier.PROTECTED, Modifier.PUBLIC);
        }
        else
        {
            field = TypeUtils.findField(propertyContext.getClazz(), propertyContext.getName(), Modifier.PUBLIC);
        }
        if (field != null)
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
                getter = TypeUtils.findGetter(propertyContext.getClazz(), propertyContext.getName(), returnType,
                        Modifier.DEFAULT, Modifier.PROTECTED, Modifier.PUBLIC);
            }
            else
            {
                getter = TypeUtils.findGetter(propertyContext.getClazz(), propertyContext.getName(), returnType,
                        Modifier.PUBLIC);
            }
            if (getter != null)
            {
                writer.write("%s = model.%s();", propertyContext.getVariableNames().getValueVariable(),
                        getter.getName());
            }
            else
            {
                String reason = String.format("Cannot read %s: No accessible field or setter found in %s.",
                        propertyContext.getName(), propertyContext.getClazz().getQualifiedSourceName());
                skipProperty(writer, propertyContext, reason);
            }
        }
    }


    private static void readGxt(IndentedWriter writer, PropertyContext propertyContext)
    {
        writer.write("%s = model.get(\"%s\");", propertyContext.getVariableNames().getValueVariable(),
                propertyContext.getName());
    }


    // ----------------------------------------------------------- misc methods

    public static void idRef(IndentedWriter writer, JClassType type)
    {
        writer.write("public %s idRef(String id) {", type.getQualifiedSourceName());
        writer.indent();
        writer.write("return this.idMap.get(id);");
        writer.outdent();
        writer.write("}");
    }


    /**
     * To ensure all necessary reader and writer are registered, this little
     * helper method genereates a new instance of the specified type (only if
     * the type provides a noarg constructor).
     * 
     * <pre>
     * new &lt;specified type&gt;();
     * </pre>
     * 
     * @param writer
     * @param type
     */
    public static void readerWriterInitialization(IndentedWriter writer, JClassType type)
    {
        boolean noargConstructor = false;
        JConstructor[] constructors = type.getConstructors();
        if (constructors != null)
        {
            for (JConstructor constructor : constructors)
            {
                JParameter[] parameters = constructor.getParameters();
                if (parameters == null || parameters.length == 0)
                {
                    noargConstructor = true;
                    break;
                }
            }
        }
        else
        {
            noargConstructor = true;
        }
        if (noargConstructor)
        {
            writer.write(
                    "new %1$s(); // if there are any reader definitions in %1$s, this ensures they are registered",
                    type.getParameterizedQualifiedSourceName());
        }
    }


    /**
     * Generates code comments if a property was skipped (contains the reason
     * why the field was skipped)
     * 
     * @param writer
     * @param propertyContext
     * @param reason
     */
    public static void skipProperty(IndentedWriter writer, PropertyContext propertyContext, String reason)
    {
        writer.write("// Skipping property %s", propertyContext);
        writer.write("// " + reason);
        GWT.log("Skipping property " + propertyContext, null);
        GWT.log(reason, null);
    }


    // ----------------------------------------------------------- json methods

    /**
     * Appends the properties name in quotes and the colon to the StringBuilder
     * holding the JSON serialization data.
     * 
     * @param writer
     * @param propertyContext
     */
    public static void appendJsonKey(IndentedWriter writer, PropertyContext propertyContext)
    {
        writer.write("%s.append(\"\\\"%s\\\":\");", propertyContext.getVariableNames().getBuilderVariable(),
                propertyContext.getPath());
    }


    /**
     * Appends {@link VariableNames#getValueVariable()} to the StringBuilder
     * holding the JSON serialization data. Use this method only for non-null
     * values of {@link VariableNames#getValueVariable()}.
     * 
     * @param writer
     * @param propertyContext
     * @param quote
     *            Whether to quote the value.
     */
    public static void appendJsonValue(IndentedWriter writer, PropertyContext propertyContext, boolean quote)
    {
        if (quote)
        {
            writer.write("%s.append(\"\\\"\");", propertyContext.getVariableNames().getBuilderVariable());
        }
        writer.write("%s.append(%s);", propertyContext.getVariableNames().getBuilderVariable(), propertyContext
                .getVariableNames().getValueVariable());
        if (quote)
        {
            writer.write("%s.append(\"\\\"\");", propertyContext.getVariableNames().getBuilderVariable());
        }
    }
}
