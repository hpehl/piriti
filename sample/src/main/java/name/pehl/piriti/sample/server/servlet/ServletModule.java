package name.pehl.piriti.sample.server.servlet;

/**
 * @author $Author$
 * @version $Revision$
 */
public class ServletModule extends com.google.inject.servlet.ServletModule
{
    @Override
    protected void configureServlets()
    {
        serve("/rest/v1/*").with(PiritiSampleServlet.class);
    }
}
