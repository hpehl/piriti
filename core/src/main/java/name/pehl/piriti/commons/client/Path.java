package name.pehl.piriti.commons.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The "path" expression to select the JSON / XML data. This can be either just
 * the key of the JSON data, a <a
 * href="http://code.google.com/p/jsonpath/">JSONPath</a> or XPath expression.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Path
{
    String value();
}
