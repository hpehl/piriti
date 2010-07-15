package name.pehl.piriti.client.xml;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface XmlFields
{
    String NO_ID = "This is a special no-id-marker. Do not use for real IDs!";


    XmlId id() default @XmlId(NO_ID);


    XmlField[] value();


    XmlIdRef[] references() default {};
}
