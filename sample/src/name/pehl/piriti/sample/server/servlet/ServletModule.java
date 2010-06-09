package name.pehl.piriti.sample.server.servlet;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 520 $
 */
public class ServletModule extends com.google.inject.servlet.ServletModule
{
    @Override
    protected void configureServlets()
    {
        serve("/rest/v1/*").with(PiritiSampleServlet.class);
    }
}
