package name.pehl.piriti.sample.client;

import name.pehl.piriti.sample.client.event.BooksReadEvent;
import name.pehl.piriti.sample.client.event.BooksReadHandler;
import name.pehl.piriti.sample.client.event.EventBus;
import name.pehl.piriti.sample.client.model.Book;
import name.pehl.piriti.sample.client.model.BookModel;
import name.pehl.piriti.sample.client.rest.BooksClient;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author $Author$
 * @version $Date$ $Revision: 292
 *          $
 */
public class Application extends Composite implements BooksReadHandler
{
    private static ApplicationUiBinder uiBinder = GWT.create(ApplicationUiBinder.class);

    interface ApplicationUiBinder extends UiBinder<Widget, Application>
    {
    }

    @UiField
    Hyperlink fromJsonAsPojo;

    @UiField
    Hyperlink fromJsonAsGxtModel;

    @UiField
    Hyperlink fromXmlAsPojo;

    @UiField
    Hyperlink fromXmlAsGxtModel;

    @UiField
    Label sourceCode;

    @UiField
    SpanElement bookCount;

    @UiField
    SpanElement interval;

    BooksClient client = null;


    public Application()
    {
        client = new BooksClient();
        initWidget(uiBinder.createAndBindUi(this));
        EventBus.get().addHandler(BooksReadEvent.getType(), this);
    }


    @UiHandler("fromJsonAsPojo")
    void onFromJsonAsPojo(ClickEvent e)
    {
        client.readFromJson(Book.JSON);
    }


    @UiHandler("fromJsonAsGxtModel")
    void onFromJsonAsGxtModel(ClickEvent e)
    {
        client.readFromJson(BookModel.JSON);
    }


    @UiHandler("fromXmlAsPojo")
    void onFromXmlAsPojo(ClickEvent e)
    {
        client.readFromXml(Book.XML);
    }


    @UiHandler("fromXmlAsGxtModel")
    void onFromXmlAsGxtModel(ClickEvent e)
    {
        client.readFromXml(BookModel.XML);
    }


    @Override
    public void onTimeInterval(BooksReadEvent event)
    {
        if (event.getBooks() != null)
        {
            bookCount.setInnerText(String.valueOf(event.getBooks().size()));
            interval.setInnerText(String.valueOf(event.getTimeInterval().ms()));
        }
    }
}
