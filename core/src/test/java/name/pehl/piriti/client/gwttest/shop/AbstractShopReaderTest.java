package name.pehl.piriti.client.gwttest.shop;

import java.util.Date;
import java.util.Set;
import java.util.SortedSet;

import name.pehl.piriti.client.gwttest.AbstractPiritiTest;

/**
 * @author $Author$
 * @version $Date$ $Revision: 295
 *          $
 */
public abstract class AbstractShopReaderTest extends AbstractPiritiTest
{
    @SuppressWarnings("deprecation")
    protected void assertShop(Shop shop)
    {
        assertNotNull(shop);

        // Customer
        Set<Customer> customers = shop.getCustomers();
        assertNotNull(customers);
        assertEquals(1, customers.size());
        Customer customer = customers.iterator().next();
        assertGarryGourmet(customer);

        // Products
        SortedSet<Product> products = shop.getProducts();
        assertNotNull(products);
        assertEquals(7, products.size());
        Product product = products.iterator().next();
        assertTyhmian(product);

        // Order
        Order order = shop.getOrder();
        assertNotNull(order);
        Date date = order.getDate();
        assertNotNull(date);
        assertEquals(110, date.getYear());
        assertEquals(10, date.getMonth());
        assertEquals(22, date.getDay());
        customer = order.getCustomer();
        assertGarryGourmet(customer);
        SortedSet<OrderItem> items = order.getItems();
        assertNotNull(items);
        assertEquals(7, items.size());

        // OrderItems
        OrderItem item = items.iterator().next();
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
    }
}
