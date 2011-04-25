package name.pehl.piriti.rebind;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;

import org.apache.commons.lang3.StringEscapeUtils;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JConstructor;
import com.google.gwt.core.ext.typeinfo.JParameter;

/**
 * Contains utility method for the code generation
 * 
 * @author $Author$
 * @version $Date$ $Revision: 527
 *          $
 */
public final class CodeGeneration
{
    // -------------------------------------------------------------- constants

    protected static Map<String, String> interfaceToImplementation = new HashMap<String, String>();
    static
    {
        interfaceToImplementation.put(Collection.class.getName(), ArrayList.class.getName());
        interfaceToImplementation.put(List.class.getName(), ArrayList.class.getName());
        interfaceToImplementation.put(Set.class.getName(), HashSet.class.getName());
        interfaceToImplementation.put(SortedSet.class.getName(), TreeSet.class.getName());
    }


    /**
     * Private constructor to ensure that the class acts as a true utility class
     * i.e. it isn't instantiable and extensible.
     */
    private CodeGeneration()
    {
    }


    // ----------------------------------------------------------- misc methods

    public static String collectionImplementationFor(String classname)
    {
        String impl = interfaceToImplementation.get(classname);
        if (impl != null)
        {
            return impl;
        }
        // It's assumed that classname is already an implementation!
        return classname;
    }


    public static void log(IndentedWriter writer, Level level, String message, Object... params)
    {
        String logMessage = StringEscapeUtils.escapeJava(String.format(message, params));
        writer.write("if (logger.isLoggable(%s)) {", level);
        writer.indent();
        writer.write("logger.log(%s, \"%s\");", level, logMessage);
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
                    "new %1$s(); // if there are any reader / writer definitions in %1$s, this ensures they are registered",
                    type.getParameterizedQualifiedSourceName());
        }
    }
}
