package name.pehl.piriti.xml.client;

import java.util.List;

import name.pehl.piriti.commons.client.AbstractWriter;
import name.pehl.piriti.commons.client.ModelWriteEvent;

/**
 * Base class for generated XmlWriters. Contains common code and methods.
 * 
 * @param <T>
 *            The type
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public abstract class AbstractXmlWriter<T> extends AbstractWriter<T> implements XmlWriter<T>
{
    // -------------------------------------------------------------- constants

    static final String PROLOG = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

    // ----------------------------------------------------------------- fields

    protected final XmlRegistry xmlRegistry;


    // ----------------------------------------------------------- constructors

    protected AbstractXmlWriter()
    {
        super();
        this.xmlRegistry = XmlGinjector.INJECTOR.getXmlRegistry();
    }


    // ----------------------------------------------------- write list methods

    @Override
    public String toXml(List<T> models)
    {
        return toXml(models, modelsName(), modelName());
    }


    @Override
    public String toXml(List<T> models, String rootElement)
    {
        return toXml(models, rootElement, modelName());
    }


    @Override
    public String toXml(List<T> models, String rootElement, String nestedRootElement)
    {
        if (models != null && rootElement != null && nestedRootElement != null)
        {
            XmlBuilder xmlBuilder = new XmlBuilder(rootElement);
            for (T model : models)
            {
                if (model != null)
                {
                    XmlBuilder nestedXmlBuilder = new XmlBuilder(nestedRootElement);
                    appendModel(nestedXmlBuilder, model);
                    ModelWriteEvent.fire(this, model, nestedXmlBuilder.toString());
                    xmlBuilder.append(nestedXmlBuilder);
                }
            }
            return PROLOG + xmlBuilder.toString();
        }
        return null;
    }


    // --------------------------------------------------- write single methods

    @Override
    public String toXml(T model)
    {
        return toXml(model, modelName());
    }


    @Override
    public String toXml(T model, String rootElement)
    {
        if (model != null && rootElement != null)
        {
            XmlBuilder xmlBuilder = new XmlBuilder(rootElement);
            appendModel(xmlBuilder, model);
            String xml = xmlBuilder.toString();
            ModelWriteEvent.fire(this, model, xml);
            return PROLOG + xml;
        }
        return null;
    }


    // ------------------------------------------------------- abstract methods

    public abstract void appendModel(XmlBuilder xmlBuilder, T model);


    // --------------------------------------------------------- helper methods

    protected abstract String modelName();


    protected String modelsName()
    {
        return modelName() + "s";
    }
}
