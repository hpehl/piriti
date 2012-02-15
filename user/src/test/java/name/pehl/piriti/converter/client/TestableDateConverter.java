package name.pehl.piriti.converter.client;

import com.google.gwt.i18n.shared.DateTimeFormat;

class TestableDateConverter extends DateConverter
{

    public TestableDateConverter()
    {
        super();
    }


    public TestableDateConverter(String format)
    {
        super(format);
    }


    @Override
    protected DateTimeFormat dateTimeFormat(String format)
    {
        return new TestableDateTimeFormat(format);
    }
}
