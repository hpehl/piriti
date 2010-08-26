package name.pehl.piriti.client.gwttest.ticketgoose;

import java.util.List;

import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author$
 * @version $Date$ $Revision: 855
 *          $
 */
public class StationDetails
{
    public interface StationDetailsReader extends XmlReader<StationDetails>
    {
    }

    public static final StationDetailsReader XML = GWT.create(StationDetailsReader.class);

    @XmlField("//Station")
    List<Station> listOfStations;
}
