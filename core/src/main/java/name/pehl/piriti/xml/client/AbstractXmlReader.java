package name.pehl.piriti.xml.client;

import static java.util.logging.Level.FINE;

import java.util.ArrayList;
import java.util.List;

import name.pehl.piriti.commons.client.AbstractReader;
import name.pehl.piriti.commons.client.InstanceContextHolder;
import name.pehl.piriti.commons.client.ModelReadEvent;
import name.pehl.totoe.xml.client.Document;
import name.pehl.totoe.xml.client.Element;
import name.pehl.totoe.xml.client.Node;
import name.pehl.totoe.xml.client.XmlParseException;
import name.pehl.totoe.xml.client.XmlParser;

/**
 * Base class for generated XmlReaders. Contains common code and methods.
 * 
 * @param <T>
 *            The type
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public abstract class AbstractXmlReader<T> extends AbstractReader<T, Element> implements XmlReader<T>
{
    // ----------------------------------------------------------------- fields

    protected final XmlRegistry xmlRegistry;


    // ----------------------------------------------------------- constructors

    protected AbstractXmlReader()
    {
        super();
        this.xmlRegistry = XmlGinjector.INJECTOR.getXmlRegistry();
    }


    // ------------------------------------------------------ read list methods

    @Override
    public List<T> readList(String xml) throws XmlParseException
    {
        return readList(new XmlParser().parse(xml));
    }


    @Override
    public List<T> readList(Document document)
    {
        if (document == null || document.getRoot() == null)
        {
            return null;
        }
        return internalReadList(filterElements(document.getRoot().getChildren()));
    }


    @Override
    public List<T> readList(Document document, String xpath)
    {
        if (document == null)
        {
            return null;
        }
        return internalReadList(filterElements(document.selectNodes(xpath)));
    }


    @Override
    public List<T> readList(Element element)
    {
        if (element == null)
        {
            return null;
        }
        return internalReadList(filterElements(element.getChildren()));
    }


    @Override
    public List<T> readList(Element element, String xpath)
    {
        if (element == null)
        {
            return null;
        }
        return internalReadList(filterElements(element.selectNodes(xpath)));
    }


    protected List<T> internalReadList(List<Element> elements)
    {
        List<T> models = null;
        List<InstanceContextHolder<T, Element>> instanceContextHolders = null;

        if (!elements.isEmpty())
        {
            models = new ArrayList<T>();
            instanceContextHolders = new ArrayList<InstanceContextHolder<T, Element>>();

            if (logger.isLoggable(FINE))
            {
                logger.log(FINE, "First iteration over JSON array to create models and process IDs");
            }
            for (Element element : elements)
            {
                T model = readId(element);
                if (model != null)
                {
                    models.add(model);
                    instanceContextHolders.add(new InstanceContextHolder<T, Element>(model, element));
                }
            }

            if (logger.isLoggable(FINE))
            {
                logger.log(FINE, "Second iteration over generated models to map properties and IDREFs");
            }
            for (InstanceContextHolder<T, Element> ich : instanceContextHolders)
            {
                T model = ich.getInstance();
                readProperties(ich.getContext(), model);
                readIdRefs(ich.getContext(), model);
                ModelReadEvent.fire(this, model);
            }
        }
        return models;
    }


    // ---------------------------------------------------- read single methods

    @Override
    public T read(String xml) throws XmlParseException
    {
        return read(new XmlParser().parse(xml));
    }


    @Override
    public T read(Document document)
    {
        if (document == null)
        {
            return null;
        }
        return read(document.getRoot());
    }


    @Override
    public T read(Element element)
    {
        if (element == null)
        {
            return null;
        }
        T model = readId(element);
        readProperties(element, model);
        readIdRefs(element, model);
        ModelReadEvent.fire(this, model);
        return model;
    }


    // ----------------------------------------- ids, properties and references

    @Override
    public T idRef(String id)
    {
        return idMap.get(id);
    }


    protected T readId(Element element)
    {
        // TODO Implement IDs and IDREFs
        return newModel();
    }


    protected abstract T readProperties(Element element, T model);


    protected T readIdRefs(Element element, T model)
    {
        if (element != null)
        {
            // TODO Implement IDs and IDREFs
        }
        return model;
    }


    // --------------------------------------------------------- helper methods

    protected List<Element> filterElements(List<Node> nodes)
    {
        List<Element> elements = new ArrayList<Element>();
        for (Node node : nodes)
        {
            if (node instanceof Element)
            {
                elements.add((Element) node);
            }
        }
        return elements;
    }
}
