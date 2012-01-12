package name.pehl.piriti.converter.client;

/**
 * Base class for all converters which uses a format for conversion.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 6 $
 */
public abstract class FormatableConverter<T> extends AbstractConverter<T>
{
    private final String format;


    public FormatableConverter(String format)
    {
        this.format = format;
    }


    public String getFormat()
    {
        return format;
    }
}
