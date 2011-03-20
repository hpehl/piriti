package name.pehl.piriti.client.campaign;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 1305 $
 */

public interface CampaignResources extends ClientBundle
{
    CampaignResources INSTANCE = GWT.create(CampaignResources.class);


    @Source("campaigns.json")
    public TextResource campaignsJson();
}
