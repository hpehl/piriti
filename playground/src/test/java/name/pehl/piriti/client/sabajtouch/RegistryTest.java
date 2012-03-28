package name.pehl.piriti.client.sabajtouch;

import name.pehl.piriti.client.AbstractPlaygroundTest;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 1305 $
 */
public class RegistryTest extends AbstractPlaygroundTest
{
    public void testRegistry()
    {
        String xml = RegistryResources.INSTANCE.registryJson().getText();
        Registry registry = Registry.XML.read(xml);
        assertNotNull(registry);
    }
}
