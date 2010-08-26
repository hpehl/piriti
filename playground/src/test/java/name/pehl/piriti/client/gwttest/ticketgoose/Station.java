package name.pehl.piriti.client.gwttest.ticketgoose;

import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class Station
{
    public interface StationReader extends XmlReader<Station>
    {
    }

    public static final StationReader XML = GWT.create(StationReader.class);

    @XmlField
    int id;

    @XmlField
    String name;
}
