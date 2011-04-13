package name.pehl.piriti.rebind.json;

import org.apache.commons.lang.StringUtils;

/**
 * @author $Author$
 * @version $Date$ $Revision:
 *          1224 $
 */
public final class JsonUtils
{
    /**
     * JSONPath special characters.
     */
    private static final char[] JSON_PATH_SYMBOLS = new char[] {'$', '@', '.', '[', ']', '*', '#', ',', ':', '?', '(',
            ')',};


    /**
     * Private constructor to ensure that the class acts as a true utility class
     * i.e. it isn't instantiable and extensible.
     */
    private JsonUtils()
    {
        super();
    }


    /**
     * Return <code>true</code> if the path contains {@link #JSON_PATH_SYMBOLS},
     * <code>false</code> otherwise.
     * 
     * @param path
     * @return <code>true</code> if the path contains {@link #JSON_PATH_SYMBOLS}
     *         , <code>false</code> otherwise.
     */
    public static boolean isJsonPath(String path)
    {
        return StringUtils.containsAny(path, JSON_PATH_SYMBOLS);
    }
}
