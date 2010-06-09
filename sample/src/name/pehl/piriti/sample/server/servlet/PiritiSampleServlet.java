package name.pehl.piriti.sample.server.servlet;

import name.pehl.piriti.sample.server.rest.PiritiSampleRouter;
import name.pehl.taoki.rest.GuiceRouter;
import name.pehl.taoki.rest.RestletServlet;

import org.restlet.Context;

import com.google.inject.Injector;
import com.google.inject.Singleton;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-05-18 23:30:44 +0200 (Di, 18 Mai 2010) $ $Revision: 165
 *          $
 */
@Singleton
public class PiritiSampleServlet extends RestletServlet
{
    /**
     * @param injector
     * @param context
     * @return
     * @see name.pehl.taoki.rest.RestletServlet#createRouter(com.google.inject.Injector,
     *      org.restlet.Context)
     */
    @Override
    protected GuiceRouter createRouter(Injector injector, Context context)
    {
        return new PiritiSampleRouter(injector, context);
    }
}
