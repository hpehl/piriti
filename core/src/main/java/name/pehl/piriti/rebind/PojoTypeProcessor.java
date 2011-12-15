package name.pehl.piriti.rebind;

import static java.util.Arrays.asList;
import static name.pehl.piriti.rebind.ReferenceType.ID;
import static name.pehl.piriti.rebind.ReferenceType.IDREF;
import static name.pehl.totoe.commons.client.WhitespaceHandling.REMOVE;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import name.pehl.piriti.commons.client.CreateWith;
import name.pehl.piriti.commons.client.Format;
import name.pehl.piriti.commons.client.Id;
import name.pehl.piriti.commons.client.IdRef;
import name.pehl.piriti.commons.client.InstanceCreator;
import name.pehl.piriti.commons.client.Native;
import name.pehl.piriti.commons.client.Order;
import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.commons.client.Transient;
import name.pehl.piriti.commons.client.Whitespace;
import name.pehl.piriti.converter.client.Convert;
import name.pehl.piriti.converter.client.Converter;
import name.pehl.piriti.property.client.Getter;
import name.pehl.piriti.property.client.PropertyGetter;
import name.pehl.piriti.property.client.PropertySetter;
import name.pehl.piriti.property.client.Setter;
import name.pehl.totoe.commons.client.WhitespaceHandling;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;

