package name.pehl.piriti.client.xml;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to select an identifier fo a POJO. The annotation can only be
 * specified for one property in the POJO. The properties type must be string.
 * In most cases the id will be mapped to an ID attribute in the XML. That's why
 * the default value of the annotation is "{@code @}id".
 * 
 * @see XmlIdRef
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 82 $
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface XmlId
{
    /**
     * The properties name. Only needed if the property cannot be annotated
     * direclty and this annotation is used inside {@link XmlMappings}.
     */
    String property() default "";


    /**
     * An XPath expression to select the XML Id. Defaults to "@id".
     * 
     * @return
     */
    String value() default "@id";


    /**
     * If <code>true</code> white spaces and new lines are stripped from the
     * selected XPath value. Defaults to <code>true</code>.
     * 
     * @return
     */
    boolean stripWsnl() default true;
}
