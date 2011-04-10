package name.pehl.piriti.rebind;

import static name.pehl.piriti.rebind.propertyhandler.ReferenceType.ID;
import static name.pehl.piriti.rebind.propertyhandler.ReferenceType.IDREF;

import java.util.Set;

import name.pehl.piriti.commons.client.Mapping;
import name.pehl.piriti.commons.client.Mappings;
import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.commons.client.WhitespaceHandling;
import name.pehl.piriti.converter.client.Converter;
import name.pehl.piriti.property.client.PropertyGetter;
import name.pehl.piriti.property.client.PropertySetter;
import name.pehl.piriti.rebind.propertyhandler.ReferenceType;

import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;

public class RwTypeProcessor extends AbstractTypeProcessor
{
    public RwTypeProcessor(TreeLogger logger)
    {
        super(logger);
    }


    @Override
    protected void doProcess(TypeContext typeContext, Set<? extends JClassType> skipTypes, VariableNames variableNames)
            throws UnableToCompleteException
    {
        JClassType rwType = typeContext.getRwType();
        if (rwType.isAnnotationPresent(Mappings.class))
        {
            Mappings mappingsAnno = rwType.getAnnotation(Mappings.class);
            Mapping[] mappings = mappingsAnno.value();
            for (Mapping mapping : mappings)
            {
                PropertyContext propertyContext = createPropertyContext(typeContext, mapping, null, variableNames);
                if (propertyContext != null)
                {
                    typeContext.addProperty(propertyContext);
                }
                variableNames = variableNames.next();
            }

            Mapping idMapping = mappingsAnno.id();
            if (!idMapping.value().equals(Mappings.NO_ID))
            {
                PropertyContext propertyContext = createPropertyContext(typeContext, idMapping, ID, variableNames);
                if (propertyContext != null)
                {
                    typeContext.setId(propertyContext);
                }
                variableNames = variableNames.next();
            }

            Mapping[] idRefMappings = mappingsAnno.references();
            for (Mapping idRefMapping : idRefMappings)
            {
                PropertyContext propertyContext = createPropertyContext(typeContext, idRefMapping, IDREF, variableNames);
                if (propertyContext != null)
                {
                    typeContext.addReference(propertyContext);
                }
                variableNames = variableNames.next();
            }
        }
    }


    protected PropertyContext createPropertyContext(TypeContext typeContext, Mapping mapping,
            ReferenceType referenceType, VariableNames variableNames) throws UnableToCompleteException
    {
        PropertyContext propertyContext = null;
        JField field = typeContext.getType().getField(mapping.value());
        if (field != null)
        {
            String path = getPath(field, mapping.path());
            String format = mapping.format();
            WhitespaceHandling whitespaceHandling = mapping.whitespace();
            Class<? extends Converter<?>> converter = mapping.convert();
            Class<? extends PropertyGetter<?, ?>> getter = mapping.getter();
            Class<? extends PropertySetter<?, ?>> setter = mapping.setter();
            propertyContext = new PropertyContext(typeContext, field.getType(), field.getName(), path, format,
                    whitespaceHandling, converter, getter, setter, null, variableNames, logger);
        }
        else
        {
            // TODO Warning / error?
        }
        return propertyContext;
    }


    protected String getPath(JField field, String annotationPath)
    {
        String path = field.getName();
        Path pathAnno = field.getAnnotation(Path.class);
        if (pathAnno != null)
        {
            path = pathAnno.value();
        }
        return path;
    }
}
