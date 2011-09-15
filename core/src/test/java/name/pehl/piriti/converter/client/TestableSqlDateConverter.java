package name.pehl.piriti.converter.client;

import com.google.gwt.i18n.shared.DateTimeFormat;

class TestableSqlDateConverter extends SqlDateConverter
{
    @Override
    protected DateTimeFormat dateTimeFormat()
    {
        return new TestableDateTimeFormat(format);
    }
}
