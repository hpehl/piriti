package name.pehl.piriti.commons.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to specify an {@link InstanceCreator} for a POJO. Normally the
 * POJO is created using <code>GWT.create()</code> which in turn calls the
 * no-arg default constructor. If you POJO doesn't have such a constructor or
 * you want to customize the instantiation of you POJO use this annotation
 * together with a specific {@link InstanceCreator}.
 * 
 * @see InstanceCreator
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface CreateWith
{
    Class<? extends InstanceCreator<?, ?>> value();
}
