package name.pehl.piriti.client.polymorph;

import java.util.List;

import name.pehl.piriti.json.client.JsonReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2011-02-22 22:55:05 +0100 (Di, 22. Feb 2011) $ $Revision:
 *          1454 $
 */
//@formatter:off
public class Library
{
    public interface LibraryReader extends JsonReader<Library> {}
    public static final LibraryReader READER = GWT.create(LibraryReader.class);
    
    List<Medium> mediums;
}
