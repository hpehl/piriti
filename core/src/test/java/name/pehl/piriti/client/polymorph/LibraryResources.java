package name.pehl.piriti.client.polymorph;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */

public interface LibraryResources extends ClientBundle
{
    LibraryResources INSTANCE = GWT.create(LibraryResources.class);


    @Source("library.json")
    TextResource libraryJson();
}
