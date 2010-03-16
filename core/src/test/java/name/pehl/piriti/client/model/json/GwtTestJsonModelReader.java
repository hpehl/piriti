package name.pehl.piriti.client.model.json;

import name.pehl.piriti.client.model.Model;
import name.pehl.piriti.client.model.ModelTestCase;

/**
 * @author $Author$
 * @version $Revision$
 */
public class GwtTestJsonModelReader extends ModelTestCase
{
    public void testRead()
    {
        String json = JsonModelFactory.createModel();
        Model model = Model.JSON.read(json);
        // Not yet implemented
        // assertModel(model);
    }
}
