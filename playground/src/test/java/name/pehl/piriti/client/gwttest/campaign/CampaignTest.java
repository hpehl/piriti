package name.pehl.piriti.client.gwttest.campaign;

import java.util.List;

import name.pehl.piriti.client.gwttest.AbstractPlaygroundTest;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 1305 $
 */
public class CampaignTest extends AbstractPlaygroundTest
{
    public void testCampaigns()
    {
        String json = CampaignResources.INSTANCE.campaignsJson().getText();
        List<Campaign> campaigns = Campaign.READER.readList(json);
        assertNotNull(campaigns);
        assertEquals(4, campaigns.size());
    }
}
