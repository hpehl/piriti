package name.pehl.piriti.client.xml;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to select an identifier fo a POJO. The annotation can only be
 * specified for one field in the POJO. The field type must be string. In most
 * cases the id will be mapped to an ID attribute in the XML. That's why the
 * default value of the annotation is "&#64;id".
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
     * The fields name. Only needed if the field cannot be annotated direclty
     * and this annotation is used inside {@link XmlFields}.
     */
    String name() default "";


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
