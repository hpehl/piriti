package name.pehl.piriti.rebind.propertyhandler;

import java.lang.annotation.Annotation;

import com.google.gwt.core.ext.typeinfo.JType;

/**
 * Helper class for one annotated property.
 * 
 * @author $Author$
 * @version $Date$ $Revision: 728
 *          $
 */
public class PropertyAnnotation<T extends Annotation> implements Comparable<PropertyAnnotation<T>>
{
    private final String property;
    private final JType type;
    private final T annotation;
    private final int order;


    public PropertyAnnotation(String property, JType type, T annotation, int order)
    {
        this.property = property;
        this.type = type;
        this.annotation = annotation;
        this.order = order;
    }


    @Override
    public int compareTo(PropertyAnnotation<T> o)
    {
        return order - o.order;
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
