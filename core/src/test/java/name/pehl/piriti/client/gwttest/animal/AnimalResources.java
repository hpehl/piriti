package name.pehl.piriti.client.gwttest.animal;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */

public interface AnimalResources extends ClientBundle
{
    // ------------------------------------------------------- deferred binding

    AnimalResources INSTANCE = GWT.create(AnimalResources.class);


    @Source("bird.json")
    public TextResource birdJson();


    @Source("birdOrdered.json")
    public TextResource birdOrderedJson();


    @Source("bird.xml")
    public TextResource birdXml();


    @Source("insect.json")
    public TextResource insectJson();


    @Source("insect.xml")
    public TextResource insectXml();


    @Source("cat.json")
    public TextResource catJson();


    @Source("cat.xml")
    public TextResource catXml();


    @Source("dog.json")
    public TextResource dogJson();


    @Source("dog.xml")
    public TextResource dogXml();
}
