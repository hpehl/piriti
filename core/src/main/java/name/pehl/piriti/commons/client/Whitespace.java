package name.pehl.piriti.commons.client;

import static name.pehl.piriti.commons.client.WhitespaceHandling.REMOVE;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this annotation top specify how leading and trailing whitespace is
 * handled. Please note that this does not effect whitespace
 * <strong>within</strong> the mapped value.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 82 $
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Whitespace
{
    WhitespaceHandling value() default REMOVE;
}
