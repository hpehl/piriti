package name.pehl.piriti.client.ticketgoose;

import java.util.List;

import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.xml.client.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author$
 * @version $Date$ $Revision: 855
 *          $
 */
//@formatter:off
public class StationDetails
{
    public interface StationDetailsReader extends XmlReader<StationDetails> {}
    public static final StationDetailsReader XML = GWT.create(StationDetailsReader.class);

    @Path("//Station") List<Station> listOfStations;
}
