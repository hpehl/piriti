package name.pehl.gwt.piriti.rebind;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import name.pehl.gwt.piriti.client.xml.XmlField;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JArrayType;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JEnumType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

/**
 * Class which contains information needed to generate code for the XPath
 * evaluation, conversion and assignment of a field. An instance of this class
 * is generated in {@link XmlReaderCreator} and passed to the
 * {@link FieldHandler}.
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class FieldContext
{
    private static final String AS_STRING_SUFFIX = "AsString";
    private static final String READER_SUFFIX = "Reader";

    private final TypeOracle typeOracle;
    private final JClassType modelType;
    private final FieldHandlerRegistry handlerRegistry;
    private final String sourceType;
    private final String sourceVariable;
    private final XmlField xmlField;
    private final JField field;
    private final JType type;
    private final String xpath;
    private final String format;
    private final String valueVariable;
    private final String valueAsStringVariable;
    private final String valueReaderVariable;


    public FieldContext(TypeOracle typeOracle, JClassType modelType, FieldHandlerRegistry handlerRegistry,
            String sourceType, String sourceVariable, XmlField xmlField, JField field, String valueVariable)
            throws UnableToCompleteException
    {
        // constructor parameters
        this.typeOracle = typeOracle;
        this.modelType = modelType;
        this.handlerRegistry = handlerRegistry;
        this.sourceType = sourceType;
        this.sourceVariable = sourceVariable;
        this.xmlField = xmlField;
        this.field = field;
        this.valueVariable = valueVariable;

        // calculated values
        JPrimitiveType primitiveType = field.getType().isPrimitive();
        if (primitiveType != null) // isPrimitive() is not available here!
        {
            try
            {
                // Use the boxed type for primitives
                this.type = typeOracle.getType(primitiveType.getQualifiedBoxedSourceName());
            }
            catch (NotFoundException e)
            {
                throw new UnableToCompleteException();
            }
        }
        else
        {
            this.type = field.getType();
        }
        this.xpath = xpathFromType();
        this.format = xmlField.format().equals("") ? null : xmlField.format();
        this.valueAsStringVariable = valueVariable + AS_STRING_SUFFIX;
        this.valueReaderVariable = valueVariable + READER_SUFFIX;
    }


    private String xpathFromType()
    {
        String xpath = xmlField.value();
        if ("".equals(xpath))
        {
            xpath = field.getName();
            if (isPrimitive() || isBasicType() || isEnum())
            {
                xpath += "/text()";
            }
        }
        return xpath;
    }


    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder().append(type.getParameterizedQualifiedSourceName()).append(" ")
                .append(field.getName()).append(", xpath=\"").append(xpath).append("\"");
        if (format != null)
        {
            builder.append(", format=\"").append(format).append("\"");
        }
        return builder.toString();
    }


    public boolean isPrimitive()
    {
        return getPrimitiveType() != null;
    }


    public JPrimitiveType getPrimitiveType()
    {
        return type.isPrimitive();
    }


    public boolean isBasicType()
    {
        if (Boolean.class.getName().equals(type.getQualifiedSourceName())
                || Byte.class.getName().equals(type.getQualifiedSourceName())
                || Character.class.getName().equals(type.getQualifiedSourceName())
                || Date.class.getName().equals(type.getQualifiedSourceName())
                || Double.class.getName().equals(type.getQualifiedSourceName())
                || Float.class.getName().equals(type.getQualifiedSourceName())
                || Integer.class.getName().equals(type.getQualifiedSourceName())
                || Long.class.getName().equals(type.getQualifiedSourceName())
                || Short.class.getName().equals(type.getQualifiedSourceName())
                || String.class.getName().equals(type.getQualifiedSourceName()))
        {
            return true;
        }
        return false;
    }


    public boolean isEnum()
    {
        return getEnumType() != null;
    }


    public JEnumType getEnumType()
    {
        return type.isEnum();
    }


    public boolean isClassOrInterface()
    {
        return getClassOrInterfaceType() != null;
    }


    public JClassType getClassOrInterfaceType()
    {
        return type.isClass();
    }


    public boolean isArray()
    {
        return getArrayType() != null;
    }


    public JArrayType getArrayType()
    {
        return type.isArray();
    }


    public boolean isCollection()
    {
        if (Collection.class.getName().equals(type.getQualifiedSourceName())
                || List.class.getName().equals(type.getQualifiedSourceName())
                || ArrayList.class.getName().equals(type.getQualifiedSourceName())
                || LinkedList.class.getName().equals(type.getQualifiedSourceName())
                || Set.class.getName().equals(type.getQualifiedSourceName())
                || HashSet.class.getName().equals(type.getQualifiedSourceName())
                || SortedSet.class.getName().equals(type.getQualifiedSourceName())
                || TreeSet.class.getName().equals(type.getQualifiedSourceName()))
        {
            return true;
        }
        return false;
    }


    public boolean isMap()
    {
        if (Map.class.getName().equals(type.getQualifiedSourceName())
                || HashMap.class.getName().equals(type.getQualifiedSourceName())
                || SortedMap.class.getName().equals(type.getQualifiedSourceName())
                || TreeMap.class.getName().equals(type.getQualifiedSourceName()))
        {
            return true;
        }
        return false;
    }


    public TypeOracle getTypeOracle()
    {
        return typeOracle;
    }


    public JClassType getModelType()
    {
        return modelType;
    }


    public FieldHandlerRegistry getHandlerRegistry()
    {
        return handlerRegistry;
    }


    public String getSourceType()
    {
        return sourceType;
    }


    public String getSourceVariable()
    {
        return sourceVariable;
    }


    public XmlField getXmlField()
    {
        return xmlField;
    }


    public JField getField()
    {
        return field;
    }


    public JType getType()
    {
        return type;
    }


    public String getXpath()
    {
        return xpath;
    }


    public String getFormat()
    {
        return format;
    }


    public String getValueVariable()
    {
        return valueVariable;
    }


    public String getValueAsStringVariable()
    {
        return valueAsStringVariable;
    }


    public String getValueReaderVariable()
    {
        return valueReaderVariable;
    }
}
