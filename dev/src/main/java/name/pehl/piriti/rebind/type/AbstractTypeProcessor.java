package name.pehl.piriti.rebind.type;

import static name.pehl.piriti.rebind.property.ReferenceType.*;

import java.util.HashSet;
import java.util.Set;

import name.pehl.piriti.rebind.Logger;
import name.pehl.piriti.rebind.property.InvalidPropertyException;
import name.pehl.piriti.rebind.property.PropertyContext;
import name.pehl.piriti.rebind.property.PropertyContextCreator;
import name.pehl.piriti.rebind.property.PropertyContextValidator;
import name.pehl.piriti.rebind.property.PropertySource;
import name.pehl.piriti.rebind.property.ReferenceType;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public abstract class AbstractTypeProcessor implements TypeProcessor
{
    private TypeProcessor next;
    private Set<? extends JClassType> skipTypes;
    protected final PropertyContextCreator propertyContextCreator;
    protected final PropertyContextValidator propertyContextValidator;
    protected final Logger logger;


    public AbstractTypeProcessor(PropertyContextCreator propertyContextCreator,
            PropertyContextValidator propertyContextValidator, Logger logger)
    {
        this.skipTypes = new HashSet<JClassType>();
        this.propertyContextCreator = propertyContextCreator;
        this.propertyContextValidator = propertyContextValidator;
        this.logger = logger;
    }


    @Override
    public TypeProcessor getNext()
    {
        return next;
    }


    @Override
    public void setNext(TypeProcessor processor)
    {
        next = processor;
    }


    @Override
    public boolean hasNext()
    {
        return next != null;
    }


    /**
     * @param typeContext
     * @see name.pehl.piriti.rebind.type.TypeProcessor#process(name.pehl.piriti.rebind.type.TypeContext)
     */
    @Override
    public final void process(TypeContext typeContext) throws UnableToCompleteException
    {
        // log
        logger.debug("Entering TypeProcessor %s - processing %s", getClass().getSimpleName(), typeContext);

        // prepare
        skipTypes = typeContext.getStopAt().getFlattenedSupertypeHierarchy();

        // process
        doProcess(typeContext, skipTypes);

        // next one please
        if (hasNext())
        {
            getNext().process(typeContext);
        }
    }


    protected abstract void doProcess(TypeContext typeContext, Set<? extends JClassType> skipTypes)
            throws UnableToCompleteException;


    protected boolean skipType(JClassType type)
    {
        if (type == null)
        {
            return true;
        }
        for (JClassType t : skipTypes)
        {
            if (t.isInterface() != null)
            {
                continue;
            }
            if (t.equals(type))
            {
                return true;
            }
        }
        return false;
    }


    protected void addProperty(TypeContext typeContext, PropertySource propertySource)
    {
        try
        {
            PropertyContext propertyContext = createPropertyContext(typeContext, propertySource, PROPERTY);
            logger.debug("Adding property %s to %s", propertyContext, typeContext);
            typeContext.addProperty(propertyContext);
        }
        catch (InvalidPropertyException e)
        {
            // if this property was already added to the TypeContext by another
            // TypeProcessor, we have to remove it!
            typeContext.removeProperty(propertySource.getName());
            invalidProperty(typeContext, propertySource, e);
        }
    }


    protected void setId(TypeContext typeContext, PropertySource propertySource)
    {
        try
        {
            PropertyContext propertyContext = createPropertyContext(typeContext, propertySource, ID);
            logger.debug("Setting id %s for %s", propertyContext, typeContext);
            typeContext.setId(propertyContext);
        }
        catch (InvalidPropertyException e)
        {
            // if the id was already set to the TypeContext by another
            // TypeProcessor, we have to unset it!
            typeContext.setId(null);
            invalidProperty(typeContext, propertySource, e);
        }

    }


    protected void addReference(TypeContext typeContext, PropertySource propertySource)
    {
        try
        {
            PropertyContext propertyContext = createPropertyContext(typeContext, propertySource, REFERENCE);
            logger.debug("Adding reference %s to %s", propertyContext, typeContext);
            typeContext.addReference(propertyContext);
        }
        catch (InvalidPropertyException e)
        {
            // if this reference was already added to the TypeContext by another
            // TypeProcessor, we have to remove it!
            typeContext.removeReference(propertySource.getName());
            invalidProperty(typeContext, propertySource, e);
        }
    }


    private PropertyContext createPropertyContext(TypeContext typeContext, PropertySource propertySource,
            ReferenceType referenceType) throws InvalidPropertyException
    {
        PropertyContext propertyContext = propertyContextCreator.createPropertyContext(typeContext, propertySource,
                referenceType);
        propertyContextValidator.validate(typeContext, propertyContext);
        return propertyContext;
    }


    private void invalidProperty(TypeContext typeContext, PropertySource propertySource, InvalidPropertyException e)
    {
        logger.warn(e.getMessage());
    }
}
