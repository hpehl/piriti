package name.pehl.piriti.rebind;

import static java.util.Arrays.asList;
import static name.pehl.piriti.commons.client.WhitespaceHandling.REMOVE;
import static name.pehl.piriti.rebind.propertyhandler.ReferenceType.ID;
import static name.pehl.piriti.rebind.propertyhandler.ReferenceType.IDREF;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import name.pehl.piriti.commons.client.Format;
import name.pehl.piriti.commons.client.Id;
import name.pehl.piriti.commons.client.IdRef;
import name.pehl.piriti.commons.client.Order;
import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.commons.client.Transient;
import name.pehl.piriti.commons.client.Whitespace;
import name.pehl.piriti.commons.client.WhitespaceHandling;
import name.pehl.piriti.converter.client.Convert;
import name.pehl.piriti.converter.client.Converter;
import name.pehl.piriti.property.client.Getter;
import name.pehl.piriti.property.client.PropertyGetter;
import name.pehl.piriti.property.client.PropertySetter;
import name.pehl.piriti.property.client.Setter;
import name.pehl.piriti.rebind.propertyhandler.ReferenceType;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;

public class PojoTypeProcessor extends AbstractTypeProcessor
{
    @Override
    @SuppressWarnings("unchecked")
    protected void doProcess(TypeContext typeContext, Set<? extends JClassType> skipTypes, VariableNames variableNames)
            throws UnableToCompleteException
    {
        // normal mappings
        List<JField> fields = new ArrayList<JField>();
        collectFields(typeContext.getType(), fields, Collections.<Class<? extends Annotation>> emptySet(),
                new HashSet<Class<? extends Annotation>>(asList(Transient.class, Id.class, IdRef.class)));
        for (JField field : fields)
        {
            PropertyContext propertyContext = createPropertyContext(typeContext, field, null, variableNames);
            if (propertyContext != null)
            {
                typeContext.addProperty(propertyContext);
            }
            variableNames = variableNames.next();
        }

        // id
        List<JField> idFields = new ArrayList<JField>();
        collectFields(typeContext.getType(), idFields, new HashSet<Class<? extends Annotation>>(asList(Id.class)),
                new HashSet<Class<? extends Annotation>>(asList(Transient.class, IdRef.class)));
        if (!idFields.isEmpty())
        {
            if (idFields.size() > 1)
            {
                throw new UnableToCompleteException();
            }
            else
            {
                PropertyContext propertyContext = createPropertyContext(typeContext, idFields.get(0), ID, variableNames);
                if (propertyContext != null)
                {
                    typeContext.setId(propertyContext);
                }
                variableNames = variableNames.next();
            }
        }

        // idref
        List<JField> idRefFields = new ArrayList<JField>();
        collectFields(typeContext.getType(), idRefFields,
                new HashSet<Class<? extends Annotation>>(asList(IdRef.class)),
                new HashSet<Class<? extends Annotation>>(asList(Transient.class, Id.class)));
        for (JField field : idRefFields)
        {
            PropertyContext propertyContext = createPropertyContext(typeContext, field, IDREF, variableNames);
            if (propertyContext != null)
            {
                typeContext.addReference(propertyContext);
            }
            variableNames = variableNames.next();
        }
    }


    /**
     * Collects all non-static, non-transient fields from the specified type and
     * all of its supertypes.
     * 
     * @param type
     * @param fields
     */
    protected void collectFields(JClassType type, List<JField> fields,
            Set<Class<? extends Annotation>> mustHaveAnnotations, Set<Class<? extends Annotation>> annotationsToSkip)
    {
        if (skipType(type))
        {
            return;
        }

        // Superclass first please
        collectFields(type.getSuperclass(), fields, mustHaveAnnotations, annotationsToSkip);

        // Collect fields
        JField[] currentFields = type.getFields();
        if (currentFields != null)
        {
            for (JField field : currentFields)
            {
                if (skipField(field, annotationsToSkip))
                {
                    continue;
                }
                if (includeField(field, mustHaveAnnotations))
                {
                    fields.add(field);
                }
            }
        }
    }


    protected boolean skipField(JField field, Set<Class<? extends Annotation>> annotationsToSkip)
    {
        if (field.isTransient() || field.isStatic())
        {
            return true;
        }
        for (Class<? extends Annotation> a : annotationsToSkip)
        {
            if (field.isAnnotationPresent(a))
            {
                return true;
            }
        }
        return false;
    }


    private boolean includeField(JField field, Set<Class<? extends Annotation>> mustHaveAnnotations)
    {
        if (mustHaveAnnotations.isEmpty())
        {
            return true;
        }
        for (Class<? extends Annotation> a : mustHaveAnnotations)
        {
            if (field.isAnnotationPresent(a))
            {
                return true;
            }
        }
        return false;
    }


    protected PropertyContext createPropertyContext(TypeContext typeContext, JField field, ReferenceType referenceType,
            VariableNames variableNames) throws UnableToCompleteException
    {
        String path = getPath(field);
        String format = getFormat(field);
        WhitespaceHandling whitespaceHandling = getWhitespaceHandling(field);
        // TODO Use order: int order = getOrder(field);
        Class<? extends Converter<?>> converter = getConverter(field);
        Class<? extends PropertyGetter<?, ?>> getter = getGetter(field);
        Class<? extends PropertySetter<?, ?>> setter = getSetter(field);
        PropertyContext propertyContext = new PropertyContext(typeContext, field.getType(), field.getName(), path,
                format, whitespaceHandling, converter, getter, setter, referenceType, variableNames);
        return propertyContext;
    }


    protected String getPath(JField field)
    {
        String path = field.getName();
        Path pathAnno = field.getAnnotation(Path.class);
        if (pathAnno != null)
        {
            path = pathAnno.value();
        }
        return path;
    }


    protected String getFormat(JField field)
    {
        String format = null;
        Format formatAnno = field.getAnnotation(Format.class);
        if (formatAnno != null)
        {
            format = formatAnno.value();
        }
        return format;
    }


    protected WhitespaceHandling getWhitespaceHandling(JField field)
    {
        WhitespaceHandling whitespaceHandling = REMOVE;
        Whitespace whitespaceAnno = field.getAnnotation(Whitespace.class);
        if (whitespaceAnno != null)
        {
            whitespaceHandling = whitespaceAnno.value();
        }
        return whitespaceHandling;
    }


    protected int getOrder(JField field)
    {
        int order = 0;
        Order orderAnno = field.getAnnotation(Order.class);
        if (orderAnno != null)
        {
            order = orderAnno.value();
        }
        return order;
    }


    protected Class<? extends Converter<?>> getConverter(JField field)
    {
        Class<? extends Converter<?>> converter = null;
        Convert convertAnno = field.getAnnotation(Convert.class);
        if (convertAnno != null)
        {
            converter = convertAnno.value();
        }
        return converter;
    }


    protected Class<? extends PropertyGetter<?, ?>> getGetter(JField field)
    {
        Class<? extends PropertyGetter<?, ?>> getter = null;
        Getter getterAnno = field.getAnnotation(Getter.class);
        if (getterAnno != null)
        {
            getter = getterAnno.value();
        }
        return getter;
    }


    protected Class<? extends PropertySetter<?, ?>> getSetter(JField field)
    {
        Class<? extends PropertySetter<?, ?>> setter = null;
        Setter setterAnno = field.getAnnotation(Setter.class);
        if (setterAnno != null)
        {
            setter = setterAnno.value();
        }
        return setter;
    }
}
