package name.pehl.piriti.converter.client;

import com.google.gwt.i18n.shared.DateTimeFormat;

class TestableDateConverter extends DateConverter
{

    public TestableDateConverter()
    {
        this(new TestableDateTimeFormat());
    }


    public TestableDateConverter(String format)
    {
        super(format);
    }


    public TestableDateConverter(DateTimeFormat dateTimeFormat)
    {
        super(dateTimeFormat);
    }


    @Override
    protected DateTimeFormat dateTimeFormat(String format)
    {
        return new TestableDateTimeFormat(format);
    }
}
