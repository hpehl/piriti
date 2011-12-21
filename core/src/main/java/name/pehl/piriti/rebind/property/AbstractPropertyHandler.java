package name.pehl.piriti.rebind.property;

import name.pehl.piriti.rebind.Logger;

/**
 * Abstract base class for {@linkplain PropertyHandler}s with default
 * implementations and common code.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public abstract class AbstractPropertyHandler implements PropertyHandler
{
    // ---------------------------------------------------- overwritten methods

    @Override
    public boolean isValid(PropertyContext propertyContext)
    {
        if (propertyContext.getTypeContext().isReader())
        {
            if (!(propertyContext.isAccessibleField() || propertyContext.isCallableGetter()))
            {
                skipProperty(propertyContext, "No accessible field or getter for \"" + propertyContext.getName()
                        + "\" in " + propertyContext.getTypeContext());
                return false;
            }
        }
        else if (propertyContext.getTypeContext().isWriter())
        {
            if (!(propertyContext.isAccessibleField() || propertyContext.isCallableSetter()))
            {
                skipProperty(propertyContext, "No accessible field or setter for \"" + propertyContext.getName()
                        + "\" in " + propertyContext.getTypeContext());
                return false;
            }
            if (propertyContext.getTypeContext().isJson() && propertyContext.isJsonPath())
            {
                skipProperty(propertyContext, "The path \"" + propertyContext.getPath()
                        + "\" is a JSONPath expressions which is not supported for writing");
                return false;

            }
            // TODO Same for XML and XPath
        }
        return true;
    }


    @Override
    public void calculateTemplate(PropertyContext propertyContext)
    {
        StringBuilder template = basePath(propertyContext);
        template.append(getClass().getSimpleName()).append(".vm");
        propertyContext.setTemplate(template.toString());
    }


    protected StringBuilder basePath(PropertyContext propertyContext)
    {
        StringBuilder basePath = new StringBuilder();
        basePath.append(getClass().getPackage().getName().replace('.', '/')).append("/");
        if (propertyContext.getTypeContext().isJson())
        {
            basePath.append("json/");
        }
        else if (propertyContext.getTypeContext().isXml())
        {
            basePath.append("xml/");
        }
        if (propertyContext.getTypeContext().isReader())
        {
            basePath.append("reader/");
        }
        else if (propertyContext.getTypeContext().isWriter())
        {
            basePath.append("writer/");
        }
        return basePath;
    }


    // --------------------------------------------------------- helper methods

    protected void skipProperty(PropertyContext propertyContext, String reason)
    {
        Logger.get().warn("Skipping property %s. Reason: %s.", propertyContext, reason);
    }
}
