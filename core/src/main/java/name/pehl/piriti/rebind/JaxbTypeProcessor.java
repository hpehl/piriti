package name.pehl.piriti.rebind;

import java.util.Set;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

public class JaxbTypeProcessor extends AbstractTypeProcessor
{
    public JaxbTypeProcessor(TreeLogger logger)
    {
        super(logger);
    }


    @Override
    protected void doProcess(TypeContext typeContext, Set<? extends JClassType> skipTypes)
            throws UnableToCompleteException
    {
    }
}
