package name.pehl.piriti.client.json;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO Javadoc
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 82 $
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JsonId
{
    /**
     * An path expression to select the JSON id. Defaults to "" which means that
     * the fields name is taken as a base for the XPath expression.
     * 
     * @return
     */
    String value() default "";
}
