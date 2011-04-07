package name.pehl.piriti.rebind;

import name.pehl.piriti.commons.client.IdRef;

public class IdRefTypeProcessor extends AbstractTypeProcessor
{
    @SuppressWarnings("unchecked")
    public IdRefTypeProcessor()
    {
        super(new Class[] {IdRef.class}, null);
    }
}
