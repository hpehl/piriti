package name.pehl.gwt.piriti.client.xml;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author $Author:$
 * @version $Revision:$
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface XmlField
{
    /**
     * An xpath expression
     * 
     * @return
     */
    String value() default "";


    /**
     * The format for the value (e.g. a date or number format)
     * 
     * @return
     */
    String format() default "";
}
