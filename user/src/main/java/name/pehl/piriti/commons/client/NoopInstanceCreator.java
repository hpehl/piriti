package name.pehl.piriti.commons.client;

/**
 * Noop {@link InstanceCreator} used as default value in annotations.
 * 
 * @author $Author: harald.pehl $
 * @version $Date: 2011-04-10 22:32:38 +0200 (So, 10. Apr 2011) $ $Revision:
 *          1097 $
 */
public class NoopInstanceCreator implements InstanceCreator<Object, Object>
{
    /**
     * Throws an {@link UnsupportedOperationException}.
     * 
     * @see name.pehl.piriti.commons.client.InstanceCreator#newInstance(java.lang.Object)
     */
    @Override
    public Object newInstance(Object context)
    {
        throw new UnsupportedOperationException("Not implemented");
    }
}
