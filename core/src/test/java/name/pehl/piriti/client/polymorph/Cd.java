package name.pehl.piriti.client.polymorph;

import name.pehl.piriti.commons.client.CreateWith;
import name.pehl.piriti.json.client.JsonReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2011-02-22 22:55:05 +0100 (Di, 22. Feb 2011) $ $Revision:
 *          1454 $
 */
//@formatter:off
@CreateWith(MediumCreator.class)
public class Cd extends Medium
{
    public interface CdReader extends JsonReader<Cd> {}
    public static final CdReader READER = GWT.create(CdReader.class);

    int tracks;
}
