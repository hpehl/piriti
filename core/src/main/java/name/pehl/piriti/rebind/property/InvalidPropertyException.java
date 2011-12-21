package name.pehl.piriti.rebind.property;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
@SuppressWarnings("serial")
public class InvalidPropertyException extends Exception
{
    /**
     * Construct a new instance of this class
     */
    InvalidPropertyException()
    {
        super();
    }


    /**
     * Construct a new instance of this class
     * 
     * @param message
     * @param cause
     */
    InvalidPropertyException(String message, Throwable cause)
    {
        super(message, cause);
    }


    /**
     * Construct a new instance of this class
     * 
     * @param message
     */
    InvalidPropertyException(String message)
    {
        super(message);
    }


    /**
     * Construct a new instance of this class
     * 
     * @param cause
     */
    InvalidPropertyException(Throwable cause)
    {
        super(cause);
    }
}
