package name.pehl.piriti.client.xml;

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
public @interface XmlId
{
    /**
     * An XPath expression to select the XML Id. Defaults to "" which means that
     * the fields name is taken as a base for the XPath expression.
     * 
     * @return
     */
    String value() default "";
}
