package name.pehl.piriti.commons.client;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public @interface CreateWith
{
    Class<? extends InstanceCreator<?, ?>> value();
}
