package name.pehl.piriti.sample.server.rest;

import com.google.inject.AbstractModule;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 273 $
 */
public class RestModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(BooksResource.class);
    }
}
