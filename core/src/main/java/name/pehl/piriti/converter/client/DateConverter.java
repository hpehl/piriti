package name.pehl.piriti.converter.client;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;

/**
 * Converter for dates. Uses {@code DateTimeFormat#parse(String)} for the
 * conversion. If no format is given {@linkplain PredefinedFormat#ISO_8601
 * ISO_8601} is used.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 7 $
 */
public class DateConverter extends AbstractDateConverter<Date>
{
    @Override
    protected Date convertWithoutFormat(String value)
    {
        return parseInternal(value);
    }


    @Override
    protected Date newInstance(Date parsed)
    {
        return parsed;
    }
}
