package name.pehl.piriti.rebind.fieldhandler;

import java.lang.annotation.Annotation;

import com.google.gwt.core.ext.typeinfo.JField;

/**
 * @author $Author$
 * @version $Date$ $Revision: 728
 *          $
 */
public class FieldAnnotation<T extends Annotation>
{
    public JField field;
    public T annotation;
    public AssignmentPolicy assignmentPolicy;


    public FieldAnnotation(JField field, T annotation, AssignmentPolicy assignmentPolicy)
    {
        super();
        this.field = field;
        this.annotation = annotation;
        this.assignmentPolicy = assignmentPolicy;
    }
}
