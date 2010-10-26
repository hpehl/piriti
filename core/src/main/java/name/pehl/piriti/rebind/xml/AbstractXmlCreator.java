package name.pehl.piriti.rebind.xml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import name.pehl.piriti.client.xml.Xml;
import name.pehl.piriti.client.xml.XmlMappings;
import name.pehl.piriti.client.xml.XmlReader;
import name.pehl.piriti.client.xml.XmlWriter;
import name.pehl.piriti.rebind.AbstractCreator;
import name.pehl.piriti.rebind.IndentedWriter;
import name.pehl.piriti.rebind.TypeUtils;
import name.pehl.piriti.rebind.propertyhandler.PropertyAnnotation;
import name.pehl.piriti.rebind.propertyhandler.PropertyContext;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandler;
import name.pehl.piriti.rebind.propertyhandler.PropertyHandlerRegistry;
import name.pehl.piriti.rebind.propertyhandler.VariableNames;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JField;
import com.google.gwt.core.ext.typeinfo.JType;

/**
 * Common creator for {@linkplain XmlReader}s and {@linkplain XmlWriter}s.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 139 $
 */
public abstract class AbstractXmlCreator extends AbstractCreator
{
    // ---------------------------------------------------------- constructors

    public AbstractXmlCreator(GeneratorContext context, JClassType interfaceType, String implName,
            String readerClassname, TreeLogger logger) throws UnableToCompleteException
    {
        super(context, interfaceType, implName, readerClassname, logger);
    }


    // ---------------------------------------------------- overwritten methods

    @Override
    protected PropertyHandlerRegistry setupFieldHandlerRegistry()
    {
        return new XmlPropertyHandlerRegistry();
    }


    // --------------------------------------------------------- create methods

    @Override
    protected void createImports(IndentedWriter writer) throws UnableToCompleteException
    {
        super.createImports(writer);
        writer.write("import name.pehl.piriti.client.xml.*;");
        writer.write("import name.pehl.totoe.xml.client.*;");
        writer.write("import static name.pehl.piriti.client.xml.XmlReader.*;");
    }


    @Override
    protected void createMemberVariables(IndentedWriter writer) throws UnableToCompleteException
    {
        super.createMemberVariables(writer);
        writer.write("private XmlRegistry xmlRegistry;");
    }


    @Override
    protected void createConstructorBody(IndentedWriter writer)
    {
        super.createConstructorBody(writer);
        writer.write("this.xmlRegistry = PiritiGinjector.INJECTOR.getXmlRegistry();");
        writer.write("this.xmlRegistry.register(%s.class, this);", modelType.getQualifiedSourceName());
    }


    protected void handleProperties(IndentedWriter writer) throws UnableToCompleteException
    {
        int counter = 0;
        List<PropertyAnnotation<Xml>> properties = findPropertyAnnotations();
        for (Iterator<PropertyAnnotation<Xml>> iter = properties.iterator(); iter.hasNext();)
        {
            PropertyAnnotation<Xml> propertyAnnotation = iter.next();
            String xpath = calculateXpath(propertyAnnotation.getAnnotation().value(), propertyAnnotation.getProperty(),
                    propertyAnnotation.getType());
            VariableNames variableNames = new VariableNames("element", "value" + counter, "xmlBuilder");
            PropertyContext propertyContext = new PropertyContext(context.getTypeOracle(), handlerRegistry,
                    interfaceType, modelType, propertyAnnotation.getType(), propertyAnnotation.getProperty(), xpath,
                    propertyAnnotation.getAnnotation().format(), propertyAnnotation.getAnnotation().stripWsnl(),
                    propertyAnnotation.getAnnotation().converter(), null, propertyAnnotation.getAnnotation().getter(),
                    propertyAnnotation.getAnnotation().setter(), variableNames);
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
    protected List<PropertyAnnotation<Xml>> findPropertyAnnotations() throws UnableToCompleteException
    {
        Set<PropertyAnnotation<Xml>> properties = new HashSet<PropertyAnnotation<Xml>>();

        // Step 1: Add all @Xml annotations on fields.
        JField[] fields = findAnnotatedFields(modelType, Xml.class);
        for (JField field : fields)
        {
            Xml annotation = field.getAnnotation(Xml.class);
            properties
                    .add(new PropertyAnnotation<Xml>(field.getName(), field.getType(), annotation, annotation.order()));
        }

        // Step 2: Add all @Xml annotations in the @XmlMappings annotation
        // from the interfaceType. If there are already annotated properties
        // from step 1, they won't be added again.
        XmlMappings xmlFields = interfaceType.getAnnotation(XmlMappings.class);
        if (xmlFields != null)
        {
            Xml[] annotations = xmlFields.value();
            for (Xml annotation : annotations)
            {
                JField field = TypeUtils.findField(modelType, annotation.property());
                if (field != null)
                {
                    properties.add(new PropertyAnnotation<Xml>(annotation.property(), field.getType(), annotation,
                            annotation.order()));
                }
                else
                {
                    die("Cannot find field %s in %s or its superclasses", annotation.property(),
                            modelType.getQualifiedSourceName());
                }
            }
        }

        // Sort by order
        Comparator<PropertyAnnotation<Xml>> comparator = new Comparator<PropertyAnnotation<Xml>>()
        {
            @Override
            public int compare(PropertyAnnotation<Xml> left, PropertyAnnotation<Xml> right)
            {
                return left.getOrder() - right.getOrder();
            }
        };
        List<PropertyAnnotation<Xml>> orderedProperties = new ArrayList<PropertyAnnotation<Xml>>(properties);
        Collections.sort(orderedProperties, comparator);
        return orderedProperties;
    }


    protected String calculateXpath(String xpath, String property, JType type)
    {
        String effectiveXpath = xpath;
        if (effectiveXpath == null || effectiveXpath.length() == 0)
        {
            effectiveXpath = property;
            if (type.isPrimitive() != null || TypeUtils.isBasicType(type) || type.isEnum() != null)
            {
                effectiveXpath += "/text()";
            }
        }
        return effectiveXpath;
    }
}
