package name.pehl.piriti.client.gwttest.xml;

import name.pehl.piriti.client.gwttest.namespace.LotteryTicketResources;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class XmlParserApp implements EntryPoint
{
    interface Binder extends UiBinder<DockLayoutPanel, XmlParserApp>
    {
    }

    private static final Binder binder = GWT.create(Binder.class);

    @UiField
    TextArea xmlIn;

    @UiField
    TextBox xpath;

    @UiField
    TextBox namespaces;

    @UiField
    Button select;

    @UiField
    TextArea xmlOut;


    @Override
    public void onModuleLoad()
    {
        // Create the UI defined in XmlParserApp.ui.xml.
        DockLayoutPanel outer = binder.createAndBindUi(this);
        
        // Load sample xml
        String xml = LotteryTicketResources.INSTANCE.lotteryTicketDefaultNs().getText();
        xmlIn.setText(xml);

        // Add the outer panel to the RootLayoutPanel, so that it will be
        // displayed.
        RootLayoutPanel root = RootLayoutPanel.get();
        root.add(outer);
    }


    @UiHandler("select")
    void onSelect(ClickEvent e)
    {
        Window.alert("NYI");
    }
}
