package name.pehl.piriti.restlet.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.URL;

/**
 * URL Builder with dedicated support for resources. A resource URL is made up
 * of the following parts:<br/>
 * <code>&lt;protocol&gt;://&lt;host&gt;[:&lt;port&gt;]/[&lt;context&gt;][/&lt;module&gt;][/&lt;version&gt;]&lt;resourcePaths&gt;[?&lt;queryParameter&gt;]</code>
 * <p>
 * <table>
 * <tr>
 * <th>Part</th>
 * <th>Description</th>
 * <th>Default</th>
 * <th>Example</th>
 * </tr>
 * <tr>
 * <td>protocol</td>
 * <td>The protocol. If specified, must not be null / empty.</td>
 * <td>The protocol from {@link GWT#getHostPageBaseURL()}</td>
 * <td>http, https</td>
 * </tr>
 * <tr>
 * <td>host</td>
 * <td>The hostname. Can include a port. If specified, must not be null / empty.
 * </td>
 * <td>The host from {@link GWT#getHostPageBaseURL()}</td>
 * <td>127.0.0.1, www.esoves.de:8080</td>
 * </tr>
 * <tr>
 * <td>port</td>
 * <td>The port number. If specified must be something between {@link #MIN_PORT}
 * and {@link #MAX_PORT}</td>
 * <td>The port from {@link GWT#getHostPageBaseURL()}</td>
 * <td>80, 8080</td>
 * </tr>
 * <tr>
 * <td>context</td>
 * <td>The context path. If specified, must not be null / empty. In case the
 * context starts with a '/' there won't be a double '//' in the URL.</td>
 * <td>The context from {@link GWT#getHostPageBaseURL()}</td>
 * <td>foo, /bar, /</td>
 * </tr>
 * <tr>
 * <td>module</td>
 * <td>Some kind of REST module. If you have many resources you might want to
 * subdivide your resources in different modules like 'admin', 'export', etc. If
 * specified, must not be null / empty.</td>
 * <td>nothing</td>
 * <td>admin, common, export</td>
 * </tr>
 * <tr>
 * <td>version</td>
 * <td>In case you eant to include a version identifier in your resources you
 * can specify it with this part. If specified, must not be null / empty.</td>
 * <td>nothing</td>
 * <td>r123, v4.2, 56</td>
 * </tr>
 * <tr>
 * <td>resourcePaths</td>
 * <td>The actual resource paths. At least one path must be provided. In case
 * the context starts with a '/' there won't be a double '//' in the URL.</td>
 * <td>nothing</td>
 * <td>[user, 0815], [/users, 0815, /groups, 12]</td>
 * </tr>
 * <tr>
 * <td>queryParameter</td>
 * <td>Query parameters. You can specify 0-n values for one parameter. If
 * specified, the name must not be null / empty.</td>
 * <td>nothing</td>
 * <td>[foo=[bar], options=[a, b, x]]</td>
 * </tr>
 * </table>
 * <p>
 * Here are some examples of URLs you can build with this class:
 * <ul>
 * <li><code>new UrlBuilder().toUrl()</code> &rarr; http://localhost/
 * <li><code>new UrlBuilder().toUrl()</code> &rarr; http://localhost/
 * <li><code>new UrlBuilder().toUrl()</code> &rarr; http://localhost/
 * <li><code>new UrlBuilder().toUrl()</code> &rarr; http://localhost/
 * </ul>
 * 
 * @author $Author: lfstad-pehl $
 * @version $Date: 2010-02-23 14:59:55 +0100 (Di, 23. Feb 2010) $ $Revision:
 *          77293 $
 */
public class UrlBuilder
{
    // -------------------------------------------------------------- constants

    public static final int PORT_UNSPECIFIED = Integer.MIN_VALUE;
    public static final int MIN_PORT = 0;
    public static final int MAX_PORT = 65535;
    protected static final int URL_MIN_LENGTH = 9;
    protected static final String PROTOCOL_HOSTNAME_SEPARATOR = "://";

    // -------------------------------------------------------- private members

