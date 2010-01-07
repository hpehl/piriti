package name.pehl.gwt.piriti.client.xml;

import java.util.List;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;

/**
 * @author $Author:$
 * @version $Revision:$
 */
public interface XmlReader<T>
{
    T readSingle(Document document);


    T readSingle(Element element);


    List<T> readList(Document document, String xpath);


    List<T> readList(Element element, String xpath);
}
