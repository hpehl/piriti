package name.pehl.piriti.rebind.property;

import static name.pehl.piriti.rebind.property.ReferenceType.PROPERTY;
import name.pehl.piriti.rebind.type.TypeContext;
import name.pehl.piriti.rebind.type.TypeUtils;

import com.google.gwt.core.ext.typeinfo.JType;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class PropertyTemplatesLookup
{
    public Templates get(TypeContext typeContext, JType type, ReferenceType referenceType)
    {
        Templates templates = Templates.NO_TEMPLATES;
        String path = getPath(typeContext, referenceType);
        String template = getTemplate(type, referenceType);
        String elementTypeTemplate = null;
        if (referenceType == PROPERTY && (type.isArray() != null || TypeUtils.isCollection(type)))
        {
            JType elementType = null;
            if (type.isArray() != null)
            {
                elementType = type.isArray().getComponentType();
            }
            else
            {
                elementType = TypeUtils.getTypeVariable(type);
            }
            if (elementType != null)
            {
                elementTypeTemplate = getTemplate(elementType, referenceType);
            }
        }

        if (template != null)
        {
            String fullQualifiedTemplatePath = path + "/" + template;
            String fullQualifiedElementTypeTemplatePath = null;
            if (elementTypeTemplate != null)
            {
                fullQualifiedElementTypeTemplatePath = path + "/elementtype/" + elementTypeTemplate;
            }
            templates = new Templates(fullQualifiedTemplatePath, fullQualifiedElementTypeTemplatePath);
        }
        return templates;
    }


    private String getPath(TypeContext typeContext, ReferenceType referenceType)
    {
        StringBuilder path = new StringBuilder("name/pehl/piriti/rebind/");
        String jsonxml = typeContext.isJson() ? "json" : "xml";
        String rw = typeContext.isReader() ? "reader" : "writer";
        path.append(jsonxml).append("/").append(rw).append("/").append(referenceType.name().toLowerCase());
        return path.toString();
    }


    private String getTemplate(JType type, ReferenceType referenceType)
    {
        String template = null;
        switch (referenceType)
        {
            case ID:
                template = "string.vm";
                break;
            case PROPERTY:
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
                else if (TypeUtils.isDate(type))
                {
                    template = "date.vm";
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
                break;
            case REFERENCE:
                if (type.isArray() != null)
                {
                    template = "array.vm";
                }
                else if (TypeUtils.isCollection(type))
                {
                    template = "collection.vm";
                }
                else if (TypeUtils.canContainId(type))
                {
                    template = "any.vm";
                }
                break;
        }
        return template;
    }
}
