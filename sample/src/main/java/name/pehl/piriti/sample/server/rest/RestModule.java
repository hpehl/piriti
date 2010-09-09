package name.pehl.piriti.sample.server.rest;

import com.google.inject.AbstractModule;

/**
 * @author $Author$
 * @version $Revision$
 */
public class RestModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(BooksResource.class);
    }
}