public class PojoTypeProcessor extends AbstractTypeProcessor
{
    @Override
    @SuppressWarnings("unchecked")
    protected void doProcess(TypeContext typeContext, Set<? extends JClassType> skipTypes)
            throws UnableToCompleteException
    {
        // normal mappings
        Logger.get().debug("Collect normal mappings...");
        List<JField> fields = new ArrayList<JField>();
        collectFields(typeContext.getType(), fields, Collections.<Class<? extends Annotation>> emptySet(),
                new HashSet<Class<? extends Annotation>>(asList(Transient.class, Id.class, IdRef.class)));
        for (JField field : fields)
        {
            PropertyContext propertyContext = createPropertyContext(typeContext, field, null);
            if (propertyContext != null)
            {
                Logger.get().debug("Adding property %s to %s", propertyContext, typeContext);
                typeContext.addProperty(propertyContext);
            }
        }
        Logger.get().debug("Normal mappings done");

        // id
        Logger.get().debug("Looking for id...");
        List<JField> idFields = new ArrayList<JField>();
        collectFields(typeContext.getType(), idFields, new HashSet<Class<? extends Annotation>>(asList(Id.class)),
                new HashSet<Class<? extends Annotation>>(asList(Transient.class, IdRef.class)));
        if (!idFields.isEmpty())
        {
            if (idFields.size() > 1)
            {
                Logger.get().die("%s id mappings defined in the type hirarchy of %s. Only one id mapping is allowed!",
                        idFields.size(), typeContext.getType().getParameterizedQualifiedSourceName());
            }
            else
            {
                PropertyContext propertyContext = createPropertyContext(typeContext, idFields.get(0), ID);
                if (propertyContext != null)
                {
                    Logger.get().debug("Settings id %s for %s", propertyContext, typeContext);
                    typeContext.setId(propertyContext);
                }
            }
        }
        Logger.get().debug("Id done");

        // idref
        Logger.get().debug("Collect reference mappings...");
        List<JField> idRefFields = new ArrayList<JField>();
        collectFields(typeContext.getType(), idRefFields,
                new HashSet<Class<? extends Annotation>>(asList(IdRef.class)),
                new HashSet<Class<? extends Annotation>>(asList(Transient.class, Id.class)));
        for (JField field : idRefFields)
        {
            PropertyContext propertyContext = createPropertyContext(typeContext, field, IDREF);
            if (propertyContext != null)
            {
                Logger.get().debug("Adding reference %s to %s", propertyContext, typeContext);
                typeContext.addReference(propertyContext);
            }
        }
        Logger.get().debug("Reference mappings done");
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
            Logger.get().debug("Skipping %s field %s in %s", (field.isTransient() ? "transient" : "static"),
                    field.getName(), field.getEnclosingType().getParameterizedQualifiedSourceName());
            return true;
        }
        for (Class<? extends Annotation> a : annotationsToSkip)
        {
            if (field.isAnnotationPresent(a))
            {
                Logger.get().debug("Skipping field %s in %s as it is annotated with @%s", field.getName(),
                        field.getEnclosingType().getParameterizedQualifiedSourceName(), a.getClass().getName());
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
        Logger.get().debug("Skipping field %s in %s as it is not annotated with any of %s", field.getName(),
                field.getEnclosingType().getParameterizedQualifiedSourceName(), mustHaveAnnotations);
        return false;
    }


    protected PropertyContext createPropertyContext(TypeContext typeContext, JField field, ReferenceType referenceType)
    {
        PropertyContext propertyContext = null;

        int order = getOrder(field);
        String path = getPath(field);
        Class<? extends Converter<?>> converter = getConverter(field);
        String format = getFormat(field);
        WhitespaceHandling whitespaceHandling = getWhitespaceHandling(field);
        Class<? extends InstanceCreator<?, ?>> instanceCreator = getInstanceCreator(field);
        Class<? extends PropertyGetter<?, ?>> getter = getGetter(field);
        Class<? extends PropertySetter<?, ?>> setter = getSetter(field);
        boolean native_ = getNative(field);

        propertyContext = new PropertyContext.Builder(order, typeContext, field.getType(), field.getName()).path(path)
                .converter(converter).format(format).whitespaceHandling(whitespaceHandling)
                .instanceCreator(instanceCreator).getter(getter).setter(setter).referenceType(referenceType)
                .native_(native_).build();
        return propertyContext;
    }


    protected String getPath(JField field)
    {
        String path = null;
        if (field.isAnnotationPresent(Path.class))
        {
            path = field.getAnnotation(Path.class).value();
        }
        return path;
    }


    protected String getFormat(JField field)
    {
        String format = null;
        if (field.isAnnotationPresent(Format.class))
        {
            format = field.getAnnotation(Format.class).value();
        }
        return format;
    }


    protected WhitespaceHandling getWhitespaceHandling(JField field)
    {
        WhitespaceHandling whitespaceHandling = REMOVE;
        if (field.isAnnotationPresent(Whitespace.class))
        {
            whitespaceHandling = field.getAnnotation(Whitespace.class).value();
        }
        return whitespaceHandling;
    }


    protected Class<? extends InstanceCreator<?, ?>> getInstanceCreator(JField field)
    {
        Class<? extends InstanceCreator<?, ?>> instanceCreator = null;
        if (field.isAnnotationPresent(CreateWith.class))
        {
            instanceCreator = field.getAnnotation(CreateWith.class).value();
        }
        return instanceCreator;
    }


    protected int getOrder(JField field)
    {
        int order = TypeContext.nextOrder();
        if (field.isAnnotationPresent(Order.class))
        {
            order = field.getAnnotation(Order.class).value();
        }
        return order;
    }


    protected Class<? extends Converter<?>> getConverter(JField field)
    {
        Class<? extends Converter<?>> converter = null;
        if (field.isAnnotationPresent(Convert.class))
        {
            converter = field.getAnnotation(Convert.class).value();
        }
        return converter;
    }


    protected Class<? extends PropertyGetter<?, ?>> getGetter(JField field)
    {
        Class<? extends PropertyGetter<?, ?>> getter = null;
        if (field.isAnnotationPresent(Getter.class))
        {
            getter = field.getAnnotation(Getter.class).value();
        }
        return getter;
    }


    protected Class<? extends PropertySetter<?, ?>> getSetter(JField field)
    {
        Class<? extends PropertySetter<?, ?>> setter = null;
        if (field.isAnnotationPresent(Setter.class))
        {
            setter = field.getAnnotation(Setter.class).value();
        }
        return setter;
    }


    protected boolean getNative(JField field)
    {
        boolean nativ = field.isAnnotationPresent(Native.class);
        return nativ;
    }
}
