package name.pehl.piriti.rebind.property;

import name.pehl.piriti.rebind.type.TypeContext;
import name.pehl.piriti.rebind.type.TypeUtils;

import com.google.gwt.core.ext.typeinfo.JType;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class TemplateFinder
{
    public String getPath(TypeContext typeContext)
    {
        StringBuilder path = new StringBuilder("name/pehl/piriti/rebind/");
        String jsonxml = typeContext.isJson() ? "json" : "xml";
        String rw = typeContext.isReader() ? "reader" : "writer";
        path.append(jsonxml).append("/").append(rw).append("/property/");
        return path.toString();
    }


    public String getTemplate(JType type)
    {
        String template = null;
        if (TypeUtils.isBoolean(type))
        {
            template = "boolean.vm";
        }
        else if (TypeUtils.isNumeric(type))
        {
            template = "numeric.vm";
        }
        else if (TypeUtils.isCharacter(type))
        {
            template = "character.vm";
        }
        else if (TypeUtils.isString(type))
        {
            template = "string.vm";
        }
        else if (type.isEnum() != null)
        {
            template = "enum.vm";
        }
        else if (type.isArray() != null)
        {
            template = "array.vm";
        }
        else if (TypeUtils.isCollection(type))
        {
            template = "collection.vm";
        }
        else
        {
            template = "any.vm";
        }
        return template;
    }
}
