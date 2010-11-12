package name.pehl.piriti.restlet.client;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author $Author: lfstad-pehl $
 * @version $Date: 2009-12-08 17:33:09 +0100 (Di, 08. Dez 2009) $ $Revision:
 *          77293 $
 */
public class UrlBuilderTest
{
    // -------------------------------------------.---------- default url tests

    @Test(expected = IllegalArgumentException.class)
    public void testNullDefaultUrl()
    {
        new UrlBuilder()
        {
            @Override
            protected String defaultUrl()
            {
                return null;
            }
        };
    }


    @Test(expected = IllegalArgumentException.class)
    public void testEmptyDefaultUrl()
    {
        new UrlBuilder()
        {
            @Override
            protected String defaultUrl()
            {
                return "";
            }
        };
    }


    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDefaultUrl()
    {
        new UrlBuilder()
        {
            @Override
            protected String defaultUrl()
            {
                return "h://w";
            }
        };
    }


    // ----------------------------------------------.------ new instance tests

    @Test(expected = IllegalStateException.class)
    public void testBuildUrlWithNoData()
    {
        new StandaloneUrlBuilder().toUrl();
    }


    // ----------------------------------------------------------- schema tests

    @Test
    public void testSetProtocol()
    {
        UrlBuilder underTest = new StandaloneUrlBuilder().setProtocol("http");
        assertEquals("http", underTest.getProtocol());

        underTest.setProtocol("http://");
        assertEquals("http", underTest.getProtocol());

        underTest.setProtocol("http:/");
        assertEquals("http", underTest.getProtocol());

        underTest.setProtocol("http:");
        assertEquals("http", underTest.getProtocol());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetNullProtocol()
    {
        new StandaloneUrlBuilder().setProtocol(null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetEmptyProtocol()
    {
        new StandaloneUrlBuilder().setProtocol("");
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidProtocol()
    {
        new StandaloneUrlBuilder().setProtocol("so:nicht");
    }


    // ------------------------------------------------------------- host tests

    @Test
    public void testSetHost()
    {
        UrlBuilder underTest = new StandaloneUrlBuilder().setHost("www.foo.com");
        assertEquals("www.foo.com", underTest.getHost());

        underTest.setHost("www.foo.com:8080");
        assertEquals("www.foo.com", underTest.getHost());
        assertEquals(8080, underTest.getPort());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetNullHost()
    {
        new StandaloneUrlBuilder().setHost(null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetEmptyHost()
    {
        new StandaloneUrlBuilder().setHost("");
    }


    // ------------------------------------------------------------- port tests

    @Test
    public void testSetPort()
    {
        UrlBuilder underTest = new StandaloneUrlBuilder();
        assertEquals(UrlBuilder.PORT_UNSPECIFIED, underTest.getPort());

        underTest.setPort(0);
        assertEquals(0, underTest.getPort());

        underTest.setPort(1234);
        assertEquals(1234, underTest.getPort());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidPortMin()
    {
        new StandaloneUrlBuilder().setPort(-12);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidPortMax()
    {
        new StandaloneUrlBuilder().setPort(UrlBuilder.MAX_PORT + 1);
    }


    // ---------------------------------------------------------- context tests

    @Test
    public void testSetContext()
    {
        UrlBuilder underTest = new StandaloneUrlBuilder();
        assertEquals("/", underTest.getContext());

        underTest.setContext("/");
        assertEquals("/", underTest.getContext());

        underTest.setContext("foo");
        assertEquals("/foo", underTest.getContext());

        underTest.setContext("/foo");
        assertEquals("/foo", underTest.getContext());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetNullContext()
    {
        new StandaloneUrlBuilder().setContext(null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetEmptyContext()
    {
        new StandaloneUrlBuilder().setContext("");
    }


    // ----------------------------------------------------------- module tests

    @Test
    public void testSetModule()
    {
        UrlBuilder underTest = new StandaloneUrlBuilder();
        assertNull(underTest.getModule());

        underTest.setModule("foo");
        assertEquals("foo", underTest.getModule());

        underTest.setModule("/foo");
        assertEquals("foo", underTest.getModule());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetNullModule()
    {
        new StandaloneUrlBuilder().setModule(null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetEmptyModule()
    {
        new StandaloneUrlBuilder().setModule("");
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetSlashModule()
    {
        new StandaloneUrlBuilder().setModule("/");
    }


    // ---------------------------------------------------------- version tests

    @Test
    public void testSetVersion()
    {
        UrlBuilder underTest = new StandaloneUrlBuilder();
        assertNull(underTest.getVersion());

        underTest.setVersion("v1");
        assertEquals("v1", underTest.getVersion());

        underTest.setVersion("/v1");
        assertEquals("v1", underTest.getVersion());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetNullVersion()
    {
        new StandaloneUrlBuilder().setVersion(null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetEmptyVersion()
    {
        new StandaloneUrlBuilder().setVersion("");
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSetSlashVersion()
    {
        new StandaloneUrlBuilder().setVersion("/");
    }


    // --------------------------------------------------------- resource paths

    @Test
    public void testAddResourcePath()
    {
        UrlBuilder underTest = new StandaloneUrlBuilder();
        assertTrue(underTest.getResourcePaths().isEmpty());

        underTest.addResourcePath("eins", "/", null, "/zwei");
        assertEquals(4, underTest.getResourcePaths().size());
        assertEquals("eins", underTest.getResourcePaths().get(0));
        assertEquals("", underTest.getResourcePaths().get(1));
        assertNull(underTest.getResourcePaths().get(2));
        assertEquals("zwei", underTest.getResourcePaths().get(3));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddNullResourcePath()
    {
        new StandaloneUrlBuilder().addResourcePath((String[]) null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddEmptyResourcePath()
    {
        new StandaloneUrlBuilder().addResourcePath(new String[0]);
    }


    @Test
    public void testClearResourcePaths()
    {
        UrlBuilder underTest = new StandaloneUrlBuilder().addResourcePath("1", "2", "3", "viele");
        assertEquals(4, underTest.getResourcePaths().size());
        underTest.clearResourcePaths();
        assertTrue(underTest.getResourcePaths().isEmpty());
    }


    // -------------------------------------------------- query parameter tests

    @Test
    public void testAddQueryParameter()
    {
        UrlBuilder underTest = new StandaloneUrlBuilder();
        assertTrue(underTest.getQueryParams().isEmpty());

        underTest.addQueryParameter("nachname", "pehl").addQueryParameter("vorname", "harald", "willi");
        assertEquals(2, underTest.getQueryParams().size());
        assertArrayEquals(new String[] {"pehl"}, underTest.getQueryParams().get("nachname"));
        assertArrayEquals(new String[] {"harald", "willi"}, underTest.getQueryParams().get("vorname"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testAddQueryParameterWithNullKey()
    {
        new StandaloneUrlBuilder().addQueryParameter(null, "egal");
    }
}
