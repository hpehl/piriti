package name.pehl.piriti.rebind.type;

import java.util.HashSet;
import java.util.Set;

import name.pehl.piriti.rebind.GeneratorContextHolder;
import name.pehl.piriti.rebind.Logger;
import name.pehl.piriti.rebind.property.InvalidPropertyException;
import name.pehl.piriti.rebind.property.PropertyContext;
import name.pehl.piriti.rebind.property.PropertyContextCreator;
import name.pehl.piriti.rebind.property.PropertySource;

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
    private PropertyContextCreator propertyContextCreator;


    public AbstractTypeProcessor()
    {
        skipTypes = new HashSet<JClassType>();
        propertyContextCreator = new PropertyContextCreator(GeneratorContextHolder.get().getContext().getTypeOracle());
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


    protected void addProperty(TypeContext typeContext, PropertySource propertySource)
    {
        try
        {
            PropertyContext propertyContext = propertyContextCreator.createPropertyContext(typeContext, propertySource);
            Logger.get().debug("Adding property %s to %s", propertyContext, typeContext);
            typeContext.addProperty(propertyContext);
        }
        catch (InvalidPropertyException e)
        {
            Logger.get().warn("Property %s in %s is invalid: %s", propertySource.getName(),
                    typeContext.getType().getQualifiedSourceName(), e.getMessage());
        }

    }


    protected void setId(TypeContext typeContext, PropertySource propertySource)
    {
        try
        {
            PropertyContext propertyContext = propertyContextCreator.createPropertyContext(typeContext, propertySource);
            Logger.get().debug("Setting id %s for %s", propertyContext, typeContext);
            typeContext.setId(propertyContext);
        }
        catch (InvalidPropertyException e)
        {
            Logger.get().warn("Property %s in %s is invalid: %s", propertySource.getName(),
                    typeContext.getType().getQualifiedSourceName(), e.getMessage());
        }

    }


    protected void addReference(TypeContext typeContext, PropertySource propertySource)
    {
        try
        {
            PropertyContext propertyContext = propertyContextCreator.createPropertyContext(typeContext, propertySource);
            Logger.get().debug("Adding reference %s to %s", propertyContext, typeContext);
            typeContext.addReference(propertyContext);
        }
        catch (InvalidPropertyException e)
        {
            Logger.get().warn("Property %s in %s is invalid: %s", propertySource.getName(),
                    typeContext.getType().getQualifiedSourceName(), e.getMessage());
        }
    }


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
}
