package name.pehl.piriti.rebind.property;


/**
 * Interface for generating code for one property.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public interface PropertyHandler
{
    boolean isValid(PropertyContext propertyContext);


    /**
     * Sets the template for the specified property context
     * 
     * @return
     */
    void calculateTemplate(PropertyContext propertyContext);
}
