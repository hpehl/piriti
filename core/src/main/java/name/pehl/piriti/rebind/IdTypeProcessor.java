package name.pehl.piriti.rebind;

import java.util.List;

import name.pehl.piriti.commons.client.Id;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JField;

public class IdTypeProcessor extends AbstractTypeProcessor
{
    @SuppressWarnings("unchecked")
    public IdTypeProcessor()
    {
        super(new Class[] {Id.class}, null);
    }


    @Override
    protected void processFields(TypeContext typeContext, List<JField> fields) throws UnableToCompleteException
    {
        if (fields.size() > 1)
        {
            throw new UnableToCompleteException();
        }
    }
}
