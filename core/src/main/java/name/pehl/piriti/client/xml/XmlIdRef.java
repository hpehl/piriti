package name.pehl.piriti.client.xml;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to reference one or more POJOs in the XML data. The value of the
 * annotation must select one or several ids. The properties type must be a POJO
 * with a registered {@link XmlReader} or an array or collection of such a
 * POJOs.
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
     * The properties name. Only needed if the property cannot be annotated
     * direclty and this annotation is used inside {@link XmlMappings}.
     */
    String property() default "";


    /**
     * An XPath expression to select the XML IdRef. Defaults to "" which means
     * that the properties name is taken as a base for the XPath expression.
     * 
     * @return
     */
    String value() default "";


    /**
     * If <code>true</code> white spaces and new lines are stripped from the
     * selected XPath value. Defaults to <code>true</code>.
     * 
     * @return
     */
    boolean stripWsnl() default true;
}
