package name.pehl.piriti.gxt.client.gwttest.animal;

import name.pehl.piriti.gxt.client.json.JsonField;
import name.pehl.piriti.gxt.client.json.JsonModel;
import name.pehl.piriti.gxt.client.xml.XmlField;
import name.pehl.piriti.gxt.client.xml.XmlModel;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
@JsonModel({@JsonField(property = "name", type = String.class), @JsonField(property = "legs", type = Integer.class),
        @JsonField(property = "intelligence", type = Double.class)})
@XmlModel({@XmlField(property = "name", type = String.class), @XmlField(property = "legs", type = Integer.class),
        @XmlField(property = "intelligence", type = Double.class)})
public abstract class Animal extends BaseModel
{
}
