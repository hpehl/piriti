package name.pehl.piriti.client.gwttest.shop;

import java.util.Date;
import java.util.SortedSet;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class Order
{
    private Date date;
    private Customer customer;
    private SortedSet<OrderItem> items;


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


    public SortedSet<OrderItem> getItems()
    {
        return items;
    }


    public void setItems(SortedSet<OrderItem> items)
    {
        this.items = items;
    }
}
