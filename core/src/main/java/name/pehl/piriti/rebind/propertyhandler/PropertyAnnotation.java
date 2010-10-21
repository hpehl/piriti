package name.pehl.piriti.rebind.propertyhandler;

import java.lang.annotation.Annotation;

import com.google.gwt.core.ext.typeinfo.JType;

/**
 * Helper class for one annotated property, which is either a field or a setter.
 * 
 * @author $Author$
 * @version $Date$ $Revision: 728
 *          $
 */
public class PropertyAnnotation<T extends Annotation>
{
    private final String property;
    private final JType type;
    private final T annotation;


    public PropertyAnnotation(String property, JType type, T annotation)
    {
        this.property = property;
        this.type = type;
        this.annotation = annotation;
    }


    public String getProperty()
    {
        return property;
    }


    public JType getType()
    {
        return type;
    }


    public T getAnnotation()
    {
        return annotation;
    }
}
