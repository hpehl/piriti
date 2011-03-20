package name.pehl.piriti.restlet.xml.client;

import java.io.IOException;
import java.util.List;

import name.pehl.piriti.restlet.client.PiritiRepresentation;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.totoe.xml.client.Document;
import name.pehl.totoe.xml.client.XmlParser;

import org.restlet.client.data.MediaType;
import org.restlet.client.ext.xml.DomRepresentation;
import org.restlet.client.representation.Representation;

/**
 * Representation which uses an {@link XmlReader} for converting XML to an
 * instance of T.
 * <p>
 * TODO Namespace support!
 * 
 * @param <T>
 *            The model type
 * @author $Author$
 * @version $Date$ $Revision: 264
 *          $
 */
public class PiritiXmlRepresentation<T> extends DomRepresentation implements PiritiRepresentation<T>
{
    /** The XmlReader for converting the XML to an instance of T. */
    private final XmlReader<T> xmlReader;

    /** The wrapped document instance. */
    private Document document;

    /** The source XML representation. */
    private Representation xmlRepresentation;


    // ----------------------------------------------------------- constructors

    /**
     * Constructor for an empty document.
     * 
     * @param xmlReader
     *            The XmlReader for converting the XML to an instance of T.
     * @param mediaType
     *            The representation's media type.
     */
    public PiritiXmlRepresentation(XmlReader<T> xmlReader, MediaType mediaType)
    {
        super(mediaType);
        this.xmlReader = xmlReader;
    }


    /**
     * Constructor from an existing DOM document.
     * 
     * @param xmlReader
     *            The XmlReader for converting the XML to an instance of T.
     * @param mediaType
     *            The representation's media type.
     * @param document
     *            The source DOM document.
     */
    public PiritiXmlRepresentation(XmlReader<T> xmlReader, MediaType mediaType, Document document)
    {
        super(mediaType);
        this.xmlReader = xmlReader;
        this.document = document;
    }


    /**
     * Constructor.
     * 
     * @param xmlReader
     *            The XmlReader for converting the XML to an instance of T.
     * @param xmlRepresentation
     *            A source XML representation to parse.
     */
    public PiritiXmlRepresentation(XmlReader<T> xmlReader, Representation xmlRepresentation)
    {
        super(xmlRepresentation);
        this.xmlReader = xmlReader;
        this.xmlRepresentation = xmlRepresentation;
    }


    // --------------------------------------------------------- public methods

    /**
     * @return the XmlReader for converting the XML to an instance of T.
     */
    public XmlReader<T> getXmlReader()
    {
        return xmlReader;
    }


    /**
     * Converts the XML to an instance of T using the {@link XmlReader} given as
     * constructor argument. Returns null if {@link #getDocument()} or
     * {@link XmlReader} is null.
     * 
     * @return the converted instance of T or null if {@link #getDocument()} or
     *         {@link XmlReader} is null.
     * @throws IOException
     */
    @Override
    public T getModel() throws IOException
    {
        T model = null;
        Document document = lazyGetDocument();
        if (document != null)
        {
            model = xmlReader.read(document);
        }
        return model;
    }


    /**
     * Converts the XML to a list of Ts using the {@link XmlReader} given as
     * constructor argument. Returns null if {@link #getDocument()} or
     * {@link XmlReader} is null. More precisely the child elements of the
     * documents root element are converted to instances of T.
     * 
     * @return the list of converted Ts or null if {@link #getDocument()} or
     *         {@link XmlReader} is null.
     * @throws IOException
     */
    @Override
    public List<T> getModels() throws IOException
    {
        List<T> models = null;
        Document document = lazyGetDocument();
        if (document != null)
        {
            models = xmlReader.readList(document);
        }
        return models;
    }


    /**
     * Returns the wrapped document instance. If no document is defined yet, it
     * attempts to parse the XML representation eventually given at construction
     * time. Otherwise, it returns <code>null</code>.
     * 
     * @return The wrapped document instance.
     * @throws IOException
     */
    protected Document lazyGetDocument() throws IOException
    {
        if (document == null)
        {
            if (xmlRepresentation != null)
            {
                document = new XmlParser().parse(xmlRepresentation.getText());
            }
        }
        return document;
    }


    /**
     * Returns always <code>null</code>.
     * 
     * @return <code>null</code>
     * @throws IOException
     * @see org.restlet.client.ext.xml.DomRepresentation#getDocument()
     */
    @Override
    public com.google.gwt.xml.client.Document getDocument() throws IOException
    {
        return null;
    }
}
