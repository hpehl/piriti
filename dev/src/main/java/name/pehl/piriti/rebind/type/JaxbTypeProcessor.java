package name.pehl.piriti.rebind.type;

import java.util.Set;

import javax.inject.Inject;

import name.pehl.piriti.rebind.Logger;
import name.pehl.piriti.rebind.property.PropertyContextCreator;
import name.pehl.piriti.rebind.property.PropertyContextValidator;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

public class JaxbTypeProcessor extends AbstractTypeProcessor
{
    @Inject
    public JaxbTypeProcessor(PropertyContextCreator propertyContextCreator,
            PropertyContextValidator propertyContextValidator, Logger logger)
    {
        super(propertyContextCreator, propertyContextValidator, logger);
    }


    @Override
    protected void doProcess(TypeContext typeContext, Set<? extends JClassType> skipTypes)
            throws UnableToCompleteException
    {
    }
}
