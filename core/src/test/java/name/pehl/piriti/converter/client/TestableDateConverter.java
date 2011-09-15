package name.pehl.piriti.converter.client;

import com.google.gwt.i18n.shared.DateTimeFormat;

class TestableDateConverter extends DateConverter
{
    @Override
    protected DateTimeFormat dateTimeFormat()
    {
        return new TestableDateTimeFormat(format);
    }
}
