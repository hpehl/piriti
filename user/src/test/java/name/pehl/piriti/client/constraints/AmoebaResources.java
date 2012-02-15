package name.pehl.piriti.client.constraints;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */

public interface AmoebaResources extends ClientBundle
{
    // -------------------------------------------------------------- constants

    int SIZE = 3;
    String ALPHA = "Alpha";
    String BRAVO = "Bravo";
    String CHARLIE = "Charlie";
    Amoeba BLUEPRINT_AMOEBA = new Amoeba();

    // ------------------------------------------------------- deferred binding

    AmoebaResources INSTANCE = GWT.create(AmoebaResources.class);


    @Source("amoeba.json")
    public TextResource amoebaJson();


    @Source("amoebas.json")
    public TextResource amoebasJson();


    @Source("amoeba.xml")
    public TextResource amoebaXml();


    @Source("amoebas.xml")
    public TextResource amoebasXml();
}
