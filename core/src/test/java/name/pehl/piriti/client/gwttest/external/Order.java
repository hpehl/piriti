package name.pehl.piriti.client.gwttest.external;

import java.util.Date;
import java.util.List;

/**
 * @author $Author$
 * @version $Date$ $Revision: 725
 *          $
 */
public class Order
{
    private Date date;
    private Customer customer;
    private List<OrderItem> items;


    public Date getDate()
    {
        return date;
    }


    public void setDate(Date date)
    {
        this.date = date;
    }


    public Customer getCustomer()
    {
        return customer;
    }


    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }


    public List<OrderItem> getItems()
    {
        return items;
    }


    public void setItems(List<OrderItem> items)
    {
        this.items = items;
    }
}
