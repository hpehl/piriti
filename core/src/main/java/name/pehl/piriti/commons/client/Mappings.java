package name.pehl.piriti.commons.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for external mappings.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 82 $
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Mappings
{
    String NO_ID = "This is a special no-id-marker. Do not use for real IDs!";


    Mapping[] value();


    Mapping id() default @Mapping(NO_ID);


    Mapping[] idRefs() default {};
}
