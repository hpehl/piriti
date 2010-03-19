package name.pehl.piriti.gxt.client.json;

import name.pehl.piriti.client.json.JsonReader;

import com.extjs.gxt.ui.client.data.ModelData;

/**
 * Interface for converting JSON data to {@linkplain ModelData GXT models}.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public interface JsonModelReader<T extends ModelData> extends JsonReader<T>
{
}
