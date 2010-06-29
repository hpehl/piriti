package name.pehl.piriti.client.gwttest;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public abstract class AbstractPiritiTest extends GWTTestCase
{
    @Override
    public String getModuleName()
    {
        return "name.pehl.piriti.PiritiTest";
    }


    @Override
    protected void gwtSetUp() throws Exception
    {
        System.out.println("Running " + getClass().getName());
    }
}
