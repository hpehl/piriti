package name.pehl.piriti.gxt.client.gwttest.shop;

import java.util.Date;
import java.util.Set;
import java.util.SortedSet;

import name.pehl.piriti.gxt.client.json.Json;
import name.pehl.piriti.gxt.client.json.JsonMappings;
import name.pehl.piriti.gxt.client.json.JsonModelReader;
import name.pehl.piriti.gxt.client.xml.Xml;
import name.pehl.piriti.gxt.client.xml.XmlMappings;
import name.pehl.piriti.gxt.client.xml.XmlModelReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-07-16 17:02:42 +0200 (Fr, 16. Jul 2010) $ $Revision: 725
 *          $
 */
public class ShopReader
{
    // ----------------------------------------------------------- json readers

    // @formatter:off
    @JsonMappings({
        @Json(property = "customers", type = Set.class, typeVariable = Customer.class),
        @Json(property = "products", type = SortedSet.class, typeVariable = Product.class),
        @Json(property = "order", type = Order.class)})
    // @formatter:on
    public interface ShopJsonReader extends JsonModelReader<Shop>
    {
    }

    public static final ShopJsonReader SHOP_JSON_READER = GWT.create(ShopJsonReader.class);

    // @formatter:off
    @JsonMappings({
        @Json(property = "id", type = String.class), 
        @Json(property = "firstname", type = String.class),
        @Json(property = "surname", type = String.class)})
    // @formatter:on
    public interface CustomerJsonReader extends JsonModelReader<Customer>
    {
    }

    public static final CustomerJsonReader CUSTOMER_JSON_READER = GWT.create(CustomerJsonReader.class);

    // @formatter:off
    @JsonMappings({
        @Json(property = "id", type = String.class), 
        @Json(property = "name", type = String.class),
        @Json(property = "price", type = Double.class)})
    // @formatter:on
    public interface ProductJsonReader extends JsonModelReader<Product>
    {
    }

    public static final ProductJsonReader PRODUCT_JSON_READER = GWT.create(ProductJsonReader.class);

    // @formatter:off
    @JsonMappings({
        @Json(property = "date", type = Date.class, format = "dd.MM.yyyy"),
        @Json(property = "customer", type = Customer.class),
        @Json(property = "items", type = SortedSet.class, typeVariable = OrderItem.class)})
    // @formatter:on
    public interface OrderJsonReader extends JsonModelReader<Order>
    {
    }

    public static final OrderJsonReader ORDER_JSON_READER = GWT.create(OrderJsonReader.class);

    // @formatter:off
    @JsonMappings({
        @Json(property = "product", type = Product.class), 
        @Json(property = "amount", type = Integer.class)})
    // @formatter:on
    public interface OrderItemJsonReader extends JsonModelReader<OrderItem>
    {
    }

    public static final OrderItemJsonReader ORDER_ITEM_JSON_READER = GWT.create(OrderItemJsonReader.class);

    // ------------------------------------------------------------ xml readers

    // @formatter:off
    @XmlMappings({
        @Xml(property = "customers", path = "customers/customer", type = Set.class, typeVariable = Customer.class),
        @Xml(property = "products", path = "products/product", type = SortedSet.class, typeVariable = Product.class),
        @Xml(property = "order", type = Order.class)})
    // @formatter:on
    public interface ShopXmlReader extends XmlModelReader<Shop>
    {
    }

    public static final ShopXmlReader SHOP_XML_READER = GWT.create(ShopXmlReader.class);

    // @formatter:off
    @XmlMappings({
        @Xml(property = "id", type = String.class), 
        @Xml(property = "firstname", type = String.class),
        @Xml(property = "surname", type = String.class)})
    // @formatter:on
    public interface CustomerXmlReader extends XmlModelReader<Customer>
    {
    }

    public static final CustomerXmlReader CUSTOMER_XML_READER = GWT.create(CustomerXmlReader.class);

    // @formatter:off
    @XmlMappings({
        @Xml(property = "id", path = "@id", type = String.class),
        @Xml(property = "name", type = String.class), 
        @Xml(property = "price", type = Double.class)})
    // @formatter:on
    public interface ProductXmlReader extends XmlModelReader<Product>
    {
    }

    public static final ProductXmlReader PRODUCT_XML_READER = GWT.create(ProductXmlReader.class);

    // @formatter:off
    @XmlMappings({
        @Xml(property = "date", path = "@date", type = Date.class, format = "dd.MM.yyyy"),
        @Xml(property = "items", path = "items/item", type = SortedSet.class, typeVariable = OrderItem.class),
        @Xml(property = "customer", type = Customer.class)})
    // @formatter:on
    public interface OrderXmlReader extends XmlModelReader<Order>
    {
    }

    public static final OrderXmlReader ORDER_XML_READER = GWT.create(OrderXmlReader.class);

    // @formatter:off
    @XmlMappings({
        @Xml(property = "amount", path = "@amount", type = Integer.class),
        @Xml(property = "product", type = Product.class)})
    // @formatter:on
    public interface OrderItemXmlReader extends XmlModelReader<OrderItem>
    {
    }

    public static final OrderItemXmlReader ORDER_ITEM_XML_READER = GWT.create(OrderItemXmlReader.class);
}
