package name.pehl.piriti.client.constraints;

import static name.pehl.piriti.client.constraints.AmoebaResources.*;

import java.util.List;

import name.pehl.piriti.client.AbstractPiritiTest;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-06-29 17:03:23 +0200 (Di, 29. Jun 2010) $ $Revision: 295
 *          $
 */
public abstract class AbstractAmoebaTest extends AbstractPiritiTest
{
    protected void assertAmoebas(List<Amoeba> amoebas)
    {
        assertNotNull(amoebas);
        assertEquals(SIZE, amoebas.size());
        assertAmoeba(amoebas.get(0), ALPHA);
        assertAmoeba(amoebas.get(1), BRAVO);
        assertAmoeba(amoebas.get(2), CHARLIE);
    }


    protected void assertAmoeba(Amoeba amoeba, String name)
    {
        assertNotNull(amoeba);
        assertEquals(name, amoeba.getName());
        assertEquals(1, amoeba.getSize());
    }
}
