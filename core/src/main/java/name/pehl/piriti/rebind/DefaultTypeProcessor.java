package name.pehl.piriti.rebind;

import java.util.ArrayList;
import java.util.List;

import name.pehl.piriti.commons.client.Format;
import name.pehl.piriti.commons.client.Order;
import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.commons.client.Transient;
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
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class DefaultTypeProcessor implements TypeProcessor
{
    private TypeProcessor next;


    /**
     * @param typeContext
     * @throws UnableToCompleteException
     * @see name.pehl.piriti.rebind.TypeProcessor#process(name.pehl.piriti.rebind.TypeContext)
     */
    @Override
    public void process(TypeContext typeContext) throws UnableToCompleteException
    {
        doProcess(typeContext);
        if (hasNext())
        {
            next.process(typeContext);
        }
    }


    protected void doProcess(TypeContext typeContext) throws UnableToCompleteException
    {
        JClassType type = typeContext.getType();
        List<JField> fields = new ArrayList<JField>();
        collectFields(type, fields);
        createPropertyContext(typeContext, fields);
    }


    /**
     * @param processor
     * @see name.pehl.piriti.rebind.TypeProcessor#setNext(name.pehl.piriti.rebind.TypeProcessor)
     */
    @Override
    public void setNext(TypeProcessor processor)
    {
        next = processor;
    }


    /**
     * @return
     * @see name.pehl.piriti.rebind.TypeProcessor#hasNext()
     */
    @Override
    public boolean hasNext()
    {
        return next != null;
    }


    // --------------------------------------------------------- helper methods

    protected void collectFields(JClassType type, List<JField> fields)
    {
        // Superclass first please, but skip Object!
        if (type == null || Object.class.getName().equals(type.getQualifiedSourceName()))
        {
            return;
        }
        collectFields(type.getSuperclass(), fields);

        JField[] currentFields = type.getFields();
        if (currentFields != null)
        {
            for (JField field : currentFields)
            {
                if (field.isAnnotationPresent(Transient.class) || field.isTransient() || field.isStatic())
                {
                    continue;
                }
                fields.add(field);
            }
        }
    }


    protected void createPropertyContext(TypeContext typeContext, List<JField> fields) throws UnableToCompleteException
    {
        VariableNames variableNames = typeContext.getVariableNames();
        for (JField field : fields)
        {
            String path = getPath(field);
            String format = getFormat(field);
            // TODO Use order: int order = getOrder(field);
            Class<? extends Converter<?>> converter = getConverter(field);
            Class<? extends PropertyGetter<?, ?>> getter = getGetter(field);
            Class<? extends PropertySetter<?, ?>> setter = getSetter(field);
            PropertyContext propertyContext = new PropertyContext(typeContext, field.getType(), field.getName(), path,
                    format, false, converter, getter, setter, null, variableNames);
            typeContext.addProperty(propertyContext);
            variableNames = variableNames.next();
        }
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
