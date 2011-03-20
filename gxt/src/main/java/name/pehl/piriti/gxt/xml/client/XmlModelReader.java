package name.pehl.piriti.gxt.xml.client;

import name.pehl.piriti.xml.client.XmlReader;

import com.extjs.gxt.ui.client.data.ModelData;

/**
 * Interface for converting XML document / element to {@linkplain ModelData GXT
 * models}.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public interface XmlModelReader<T extends ModelData> extends XmlReader<T>
{
}
