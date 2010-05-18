package name.pehl.piriti.sample.client.rest;

import java.io.IOException;

import name.pehl.piriti.client.xml.XmlReader;
import name.pehl.piriti.restlet.client.xml.PiritiXmlRepresentation;
import name.pehl.piriti.sample.client.event.EventBus;
import name.pehl.piriti.sample.client.event.PlaygroundEvent;
import name.pehl.piriti.sample.client.model.Playground;
import name.pehl.piriti.sample.client.util.StopWatch;
import name.pehl.piriti.sample.client.util.TimeInterval;

import org.restlet.client.Request;
import org.restlet.client.Response;
import org.restlet.client.Uniform;
import org.restlet.client.data.MediaType;
import org.restlet.client.resource.ClientResource;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-03-25 18:24:50 +0100 (Do, 25 Mrz 2010) $ $Revision: 299
 *          $
 */
public class PlaygroundClient
{
    public static final String URL = "/rest/v1/playground";


    public void readFromXml(final XmlReader<Playground> xmlReader, final String sourceCode)
    {
        ClientResource clientResource = new ClientResource(URL);
        clientResource.setOnResponse(new Uniform()
        {
            @Override
            public void handle(Request request, Response response)
            {
                PlaygroundEvent playgroundEvent = null;
                StopWatch stopWatch = new StopWatch();
                stopWatch.start();
                PiritiXmlRepresentation<Playground> representation = new PiritiXmlRepresentation<Playground>(xmlReader,
                        response.getEntity());
                try
                {
                    Playground playground = representation.getModel();
                    TimeInterval timeInterval = stopWatch.stop();
                    playgroundEvent = new PlaygroundEvent(playground, timeInterval, sourceCode);
                }
                catch (IOException e)
                {
                    playgroundEvent = new PlaygroundEvent(null, null, null);
                }
                EventBus.get().fireEvent(playgroundEvent);
            }
        });
        clientResource.get(MediaType.TEXT_XML);
    }
}
