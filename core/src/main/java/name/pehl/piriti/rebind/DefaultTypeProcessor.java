package name.pehl.piriti.rebind;

import name.pehl.piriti.commons.client.Id;
import name.pehl.piriti.commons.client.IdRef;
import name.pehl.piriti.commons.client.Transient;

public class DefaultTypeProcessor
extends AbstractTypeProcessor
{
    @SuppressWarnings("unchecked")
    public DefaultTypeProcessor()
    {
        super(null,  new Class[] { Transient.class, Id.class, IdRef.class});
    }
}
