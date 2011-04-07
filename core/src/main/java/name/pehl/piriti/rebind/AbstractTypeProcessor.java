package name.pehl.piriti.rebind;

import static java.util.Arrays.asList;
import static name.pehl.piriti.commons.client.WhitespaceHandling.REMOVE;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import name.pehl.piriti.commons.client.Format;
import name.pehl.piriti.commons.client.Order;
import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.commons.client.Whitespace;
import name.pehl.piriti.commons.client.WhitespaceHandling;
import name.pehl.piriti.converter.client.Convert;
import name.pehl.piriti.converter.client.Converter;
import name.pehl.piriti.property.client.Getter;
import name.pehl.piriti.property.client.PropertyGetter;
import name.pehl.piriti.property.client.PropertySetter;
import name.pehl.piriti.property.client.Setter;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;

/**
 * Abstract {@link TypeProcessor} which collects all non-static, non-transient
 * fields from {@link TypeContext#getType()} and its superclasses.
 * <p>
 * The following fields are skipped by this {@link TypeProcessor}
 * <ul>
 * <li>Fields which are defined in <code>skipTypes</code>
 * <li>Fields which are annotated with any of <code>skipAnnotations</code>
 * </ul>
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public abstract class AbstractTypeProcessor implements TypeProcessor
{
    private TypeProcessor next;
    private Set<? extends JClassType> skipTypes;
    private final Set<Class<? extends Annotation>> includeAnnotations;
    private final Set<Class<? extends Annotation>> skipAnnotations;


    public AbstractTypeProcessor(Class<? extends Annotation>[] includeAnnotations,
            Class<? extends Annotation>[] skipAnnotations)
    {
        this.includeAnnotations = new HashSet<Class<? extends Annotation>>();
        if (includeAnnotations != null)
        {
            this.includeAnnotations.addAll(asList(includeAnnotations));
        }
        this.skipAnnotations = new HashSet<Class<? extends Annotation>>();
        if (skipAnnotations != null)
        {
            this.skipAnnotations.addAll(asList(skipAnnotations));
        }
    }


    /**
     * @param typeContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.TypeProcessor#process(name.pehl.piriti.rebind.TypeContext)
     */
    @Override
    public final void process(TypeContext typeContext) throws UnableToCompleteException
    {
        skipTypes = typeContext.getStopAt().getFlattenedSupertypeHierarchy();
        List<JField> fields = new ArrayList<JField>();
        collectFields(typeContext.getType(), fields);
        processFields(typeContext, fields);
        if (hasNext())
        {
            next.process(typeContext);
        }
    }


    @Override
    public TypeProcessor setNext(TypeProcessor processor)
    {
        next = processor;
        return this;
    }


    @Override
    public boolean hasNext()
    {
        return next != null;
    }


    /**
     * Collects all non-static, non-transient fields from the specified type and
     * all of its supertypes.
     * 
     * @param type
     * @param fields
     */
    protected void collectFields(JClassType type, List<JField> fields)
    {
        if (skipType(type))
        {
            return;
        }

        // Superclass first please
        collectFields(type.getSuperclass(), fields);

        // Collect fields
        JField[] currentFields = type.getFields();
        if (currentFields != null)
        {
            for (JField field : currentFields)
            {
                if (skipField(field))
                {
                    continue;
                }
                if (includeField(field))
                {
                    fields.add(field);
                }
            }
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


    protected boolean skipField(JField field)
    {
        if (field.isTransient() || field.isStatic())
        {
            return true;
        }
        for (Class<? extends Annotation> a : skipAnnotations)
        {
            if (field.isAnnotationPresent(a))
            {
                return true;
            }
        }
        return false;
    }


    private boolean includeField(JField field)
    {
        if (includeAnnotations.isEmpty())
        {
            return true;
        }
        for (Class<? extends Annotation> a : includeAnnotations)
        {
            if (field.isAnnotationPresent(a))
            {
                return true;
            }
        }
        return false;
    }


    protected void processFields(TypeContext typeContext, List<JField> fields) throws UnableToCompleteException
    {
        VariableNames variableNames = typeContext.getVariableNames();
        for (JField field : fields)
        {
            PropertyContext propertyContext = createPropertyContext(typeContext, field, variableNames);
            if (propertyContext != null)
            {
                typeContext.addProperty(propertyContext);
            }
            variableNames = variableNames.next();
        }
    }


    protected PropertyContext createPropertyContext(TypeContext typeContext, JField field, VariableNames variableNames)
            throws UnableToCompleteException
    {
        String path = getPath(field);
        String format = getFormat(field);
        WhitespaceHandling whitespaceHandling = getWhitespaceHandling(field);
        // TODO Use order: int order = getOrder(field);
        Class<? extends Converter<?>> converter = getConverter(field);
        Class<? extends PropertyGetter<?, ?>> getter = getGetter(field);
        Class<? extends PropertySetter<?, ?>> setter = getSetter(field);
        PropertyContext propertyContext = new PropertyContext(typeContext, field.getType(), field.getName(), path,
                format, whitespaceHandling, converter, getter, setter, null, variableNames);
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
