package name.pehl.piriti.client.gwttest.shop;

import java.util.List;

/**
 * @author $Author$
 * @version $Date$ $Revision: 725
 *          $
 */
public class Shop
{
    private List<Customer> customers;
    private List<Product> products;
    private Order order;


    public List<Customer> getCustomers()
    {
        return customers;
    }


    public void setCustomers(List<Customer> customers)
    {
        this.customers = customers;
    }


    public List<Product> getProducts()
    {
        return products;
    }


    public void setProducts(List<Product> products)
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
