package name.pehl.piriti.client.xml;

/**
 * Contains static utility methods for XML parsing / serialization.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public final class XmlUtils
{
    // @formatter:off
    @SuppressWarnings("unused")
    private static final String[][] ESCAPE = {
        {"\"", "&quot;"}, // " - double-quote
        {"&", "&amp;"},   // & - ampersand
        {"<", "&lt;"},    // < - less-than
        {">", "&gt;"},    // > - greater-than
        {"'", "&apos;"}, // XML apostrophe
    };
    // @formatter:on

    private XmlUtils()
    {
    }
}
