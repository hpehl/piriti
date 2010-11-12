package name.pehl.piriti.restlet.client;

/**
 * Standalone {@link UrlBuilder} with no dependencies to GWT.
 * 
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class StandaloneUrlBuilder extends UrlBuilder
{
    static final String PROTOCOL = "http";
    static final String HOST = "www.acme.com";
    static final String DEFAULT_URL = PROTOCOL + PROTOCOL_HOSTNAME_SEPARATOR + HOST;


    /**
     * @return {@value #DEFAULT_URL}
     * @see name.pehl.piriti.restlet.client.UrlBuilder#defaultUrl()
     */
    @Override
    protected String defaultUrl()
    {
        return DEFAULT_URL;
    }
}
