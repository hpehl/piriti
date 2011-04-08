package name.pehl.piriti.commons.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 82 $
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Mappings
{
    String DEFAULT_ID_MARKER = "__DEFAULT_ID_MARKER__";


    Mapping[] value();


    Mapping id() default @Mapping(DEFAULT_ID_MARKER);


    Mapping[] idRefs() default {};
}
