package name.pehl.piriti.sample.server.rest;

import name.pehl.taoki.rest.GuiceRouter;

import org.restlet.Context;

import com.google.inject.Injector;

/**
 * @author $Author$
 * @version $Revision$
 */
public class PiritiSampleRouter extends GuiceRouter
{
    public PiritiSampleRouter(Injector injector, Context context)
    {
        super(injector, context);
    }


    @Override
    protected void attachRoutes()
    {
        // TODO attach resources
        attach("/books", BooksResource.class);
    }
}
