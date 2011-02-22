package name.pehl.piriti.client.gwttest.converter;

import name.pehl.piriti.client.converter.Converter;

/**
 * @author $Author$
 * @version $Date$ $Revision$
 */
public class NameConverter implements Converter<String>
{
    public final static String DE_EN = "de-en";
    public final static String EN_DE = "en-de";
    public final static String GERMAN = "Max Mustermann";
    public final static String ENGLISH = "John Doe";


    @Override
    public String convert(String value, String format)
    {
        return translate(value, format);
    }


    @Override
    public String serialize(String value, String format)
    {
        return translate(value, format);
    }


    private String translate(String value, String format)
    {
        String result = null;
        if (DE_EN.equals(format))
        {
            if (GERMAN.equals(value))
            {
                result = ENGLISH;
            }
        }
        else if (EN_DE.equals(format))
        {
            if (ENGLISH.equals(value))
            {
                result = GERMAN;
            }
        }
        return result;
    }
}
