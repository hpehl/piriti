package name.pehl.piriti.commons.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Using this annotation you can exclude a property from deserialization, but
 * assign the native info. Therefore the property assigned with {@code @}Native
 * must have one of the following types:
 * <ul>
 * <li>String
 * <li>{@link com.google.gwt.json.client.JSONValue} or a subtype of it
 * <li>{@link name.pehl.totoe.xml.client.Node} or a subtype of it
 * </ul>
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Native
{
}
