package name.pehl.piriti.xml.client;

import java.util.List;

import name.pehl.piriti.commons.client.AbstractWriter;

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
        String xml = null;
        if (models != null && rootElement != null && nestedRootElement != null)
        {
            XmlBuilder out = new XmlBuilder();
            out.prolog("1.0", "UTF-8").start(rootElement);
            for (T model : models)
            {
                String modelXml = toXml(model, nestedRootElement);
                if (modelXml != null)
                {
                    out.append(modelXml);
                }
            }
            out.end();
            xml = out.toString();
        }
        return xml;
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
        String xml = null;
        if (model != null && rootElement != null)
        {
            XmlBuilder out = new XmlBuilder();
            out.prolog("1.0", "UTF-8").start(rootElement).append(toPlainXml(model)).end();
            xml = out.toString();
        }
        return xml;
    }


    // --------------------------------------------------------- helper methods

    protected abstract String modelName();


    protected String modelsName()
    {
        return modelName() + "s";
    }
}
