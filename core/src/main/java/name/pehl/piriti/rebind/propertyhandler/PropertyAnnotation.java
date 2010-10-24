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
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (property == null ? 0 : property.hashCode());
        return result;
    }


    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        PropertyAnnotation<T> other = (PropertyAnnotation<T>) obj;
        if (property == null)
        {
            if (other.property != null)
            {
                return false;
            }
        }
        else if (!property.equals(other.property))
        {
            return false;
        }
        return true;
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
