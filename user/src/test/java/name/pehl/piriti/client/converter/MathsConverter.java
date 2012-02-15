package name.pehl.piriti.client.converter;

import static name.pehl.piriti.client.converter.Maths.MANY;
import static name.pehl.piriti.client.converter.Maths.ONE;
import static name.pehl.piriti.client.converter.Maths.THREE;
import static name.pehl.piriti.client.converter.Maths.TWO;
import name.pehl.piriti.converter.client.AbstractConverter;

public class MathsConverter extends AbstractConverter<Maths>
{
    @Override
    public Maths convert(String value)
    {
        Maths result = null;
        if (isValid(value))
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
    public String serialize(Maths value)
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
