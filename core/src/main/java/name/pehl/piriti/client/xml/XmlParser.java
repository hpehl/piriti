package name.pehl.piriti.client.xml;

import java.util.Map;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public interface XmlParser
{
    Node parse(String xml);


    Node parse(String xml, Map<String, String> namespaces);
}
