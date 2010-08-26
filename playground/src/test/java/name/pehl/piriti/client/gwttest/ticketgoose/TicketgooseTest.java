package name.pehl.piriti.client.gwttest.ticketgoose;

import java.util.List;

import name.pehl.piriti.client.gwttest.AbstractPlaygroundTest;
import name.pehl.totoe.client.Document;
import name.pehl.totoe.client.XmlParser;

/**
 * @author $Author$
 * @version $Revision$
 */
public class TicketgooseTest extends AbstractPlaygroundTest
{
    static final int SIZE = 3;


    public void testReadStations()
    {
        String xml = TicketgooseResources.INSTANCE.ticketgooseXml().getText();
        Document doc = new XmlParser().parse(xml);
        List<Station> stations = Station.XML.readList(doc);
        assertStations(stations);
    }


    public void testReadStationDetails()
    {
        String xml = TicketgooseResources.INSTANCE.ticketgooseXml().getText();
        Document doc = new XmlParser().parse(xml);
        StationDetails stationDetails = StationDetails.XML.read(doc);
        assertNotNull(stationDetails);
        assertStations(stationDetails.listOfStations);
    }


    private void assertStations(List<Station> stations)
    {
        assertNotNull(stations);
        assertEquals(SIZE, stations.size());
        for (int i = 0; i < SIZE; i++)
        {
            Station station = stations.get(i);
            assertNotNull(station);
            assertEquals(12, station.id);
            assertEquals("asd", station.name);
        }
    }
}
