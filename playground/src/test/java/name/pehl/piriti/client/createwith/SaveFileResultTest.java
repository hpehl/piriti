package name.pehl.piriti.client.createwith;

import name.pehl.piriti.client.AbstractPlaygroundTest;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author: harald.pehl $
 * @version $Revision: 1478 $
 */
public class SaveFileResultTest extends AbstractPlaygroundTest
{
    public void testReadBlogItemsFromDocument()
    {
        String json = SaveFileResultResources.INSTANCE.saveFileResultJson().getText();
        SaveFileResultJsonReader reader = GWT.create(SaveFileResultJsonReader.class);
        SaveFileResult saveFileResult = reader.read(json);
        assertNotNull(saveFileResult);
        assertNull(saveFileResult.file);
    }
}
