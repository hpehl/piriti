package name.pehl.piriti.gxt.client.inheritance;

import name.pehl.piriti.gxt.json.client.Json;
import name.pehl.piriti.gxt.json.client.JsonMappings;
import name.pehl.piriti.gxt.xml.client.Xml;
import name.pehl.piriti.gxt.xml.client.XmlMappings;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * @author $Author$
 * @version $Date$ $Revision$
 */
//@formatter:off
@JsonMappings({
    @Json(property = "name", type = String.class), 
    @Json(property = "legs", type = Integer.class),
    @Json(property = "intelligence", type = Double.class)})
@XmlMappings({
    @Xml(property = "name", type = String.class), 
    @Xml(property = "legs", type = Integer.class),
    @Xml(property = "intelligence", type = Double.class)})
// @formatter:on
public abstract class Animal extends BaseModel
{
}
