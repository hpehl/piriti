package name.pehl.piriti.client.external;

import java.util.Date;
import java.util.List;

import name.pehl.piriti.client.AbstractPiritiTest;

/**
 * @author $Author$
 * @version $Date$ $Revision: 295
 *          $
 */
public abstract class AbstractShopTest extends AbstractPiritiTest
{
    public final static Date _22_11_2010 = new Date(1290380400000l);


    protected void assertShop(Shop shop)
    {
        assertNotNull(shop);

        // Customer
        List<Customer> customers = shop.getCustomers();
        assertNotNull(customers);
        assertEquals(1, customers.size());
        Customer customer = customers.iterator().next();
        assertGarryGourmet(customer);

        // Products
        List<Product> products = shop.getProducts();
        assertNotNull(products);
        assertEquals(7, products.size());
        Product product = products.get(0);
        assertTyhmian(product);

        // Order
        Order order = shop.getOrder();
        assertNotNull(order);
        assertDate(_22_11_2010, order.getDate());
        customer = order.getCustomer();
        assertGarryGourmet(customer);
        List<OrderItem> items = order.getItems();
        assertNotNull(items);
        assertEquals(7, items.size());

        // OrderItems
        OrderItem item = items.get(0);
        assertNotNull(item);
        assertEquals(1, item.getAmount());
        product = item.getProduct();
        assertTyhmian(product);
    }


    private void assertGarryGourmet(Customer customer)
    {
        assertNotNull(customer);
        assertEquals("gg", customer.getId());
        assertEquals("Garry", customer.getFirstname());
        assertEquals("Gourmet", customer.getSurname());
    }


    private void assertTyhmian(Product product)
    {
        assertNotNull(product);
        assertEquals("tym", product.getId());
        assertEquals("Tyhmian", product.getName());
        assertEquals(.5, product.getPrice(), .01);
        assertTrue(product.isAvailable());
    }
}
