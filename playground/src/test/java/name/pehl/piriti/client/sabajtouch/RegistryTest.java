package name.pehl.piriti.client.sabajtouch;

import name.pehl.piriti.client.AbstractPlaygroundTest;

/**
 * Testcase for https://groups.google.com/d/topic/piriti/DarrebOXP7A/discussion
 * 
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
        assertNotNull(registry.fields);
        assertEquals(3, registry.fields.size());
    }
}
