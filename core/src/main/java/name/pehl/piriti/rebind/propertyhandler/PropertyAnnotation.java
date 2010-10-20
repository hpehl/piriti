package name.pehl.piriti.rebind.propertyhandler;

import java.lang.annotation.Annotation;

import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JMethod;

/**
 * @author $Author$
 * @version $Date$ $Revision: 728
 *          $
 */
public class PropertyAnnotation<T extends Annotation>
{
    final private JField field;
    final private JMethod setter;
    final private T annotation;
    final private AssignmentPolicy assignmentPolicy;


    public PropertyAnnotation(JField field, T annotation, AssignmentPolicy assignmentPolicy)
    {
        super();
        this.field = field;
        this.setter = null;
        this.annotation = annotation;
        this.assignmentPolicy = assignmentPolicy;
    }


    public PropertyAnnotation(JMethod setter, T annotation, AssignmentPolicy assignmentPolicy)
    {
        super();
        this.field = null;
        this.setter = setter;
        this.annotation = annotation;
        this.assignmentPolicy = assignmentPolicy;
    }


    public JField getField()
    {
        return field;
    }


    public JMethod getSetter()
    {
        return setter;
    }


    public T getAnnotation()
    {
        return annotation;
    }


    public AssignmentPolicy getAssignmentPolicy()
    {
        return assignmentPolicy;
    }
}
