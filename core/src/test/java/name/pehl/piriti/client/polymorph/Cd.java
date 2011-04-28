package name.pehl.piriti.client.polymorph;

import name.pehl.piriti.json.client.JsonReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2011-02-22 22:55:05 +0100 (Di, 22. Feb 2011) $ $Revision:
 *          1454 $
 */
//@formatter:off
public class Cd extends Medium
{
    public interface CdReader extends JsonReader<Cd> {}
    public static final CdReader READER = GWT.create(CdReader.class);

    int tracks;
}
