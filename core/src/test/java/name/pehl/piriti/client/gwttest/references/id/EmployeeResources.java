package name.pehl.piriti.client.gwttest.references.id;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */

public interface EmployeeResources extends ClientBundle
{
    // -------------------------------------------------------------- constants

    String BOSS_ID = "boss";
    String BOSS_NAME = "Big Boss";
    String SELLER_ID = "seller";
    String SELLER_NAME = "Sally Seller";
    String ENGINEER_ID = "engineer";
    String ENGINEER_NAME = "Ed Engineer";
    String CODER_ID = "coder";
    String CODER_NAME = "Carl Coder";
    String TESTER_ID = "tester";
    String TESTER_NAME = "Tom Tester";

    String BOARD_DEPARTMENT_ID = "board";
    String BOARD_DEPARTMENT_NAME = "Board";
    String SALES_DEPARTMENT_ID = "sales";
    String SALES_DEPARTMENT_NAME = "Sales";
    String IT_DEPARTMENT_ID = "it";
    String IT_DEPARTMENT_NAME = "IT";

    // ------------------------------------------------------- deferred binding

    EmployeeResources INSTANCE = GWT.create(EmployeeResources.class);


    @Source("employees.xml")
    public TextResource employeesXml();
}
