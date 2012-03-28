package name.pehl.piriti.client.sabajtouch;

import java.util.List;

import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.xml.client.XmlReader;

import com.google.gwt.core.client.GWT;

public class Registry
{
    interface RegistryReader extends XmlReader<Registry>
    {
    }

    public static final RegistryReader XML = GWT.create(RegistryReader.class);

    @Path("Field")
    List<Field> fields;


    public void print()
    {
        for (Field f : fields)
        {
            GWT.log("" + f);
        }
    }
}
