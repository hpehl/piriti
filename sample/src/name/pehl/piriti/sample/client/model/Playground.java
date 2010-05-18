package name.pehl.piriti.sample.client.model;

import java.util.ArrayList;
import java.util.List;

import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-03-25 15:16:49 +0100 (Do, 25 Mrz 2010) $ $Revision: 131
 *          $
 */
public class Playground
{
    // ---------------------------------------------------------------- readers

    public interface PlaygroundReader extends XmlReader<Playground>
    {
    }

    public static final PlaygroundReader XML = GWT.create(PlaygroundReader.class);

    // ---------------------------------------------------------------- members

    @XmlField("/Strings/string")
    List<String> myCollection = new ArrayList<String>();
}