    private String protocol = null;
    private String host = null;
    private int port = PORT_UNSPECIFIED;
    private String context = null;
    private String module;
    private String version = null;
    private List<String> resourcePaths = new ArrayList<String>();
    private Map<String, String[]> queryParams = new HashMap<String, String[]>();


    // ----------------------------------------------------------- constructors

    /**
     * Builds a new URL and assigns the protocol, hostname, port and context
     * from from {@link GWT#getHostPageBaseURL()}.
     */
    public UrlBuilder()
    {
        setDefaultValues(defaultUrl());
    }


    /**
     * @return {@link GWT#getHostPageBaseURL()}
     */
    protected String defaultUrl()
    {
        return GWT.getHostPageBaseURL();
    }


    /**
     * Assigns the protocol, hostname, port and context from the specified url.
     * 
     * @param url
     *            The url which must contain a protocol, a host with an optional
     *            port and an optional context.
     * @throws IllegalArgumentException
     *             if the default values cannot be read from the specified url.
     */
    private void setDefaultValues(String url)
    {
        if (url == null || url.length() < URL_MIN_LENGTH)
        {
            throw new IllegalArgumentException("Cannot get default values from \"" + url + "\": URL is to short.");
        }
        int index = url.indexOf(PROTOCOL_HOSTNAME_SEPARATOR);
        if (index == -1)
        {
            throw new IllegalArgumentException("Cannot get default values from \"" + url
                    + "\": URL does not contain \"" + PROTOCOL_HOSTNAME_SEPARATOR + "\".");
        }
        try
        {
            setProtocol(url.substring(0, index));
            String hostAndContext = url.substring(index + PROTOCOL_HOSTNAME_SEPARATOR.length());
            index = hostAndContext.indexOf('/');
            if (index == -1)
            {
                setHost(hostAndContext);
                setContext("/");
            }
            else
            {
                setHost(hostAndContext.substring(0, index));
                setContext(hostAndContext.substring(index + 1));
                context = hostAndContext.substring(index + 1);
            }
        }
        catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException("Cannot get default values from \"" + url + "\": " + e.getMessage(), e);
        }
    }


    // --------------------------------------------------------- public methods

    /**
     * Build the URL and return it as an encoded string.
     * 
     * @return the encoded URL string
     */
    public final String toUrl()
    {
        assertValid(context, "No context given. Please call setContext() first.");
        if (resourcePaths.isEmpty())
        {
            throw new IllegalStateException("No resource paths given. Please call addResourcePath(String...) first.");
        }
        return internalBuildUrl();
    }


    @Override
    public String toString()
    {
        return internalBuildUrl();
    }


    private String internalBuildUrl()
    {
        // http://
        StringBuilder url = new StringBuilder();
        url.append(protocol).append(PROTOCOL_HOSTNAME_SEPARATOR);

        // http://www.foo.com
        url.append(host);

        // http://www.foo.com:8080
        if (port != PORT_UNSPECIFIED)
        {
            url.append(":").append(port);
        }

        // http://www.foo.com:8080/bar
        url.append(context);

        // http://www.foo.com:8080/bar/baz
        if (module != null)
        {
            url.append("/").append(module);
        }

        // http://www.foo.com:8080/bar/baz/v1
        if (version != null)
        {
            url.append("/").append(version);
        }

        // http://www.foo.com:8080/bar/baz/v1/a/b/c
        for (String path : resourcePaths)
        {
            url.append("/").append(path);
        }

        // http://www.foo.com:8080/bar/baz/v1/a/b/c?k0=v0&k1=v1
        char prefix = '?';
        for (Map.Entry<String, String[]> entry : queryParams.entrySet())
        {
            String[] values = entry.getValue();
            if (values == null || values.length == 0)
            {
                url.append(prefix).append(entry.getKey());
                prefix = '&';
            }
            else
            {
                for (String value : values)
                {
                    url.append(prefix).append(entry.getKey()).append('=');
                    if (value != null)
                    {
                        url.append(value);
                    }
                    prefix = '&';
                }
            }
        }
        return URL.encode(url.toString());
    }


    public UrlBuilder setProtocol(String protocol)
    {
        assertValid(protocol, "Protocol must not be empty.");
        if (protocol.endsWith(PROTOCOL_HOSTNAME_SEPARATOR))
        {
            protocol = protocol.substring(0, protocol.length() - 3);
        }
        else if (protocol.endsWith(":/"))
        {
            protocol = protocol.substring(0, protocol.length() - 2);
        }
        else if (protocol.endsWith(":"))
        {
            protocol = protocol.substring(0, protocol.length() - 1);
        }
        if (protocol.contains(":"))
        {
            throw new IllegalArgumentException("Unknown protocol: " + protocol);
        }
        assertValid(protocol, "Protocol must not be empty.");
        this.protocol = protocol;
        return this;
    }


    public String getProtocol()
    {
        return protocol;
    }


    public UrlBuilder setHost(String host)
    {
        assertValid(host, "Host must not be empty");
        if (host.contains(":"))
        {
            String[] parts = host.split(":");
            if (parts.length > 2)
            {
                throw new IllegalArgumentException("Host contains more than one ':': " + host);
            }
            try
            {
                setPort(Integer.parseInt(parts[1]));
            }
            catch (NumberFormatException e)
            {
                throw new IllegalArgumentException("Port is not a valid number: " + host);
            }
            host = parts[0];
        }
        assertValid(host, "Host must not be empty");
        this.host = host;
        return this;
    }


    public String getHost()
    {
        return host;
    }


    public UrlBuilder setPort(int port)
    {
        if (port < MIN_PORT || port > MAX_PORT)
        {
            throw new IllegalArgumentException("Port is not in range [" + MIN_PORT + ", " + MAX_PORT + "].");
        }
        this.port = port;
        return this;
    }


    public int getPort()
    {
        return port;
    }


    public UrlBuilder setContext(String context)
    {
        assertValid(context, "Context must not be empty");
        if (context.startsWith("/"))
        {
            this.context = context;
        }
        else
        {
            this.context = "/" + context;
        }
        return this;
    }


    public String getContext()
    {
        return context;
    }


    public UrlBuilder setModule(String module)
    {
        assertValid(module, "Module must not be empty");
        if (module.startsWith("/"))
        {
            module = module.substring(1);
        }
        assertValid(module, "Module must not be empty");
        this.module = module;
        return this;
    }


    public String getModule()
    {
        return module;
    }


    public UrlBuilder setVersion(String version)
    {
        assertValid(version, "Version must not be empty");
        if (version.startsWith("/"))
        {
            version = version.substring(1);
        }
        assertValid(version, "Version must not be empty");
        this.version = version;
        return this;
    }


    public String getVersion()
    {
        return version;
    }


    public UrlBuilder addResourcePath(String... paths)
    {
        assertNotEmpty(paths, "Paths must not be empty");
        for (String path : paths)
        {
            if (path != null && path.startsWith("/"))
            {
                path = path.substring(1);
            }
            resourcePaths.add(path);
        }
        return this;
    }


    public UrlBuilder clearResourcePaths()
    {
        resourcePaths.clear();
        return this;
    }


    public List<String> getResourcePaths()
    {
        return resourcePaths;
    }


    public UrlBuilder addQueryParameter(String key, String... values)
    {
        assertValid(key, "Key must not be empty.");
        queryParams.put(key, values);
        return this;
    }


    public Map<String, String[]> getQueryParams()
    {
        return queryParams;
    }


    // --------------------------------------------------------- helper methods

    private void assertValid(String value, String message) throws IllegalArgumentException
    {
        if (value == null || value.length() == 0)
        {
            throw new IllegalArgumentException(message);
        }
    }


    private void assertNotEmpty(Object[] values, String message) throws IllegalArgumentException
    {
        if (values == null)
        {
            throw new IllegalArgumentException(message);
        }
        if (values.length == 0)
        {
            throw new IllegalArgumentException(message);
        }
    }
}
