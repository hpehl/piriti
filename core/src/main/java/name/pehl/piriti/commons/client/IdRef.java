package name.pehl.piriti.commons.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to reference one or more POJOs. The properties type must be T, an
 * array or collection of Ts, where T is a POJO with a registered reader.
 * 
 * @see Id
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 82 $
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IdRef
{
}
