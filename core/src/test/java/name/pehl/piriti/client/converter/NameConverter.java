package name.pehl.piriti.client.converter;

import name.pehl.piriti.converter.client.AbstractConverter;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2011-02-22 22:55:05 +0100 (Di, 22. Feb 2011) $ $Revision:
 *          1454 $
 */
public class NameConverter extends AbstractConverter<String>
{
    public final static String DE_EN = "de-en";
    public final static String EN_DE = "en-de";
    public final static String GERMAN = "Max Mustermann";
    public final static String ENGLISH = "John Doe";


    @Override
    public String convert(String value)
    {
        return translate(value);
    }


    @Override
    public String serialize(String value)
    {
        return translate(value);
    }


    private String translate(String value)
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
