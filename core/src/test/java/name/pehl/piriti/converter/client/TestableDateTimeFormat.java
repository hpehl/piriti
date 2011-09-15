package name.pehl.piriti.converter.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gwt.i18n.shared.DateTimeFormat;

public class TestableDateTimeFormat extends DateTimeFormat
{
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZ";
    private SimpleDateFormat simpleDateFormat;


    protected TestableDateTimeFormat()
    {
        this(DEFAULT_FORMAT);
    }


    protected TestableDateTimeFormat(String format)
    {
        super("", null);
        if (format == null)
        {
            this.simpleDateFormat = new SimpleDateFormat(DEFAULT_FORMAT);
        }
        else
        {
            this.simpleDateFormat = new SimpleDateFormat(format);
        }
    }


    @Override
    public String format(Date date)
    {
        return simpleDateFormat.format(date);
    }


    @Override
    public Date parse(String text) throws IllegalArgumentException
    {
        try
        {
            return simpleDateFormat.parse(text);
        }
        catch (ParseException e)
        {
            return null;
        }
    }
}
