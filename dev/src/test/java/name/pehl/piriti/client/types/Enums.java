package name.pehl.piriti.client.types;

import java.util.List;
import java.util.Set;

import name.pehl.piriti.commons.client.Mapping;
import name.pehl.piriti.commons.client.Mappings;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.client.GWT;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
// @formatter:off
public class Enums
{
    public interface EnumsJsonReader extends JsonReader<Enums> {}
    public static final EnumsJsonReader JSON_READER = GWT.create(EnumsJsonReader.class);

    public interface EnumsJsonWriter extends JsonWriter<Enums> {}
    public static final EnumsJsonWriter JSON_WRITER = GWT.create(EnumsJsonWriter.class);


    @Mappings({@Mapping(value = "white", path = "white/color"), 
        @Mapping(value = "threeTimesRed", path = "threeTimesRed/color")})
    public interface EnumsXmlReader extends XmlReader<Enums> {}
    public static final EnumsXmlReader XML_READER = GWT.create(EnumsXmlReader.class);

    @Mappings({@Mapping(value = "white", path = "white/color"), 
        @Mapping(value = "threeTimesRed", path = "threeTimesRed/color")})
    public interface EnumsXmlWriter extends XmlWriter<Enums> {}
    public static final EnumsXmlWriter XML_WRITER = GWT.create(EnumsXmlWriter.class);
    
    
    enum Color {RED, GREEN, BLUE}

    
    Color colorless;
    Color favorite;
    Set<Color> white;
    List<Color> threeTimesRed;
}
