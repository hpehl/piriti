package name.pehl.piriti.client.xml;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to reference one or more POJOs in the XML data. The value of the
 * annotation must select one or several ids. The field type must be a POJO with
 * a registered {@link XmlReader} or an array or collection of such a POJO.
 * 
 * @see XmlId
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 82 $
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface XmlIdRef
{
    /**
     * An XPath expression to select the XML IdRef. Defaults to "" which means
     * that the fields name is taken as a base for the XPath expression.
     * 
     * @return
     */
    String value() default "";
}
