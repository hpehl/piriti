package name.pehl.piriti.client.converter;

import java.util.Date;

import name.pehl.piriti.commons.client.Format;
import name.pehl.piriti.converter.client.Convert;
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
public class BackAndForth
{
    // --------------------------------------------------- json reader / writer

    public interface BackAndForthJsonWriter extends JsonWriter<BackAndForth> {}
    public static final BackAndForthJsonWriter JSON_WRITER = GWT.create(BackAndForthJsonWriter.class);

    public interface BackAndForthJsonReader extends JsonReader<BackAndForth> {}
    public static final BackAndForthJsonReader JSON_READER = GWT.create(BackAndForthJsonReader.class);

    // ---------------------------------------------------- xml reader / writer

    public interface BackAndForthXmlReader extends XmlReader<BackAndForth> {}
    public static final BackAndForthXmlReader XML_READER = GWT.create(BackAndForthXmlReader.class);

    public interface BackAndForthXmlWriter extends XmlWriter<BackAndForth> {}
    public static final BackAndForthXmlWriter XML_WRITER = GWT.create(BackAndForthXmlWriter.class);

    // ------------------------------------------------------------------- data

    Date date;
    @Format("dd.MM.yy") Date dateWithFormat;
    @Convert(TomorrowConverter.class) Date dateWithConverter;
    
    Maths maths;
    @Convert(MathsConverter.class) Maths mathsWithConverter;
    
    @Convert(DurationConverter.class) int duration;
    @Convert(NameConverter.class) @Format(NameConverter.DE_EN) String name;
}
