package name.pehl.piriti.client.gwttest.backandforth;

import static name.pehl.piriti.client.gwttest.backandforth.Maths.*;
import name.pehl.piriti.client.converter.Converter;

public class MathsConverter implements Converter<Maths>
{
    @Override
    public Maths convert(String value, String format)
    {
        Maths result = null;
        if (value != null && value.length() != 0)
        {
            try
            {
                int i = Integer.parseInt(value);
                switch (i)
                {
                    case 1:
                        result = ONE;
                        break;
                    case 2:
                        result = TWO;
                        break;
                    case 3:
                        result = THREE;
                        break;
                    default:
                        result = MANY;
                        break;
                }
            }
            catch (NumberFormatException e)
            {
            }
        }
        return result;
    }


    @Override
    public String serialize(Maths value, String format)
    {
        String result = null;
        if (value != null)
        {
            switch (value)
            {
                case ONE:
                    result = "1";
                    break;
                case TWO:
                    result = "2";
                    break;
                case THREE:
                    result = "3";
                    break;
                default:
                    result = "42";
                    break;
            }
        }
        return result;
    }
}
