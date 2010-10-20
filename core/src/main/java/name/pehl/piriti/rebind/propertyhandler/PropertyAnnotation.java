package name.pehl.piriti.rebind.propertyhandler;

import java.lang.annotation.Annotation;

import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JMethod;

/**
 * Helper class for one annotated property, which is either a field or a setter.
 * 
 * @author $Author$
 * @version $Date$ $Revision: 728
 *          $
 */
public class PropertyAnnotation<T extends Annotation>
{
    final private JField field;
    final private JMethod setter;
    final private T annotation;


    public PropertyAnnotation(JField field, T annotation)
    {
        super();
        this.field = field;
        this.setter = null;
        this.annotation = annotation;
    }


    public PropertyAnnotation(JMethod setter, T annotation)
    {
        super();
        this.field = null;
        this.setter = setter;
        this.annotation = annotation;
    }


    public boolean isFieldAnnotated()
    {
        return field != null;
    }


    public JField getField()
    {
        return field;
    }


    public JMethod getSetter()
    {
        return setter;
    }


    public boolean isSetterAnnotated()
    {
        return setter != null;
    }


    public T getAnnotation()
    {
        return annotation;
    }
}
