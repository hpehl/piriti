/**
 * Created on Sep 4, 2006
 */
package name.pehl.piriti.client.sarissa;

/**
 * An exception for XML parsing errors
 * 
 * @author Eric Bessette <ebessette@gmail.com>
 */
public class XMLParseException extends Exception
{
    /**
     * The serial version uid
     */
    private static final long serialVersionUID = -1683644957218722406L;


    /**
     * Creates a new XML Parse Exception
     */
    public XMLParseException()
    {
        super();
    }


    /**
     * Creates a new XML Parse Exception
     * 
     * @param msg
     *            The message for this exception
     */
    public XMLParseException(String msg)
    {
        super(msg);
    }


    /**
     * Create a new XML Parse Exception
     * 
     * @param msg
     *            The message for this exception
     * @return A new parse exception
     */
    public static XMLParseException create(String msg)
    {
        return new XMLParseException(msg);
    }


    /**
     * Creates a new XML Parse Exception
     * 
     * @param msg
     *            The message for this exception
     * @param cause
     *            The child cause for this exception
     */
    public XMLParseException(String msg, Throwable cause)
    {
        super(msg, cause);
    }


    /**
     * Creates a new XML Parse Exception
     * 
     * @param cause
     *            The child cause for this exception
     */
    public XMLParseException(Throwable cause)
    {
        super(cause);
    }
}
