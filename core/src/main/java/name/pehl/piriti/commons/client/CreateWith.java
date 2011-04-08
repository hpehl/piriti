package name.pehl.piriti.commons.client;

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
public @interface CreateWith
{
    Class<? extends InstanceCreator<?, ?>> value();
}
