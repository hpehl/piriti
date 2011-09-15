package name.pehl.piriti.converter.client;

import com.google.gwt.i18n.shared.DateTimeFormat;

class TestableTimestampConverter extends TimestampConverter
{
    @Override
    protected DateTimeFormat dateTimeFormat()
    {
        return new TestableDateTimeFormat(format);
    }
}
