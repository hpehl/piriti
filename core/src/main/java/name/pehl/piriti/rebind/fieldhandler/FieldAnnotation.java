package name.pehl.piriti.rebind.fieldhandler;

import java.lang.annotation.Annotation;

import com.google.gwt.core.ext.typeinfo.JField;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class FieldAnnotation<T extends Annotation>
{
    public JField field;
    public T annotation;


    public FieldAnnotation(JField field, T annotation)
    {
        super();
        this.field = field;
        this.annotation = annotation;
    }
}
