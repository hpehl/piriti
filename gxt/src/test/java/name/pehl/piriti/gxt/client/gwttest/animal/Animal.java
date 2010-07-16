package name.pehl.piriti.gxt.client.gwttest.animal;

import name.pehl.piriti.gxt.client.json.JsonField;
import name.pehl.piriti.gxt.client.json.JsonFields;
import name.pehl.piriti.gxt.client.xml.XmlField;
import name.pehl.piriti.gxt.client.xml.XmlFields;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * @author $Author$
 * @version $Date$ $Revision$
 */
@JsonFields({@JsonField(name = "name", type = String.class), @JsonField(name = "legs", type = Integer.class),
        @JsonField(name = "intelligence", type = Double.class)})
@XmlFields({@XmlField(name = "name", type = String.class), @XmlField(name = "legs", type = Integer.class),
        @XmlField(name = "intelligence", type = Double.class)})
public abstract class Animal extends BaseModel
{
}
