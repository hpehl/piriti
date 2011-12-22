package name.pehl.piriti.rebind.property;

import name.pehl.piriti.rebind.type.TypeContext;

import com.google.gwt.core.ext.typeinfo.TypeOracle;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class PropertyContextCreator
{
    private final TypeOracle typeOracle;


    /**
     * Construct a new instance of this class
     * 
     * @param typeOracle
     */
    public PropertyContextCreator(TypeOracle typeOracle)
    {
        this.typeOracle = typeOracle;
    }


    public PropertyContext createPropertyContext(TypeContext typeContext, PropertySource source)
            throws InvalidPropertyException
    {
        return null;
    }
}
