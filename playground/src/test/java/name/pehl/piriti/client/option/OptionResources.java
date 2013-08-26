package name.pehl.piriti.client.option;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author Harald Pehl
 */
public interface OptionResources extends ClientBundle {

    OptionResources INSTANCE = GWT.create(OptionResources.class);

    @ClientBundle.Source("options.xml")
    public TextResource optionsXml();
}
