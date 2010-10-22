package name.pehl.piriti.client.gwttest.ticketgoose;

import name.pehl.piriti.client.xml.Xml;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author$
 * @version $Date$ $Revision: 855
 *          $
 */
// @formatter:off
public class Station
{
    public interface StationReader extends XmlReader<Station> {}
    public static final StationReader XML = GWT.create(StationReader.class);

    @Xml int id;
    @Xml String name;
}
