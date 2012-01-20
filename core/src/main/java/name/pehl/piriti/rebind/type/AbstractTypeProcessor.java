package name.pehl.piriti.rebind.type;

import static name.pehl.piriti.rebind.property.ReferenceType.ID;
import static name.pehl.piriti.rebind.property.ReferenceType.PROPERTY;
import static name.pehl.piriti.rebind.property.ReferenceType.REFERENCE;

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
    private final PropertyContextCreator propertyContextCreator;
    private final PropertyContextValidator propertyContextValidator;


    public AbstractTypeProcessor()
    {
        this.skipTypes = new HashSet<JClassType>();
        this.propertyContextCreator = new PropertyContextCreator();
        this.propertyContextValidator = new PropertyContextValidator();
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
        Logger.get().debug("Entering TypeProcessor %s - processing %s", getClass().getSimpleName(), typeContext);

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
            Logger.get().debug("Adding property %s to %s", propertyContext, typeContext);
            typeContext.addProperty(propertyContext);
        }
        catch (InvalidPropertyException e)
        {
            invalidProperty(typeContext, propertySource, e);
        }

    }


    protected void setId(TypeContext typeContext, PropertySource propertySource)
    {
        try
        {
            PropertyContext propertyContext = createPropertyContext(typeContext, propertySource, ID);
            Logger.get().debug("Setting id %s for %s", propertyContext, typeContext);
            typeContext.setId(propertyContext);
        }
        catch (InvalidPropertyException e)
        {
            invalidProperty(typeContext, propertySource, e);
        }

    }


    protected void addReference(TypeContext typeContext, PropertySource propertySource)
    {
        try
        {
            PropertyContext propertyContext = createPropertyContext(typeContext, propertySource, REFERENCE);
            Logger.get().debug("Adding reference %s to %s", propertyContext, typeContext);
            typeContext.addReference(propertyContext);
        }
        catch (InvalidPropertyException e)
        {
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
        Logger.get().warn("Property %s %s in %s is invalid: %s",
                propertySource.getType().getParameterizedQualifiedSourceName(), propertySource.getName(),
                typeContext.getType().getQualifiedSourceName(), e.getMessage());
    }
}
