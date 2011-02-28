package name.pehl.piriti.client.gwttest.types;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public interface FatGlobalItemResources extends ClientBundle
{
    // -------------------------------------------------------------- constants

    int SIZE = 3;
    String ITEMS = "items";
    Date MY_BIRTHDAY = new Date(115772400000l);
    java.sql.Date MY_SQL_BIRTHDAY = java.sql.Date.valueOf("1973-09-02");
    Timestamp TIMESTAMP = Timestamp.valueOf("1973-09-02 11:22:33");
    Time TIME = Time.valueOf("11:22:33");

    // ------------------------------------------------------- deferred binding

    FatGlobalItemResources INSTANCE = GWT.create(FatGlobalItemResources.class);


    @Source("fatGlobalItem.json")
    public TextResource fatGlobalItemJson();


    @Source("fatGlobalItem.xml")
    public TextResource fatGlobalItemXml();
}
