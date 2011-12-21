package name.pehl.piriti.rebind.property;

import com.google.gwt.core.ext.typeinfo.TypeOracle;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class PropertyCreator
{
    private final TypeOracle typeOracle;


    /**
     * Construct a new instance of this class
     * 
     * @param typeOracle
     */
    PropertyCreator(TypeOracle typeOracle)
    {
        this.typeOracle = typeOracle;
    }


    public PropertyContext createPropertyContext(PropertySource source) throws InvalidPropertyException
    {
        return null;
    }
}
