package name.pehl.piriti.sample.server.servlet;

import name.pehl.piriti.sample.server.rest.RestModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * @author $Author:$
 * @version $Revision:$
 */
public class ServletConfig extends GuiceServletContextListener
{
    @Override
    protected Injector getInjector()
    {
        return Guice.createInjector(new ServletModule(), new RestModule());
    }
}
