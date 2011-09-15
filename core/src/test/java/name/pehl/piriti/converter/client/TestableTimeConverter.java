package name.pehl.piriti.converter.client;

import com.google.gwt.i18n.shared.DateTimeFormat;

class TestableTimeConverter extends TimeConverter
{
    @Override
    protected DateTimeFormat dateTimeFormat()
    {
        return new TestableDateTimeFormat(format);
    }
}
