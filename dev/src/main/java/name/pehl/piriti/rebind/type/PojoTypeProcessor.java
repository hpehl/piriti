package name.pehl.piriti.rebind.type;

import static java.util.Arrays.asList;
import static name.pehl.totoe.commons.client.WhitespaceHandling.REMOVE;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

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
import name.pehl.piriti.rebind.Logger;
import name.pehl.piriti.rebind.property.PropertyContextCreator;
import name.pehl.piriti.rebind.property.PropertyContextValidator;
import name.pehl.piriti.rebind.property.PropertySource;
import name.pehl.totoe.commons.client.WhitespaceHandling;

import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JType;

public class PojoTypeProcessor extends AbstractTypeProcessor
{
    @Inject
    public PojoTypeProcessor(PropertyContextCreator propertyContextCreator,
            PropertyContextValidator propertyContextValidator, Logger logger)
    {
        super(propertyContextCreator, propertyContextValidator, logger);
    }


    @Override
    @SuppressWarnings("unchecked")
    protected void doProcess(TypeContext typeContext, Set<? extends JClassType> skipTypes)
            throws UnableToCompleteException
    {
        // normal mappings
        logger.debug("Collect normal mappings...");
        List<JField> fields = new ArrayList<JField>();
        collectFields(typeContext.getType(), fields, Collections.<Class<? extends Annotation>> emptySet(),
                new HashSet<Class<? extends Annotation>>(asList(Transient.class, Id.class, IdRef.class)));
        for (JField field : fields)
        {
            addProperty(typeContext, new FieldPropertySource(field));
        }
        logger.debug("Normal mappings done");

        // id
        logger.debug("Looking for id...");
        List<JField> idFields = new ArrayList<JField>();
        collectFields(typeContext.getType(), idFields, new HashSet<Class<? extends Annotation>>(asList(Id.class)),
                new HashSet<Class<? extends Annotation>>(asList(Transient.class, IdRef.class)));
        if (!idFields.isEmpty())
        {
            if (idFields.size() > 1)
            {
                logger.die("%s id mappings defined in the type hirarchy of %s. Only one id mapping is allowed!",
                        idFields.size(), typeContext.getType().getParameterizedQualifiedSourceName());
            }
            else
            {
                setId(typeContext, new FieldPropertySource(idFields.get(0)));
            }
        }
        logger.debug("Id done");

        // references
        logger.debug("Collect reference mappings...");
        List<JField> refFields = new ArrayList<JField>();
        collectFields(typeContext.getType(), refFields, new HashSet<Class<? extends Annotation>>(asList(IdRef.class)),
                new HashSet<Class<? extends Annotation>>(asList(Transient.class, Id.class)));
        for (JField refField : refFields)
        {
            addReference(typeContext, new FieldPropertySource(refField));
        }
        logger.debug("Reference mappings done");
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
            logger.debug("Skipping %s field %s in %s", field.isTransient() ? "transient" : "static", field.getName(),
                    field.getEnclosingType().getParameterizedQualifiedSourceName());
            return true;
        }
        for (Class<? extends Annotation> a : annotationsToSkip)
        {
            if (field.isAnnotationPresent(a))
            {
                logger.debug("Skipping field %s in %s as it is annotated with @%s", field.getName(), field
                        .getEnclosingType().getParameterizedQualifiedSourceName(), a.getClass().getName());
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
        logger.debug("Skipping field %s in %s as it is not annotated with any of %s", field.getName(), field
                .getEnclosingType().getParameterizedQualifiedSourceName(), mustHaveAnnotations);
        return false;
    }

    // ---------------------------------------------------------- inner classes

    static class FieldPropertySource implements PropertySource
    {
        final JField field;


        FieldPropertySource(JField field)
        {
            this.field = field;
        }


        @Override
        public int getOrder()
        {
            int order = TypeContext.nextOrder();
            if (field.isAnnotationPresent(Order.class))
            {
                order = field.getAnnotation(Order.class).value();
            }
            return order;
        }


        @Override
        public JType getType()
        {
            return field.getType();
        }


        @Override
        public String getName()
        {
            return field.getName();
        }


        @Override
        public String getPath()
        {
            String path = null;
            if (field.isAnnotationPresent(Id.class))
            {
                path = field.getAnnotation(Id.class).value();
            }
            else if (field.isAnnotationPresent(IdRef.class))
            {
                path = field.getAnnotation(IdRef.class).value();
            }
            else if (field.isAnnotationPresent(Path.class))
            {
                path = field.getAnnotation(Path.class).value();
            }
            return path;
        }


        @Override
        public Class<? extends Converter<?>> getConverter()
        {
            Class<? extends Converter<?>> converter = null;
            if (field.isAnnotationPresent(Convert.class))
            {
                converter = field.getAnnotation(Convert.class).value();
            }
            return converter;
        }


        @Override
        public String getFormat()
        {
            String format = null;
            if (field.isAnnotationPresent(Format.class))
            {
                format = field.getAnnotation(Format.class).value();
            }
            return format;
        }


        @Override
        public WhitespaceHandling getWhitespaceHandling()
        {
            WhitespaceHandling whitespaceHandling = REMOVE;
            if (field.isAnnotationPresent(Whitespace.class))
            {
                whitespaceHandling = field.getAnnotation(Whitespace.class).value();
            }
            return whitespaceHandling;
        }


        @Override
        public boolean isNative()
        {
            boolean nativ = field.isAnnotationPresent(Native.class);
            return nativ;
        }


        @Override
        public Class<? extends InstanceCreator<?, ?>> getInstanceCreator()
        {
            Class<? extends InstanceCreator<?, ?>> instanceCreator = null;
            if (field.isAnnotationPresent(CreateWith.class))
            {
                instanceCreator = field.getAnnotation(CreateWith.class).value();
            }
            return instanceCreator;
        }


        @Override
        public Class<? extends PropertyGetter<?, ?>> getGetter()
        {
            Class<? extends PropertyGetter<?, ?>> getter = null;
            if (field.isAnnotationPresent(Getter.class))
            {
                getter = field.getAnnotation(Getter.class).value();
            }
            return getter;
        }


        @Override
        public Class<? extends PropertySetter<?, ?>> getSetter()
        {
            Class<? extends PropertySetter<?, ?>> setter = null;
            if (field.isAnnotationPresent(Setter.class))
            {
                setter = field.getAnnotation(Setter.class).value();
            }
            return setter;
        }
    }
}
