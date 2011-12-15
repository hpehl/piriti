package name.pehl.piriti.rebind.propertyhandler;

import java.util.ArrayList;
import java.util.List;

import name.pehl.piriti.rebind.PropertyContext;
import name.pehl.piriti.rebind.TypeUtils;

import com.google.gwt.core.ext.typeinfo.JClassType;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public class DefaultPropertyHandler extends AbstractPropertyHandler
{
    @Override
    public boolean isValid(PropertyContext propertyContext)
    {
        if (!super.isValid(propertyContext))
        {
            return false;
        }
        if (!propertyContext.isClassOrInterface())
        {
            skipProperty(propertyContext, "Type is no class or interface");
            return false;
        }
        List<JClassType> concreteTypes = new ArrayList<JClassType>();
        collectConcreteTypes(concreteTypes, propertyContext.getClassOrInterfaceType());
        propertyContext.setConcreteTypes(concreteTypes);
        return true;
    }


    /**
     * Finds all concrete subtypes starting from {@code type}. If {@code type}
     * itself is concrete the list will only contains {@code type}.
     * 
     * @param types
     * @param type
     */
    private void collectConcreteTypes(List<JClassType> concreteTypes, JClassType type)
    {
        if (type != null)
        {
            if (type.isAbstract() || type.isInterface() != null)
            {
                JClassType[] subtypes = type.getSubtypes();
                if (subtypes != null && subtypes.length != 0)
                {
                    for (JClassType subtype : subtypes)
                    {
                        collectConcreteTypes(concreteTypes, subtype);
                    }
                }
            }
            else
            {
                if (!(TypeUtils.isJavaType(type) || TypeUtils.isGwtType(type)) && TypeUtils.isDefaultInstantiable(type))
                {
                    concreteTypes.add(type);
                }
            }
        }
    }
}
