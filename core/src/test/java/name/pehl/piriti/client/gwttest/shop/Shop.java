package name.pehl.piriti.client.gwttest.shop;

import java.util.Set;
import java.util.SortedSet;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class Shop
{
    private Set<Customer> customers;
    private SortedSet<Product> products;
    private Order order;


    public Set<Customer> getCustomers()
    {
        return customers;
    }


    public void setCustomers(Set<Customer> customers)
    {
        this.customers = customers;
    }


    public SortedSet<Product> getProducts()
    {
        return products;
    }


    public void setProducts(SortedSet<Product> products)
    {
        this.products = products;
    }


    public Order getOrder()
    {
        return order;
    }


    public void setOrder(Order order)
    {
        this.order = order;
    }
}
