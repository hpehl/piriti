package name.pehl.piriti.client.option;

import com.google.gwt.core.client.GWT;
import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.xml.client.XmlReader;

/**
 * @author Harald Pehl
 */
public class Option {

    public static interface OptionReader extends XmlReader<Option> { }
    public static final OptionReader READER = GWT.create(OptionReader.class);

    @Path("@id") private String id;
    @Path("text()") private String name;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
