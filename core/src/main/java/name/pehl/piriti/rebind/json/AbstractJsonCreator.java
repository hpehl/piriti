package name.pehl.piriti.rebind.json;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import name.pehl.piriti.client.json.Json;
import name.pehl.piriti.client.json.JsonMappings;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.json.JsonWriter;
import name.pehl.piriti.rebind.AbstractCreator;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.propertyhandler.PropertyAnnotation;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.VariableNames;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;

/**
 * Common creator for {@linkplain JsonReader}s and {@linkplain JsonWriter}s.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 137 $
 */
public abstract class AbstractJsonCreator extends AbstractCreator
{
    // ---------------------------------------------------------- constructors

    public AbstractJsonCreator(GeneratorContext context, JClassType interfaceType, String implName,
            String readerClassname, TreeLogger logger) throws UnableToCompleteException
    {
        super(context, interfaceType, implName, readerClassname, logger);
    }


    // --------------------------------------------------------- create methods

    @Override
    protected void createImports(IndentedWriter writer) throws UnableToCompleteException
    {
        super.createImports(writer);
        writer.write("import com.google.gwt.json.client.*;");
        writer.write("import name.pehl.piriti.client.json.*;");
        writer.write("import name.pehl.totoe.json.client.*;");
    }


    @Override
    protected void createMemberVariables(IndentedWriter writer) throws UnableToCompleteException
    {
        super.createMemberVariables(writer);
        writer.write("private JsonRegistry jsonRegistry;");
    }


    @Override
    protected void createConstructorBody(IndentedWriter writer)
    {
        super.createConstructorBody(writer);
        writer.write("this.jsonRegistry = PiritiGinjector.INJECTOR.getJsonRegistry();");
        writer.write("this.jsonRegistry.register(%s.class, this);", modelType.getQualifiedSourceName());
    }


    // --------------------------------------------------------- helper methods

    protected void handleProperties(IndentedWriter writer) throws UnableToCompleteException
    {
        int counter = 0;
        List<PropertyAnnotation<Json>> properties = findPropertyAnnotations();
        for (Iterator<PropertyAnnotation<Json>> iter = properties.iterator(); iter.hasNext();)
        {
            PropertyAnnotation<Json> propertyAnnotation = iter.next();
            String jsonPath = calculateJsonPath(propertyAnnotation.getAnnotation().value(),
                    propertyAnnotation.getProperty());
            VariableNames variableNames = new VariableNames("jsonObject", "value" + counter, "jsonBuilder");
            PropertyContext propertyContext = new PropertyContext(context.getTypeOracle(), handlerRegistry,
                    interfaceType, modelType, propertyAnnotation.getType(), propertyAnnotation.getProperty(), jsonPath,
                    propertyAnnotation.getAnnotation().format(), false, propertyAnnotation.getAnnotation().converter(),
                    null, propertyAnnotation.getAnnotation().getter(), propertyAnnotation.getAnnotation().setter(),
                    variableNames);
            PropertyHandler propertyHandler = handlerRegistry.findPropertyHandler(propertyContext);
            if (propertyHandler != null && propertyHandler.isValid(writer, propertyContext))
            {
                writer.newline();
                handleProperty(writer, propertyHandler, propertyContext, iter.hasNext());
                counter++;
            }
        }
    }


    /**
     * Returns a sorted set with the annotated properties.
     * 
     * @return
     * @throws UnableToCompleteException
     */
    private List<PropertyAnnotation<Json>> findPropertyAnnotations() throws UnableToCompleteException
    {
        Set<PropertyAnnotation<Json>> properties = new HashSet<PropertyAnnotation<Json>>();

        // Step 1: Add all @Json annotations on fields.
        JField[] fields = findAnnotatedFields(modelType, Json.class);
        for (JField field : fields)
        {
            Json annotation = field.getAnnotation(Json.class);
            PropertyAnnotation<Json> property = new PropertyAnnotation<Json>(field.getName(), field.getType(),
                    annotation, annotation.order());
            properties.add(property);
        }

        // Step 2: Add all @Json annotations in the @JsonMappings annotation
        // from the interfaceType. If there are already annotated properties
        // from step 1, they won't be added again.
        JsonMappings interfaceTypeFields = interfaceType.getAnnotation(JsonMappings.class);
        if (interfaceTypeFields != null)
        {
            Json[] annotations = interfaceTypeFields.value();
            for (Json annotation : annotations)
            {
                JField field = TypeUtils.findField(modelType, annotation.property());
                if (field != null)
                {
                    PropertyAnnotation<Json> property = new PropertyAnnotation<Json>(annotation.property(),
                            field.getType(), annotation, annotation.order());
                    properties.add(property);
                }
                else
                {
                    die("Cannot find field %s in %s or its superclasses", annotation.property(),
                            modelType.getQualifiedSourceName());
                }
            }
        }

        // Sort by order
        Comparator<PropertyAnnotation<Json>> comparator = new Comparator<PropertyAnnotation<Json>>()
        {
            @Override
            public int compare(PropertyAnnotation<Json> left, PropertyAnnotation<Json> right)
            {
                return left.getOrder() - right.getOrder();
            }
        };
        List<PropertyAnnotation<Json>> orderedProperties = new ArrayList<PropertyAnnotation<Json>>(properties);
        Collections.sort(orderedProperties, comparator);
        return orderedProperties;
    }


    protected String calculateJsonPath(String jsonPath, String property)
    {
        String effectiveJsonPath = jsonPath;
        if (effectiveJsonPath == null || effectiveJsonPath.length() == 0)
        {
            effectiveJsonPath = property;
        }
        return effectiveJsonPath;
    }
}
