package name.pehl.piriti.client.polymorph;

import java.util.List;

import name.pehl.piriti.commons.client.Mapping;
import name.pehl.piriti.commons.client.Mappings;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2011-02-22 22:55:05 +0100 (Di, 22. Feb 2011) $ $Revision:
 *          1454 $
 */
//@formatter:off
public class Library
{
    @Mappings({@Mapping(value = "mediumsArray", createWith = JsonMediumCreator.class), 
        @Mapping(value = "mediumsList", createWith = JsonMediumCreator.class)})    
    public interface JsonLibraryReader extends JsonReader<Library> {}
    public static final JsonLibraryReader JSON_READER = GWT.create(JsonLibraryReader.class);
    
    public interface JsonLibraryWriter extends JsonWriter<Library> {}
    public static final JsonLibraryWriter JSON_WRITER = GWT.create(JsonLibraryWriter.class);

    @Mappings({@Mapping(value = "mediumsArray", path = "mediumsArray/medium", createWith = XmlMediumCreator.class), 
        @Mapping(value = "mediumsList", path = "mediumsList/medium", createWith = XmlMediumCreator.class)})    
    public interface XmlLibraryReader extends XmlReader<Library> {}
    public static final XmlLibraryReader XML_READER = GWT.create(XmlLibraryReader.class);
    
    @Mappings({@Mapping(value = "mediumsArray", path = "mediumsArray/medium"), 
        @Mapping(value = "mediumsList", path = "mediumsList/medium")})    
    public interface XmlLibraryWriter extends XmlWriter<Library> {}
    public static final XmlLibraryWriter XML_WRITER = GWT.create(XmlLibraryWriter.class);

    Medium[] mediumsArray;
    List<Medium> mediumsList;
}
