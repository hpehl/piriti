package name.pehl.piriti.client.polymorph;

import name.pehl.piriti.xml.client.XmlInstanceCreator;
import name.pehl.totoe.xml.client.Node;

public class XmlMediumCreator extends XmlInstanceCreator<Medium>
{
    @Override
    public Medium newInstance(Node context)
    {
        Medium medium = null;
        String id = context.selectValue("id");
        if (id != null)
        {
            if (id.startsWith("isbn-"))
            {
                medium = new Book();
            }
            else if (id.startsWith("cd-"))
            {
                medium = new Cd();
            }
            if (id.startsWith("dvd-"))
            {
                medium = new Dvd();
            }
        }
        return medium;
    }
}
