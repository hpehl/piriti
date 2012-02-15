package name.pehl.piriti.client.external;

import name.pehl.piriti.commons.client.Mapping;
import name.pehl.piriti.commons.client.Mappings;
import name.pehl.piriti.json.client.JsonReader;
import name.pehl.piriti.json.client.JsonWriter;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author$
 * @version $Date$ $Revision: 725
 *          $
 */
// @formatter:off
public class ShopReader
{
    // ----------------------------------------------------------- json readers

    @Mappings({
        @Mapping("customers"), 
        @Mapping("products"), 
        @Mapping("order")})
    public interface ShopJsonReader extends JsonReader<Shop> {}
    public static final ShopJsonReader SHOP_JSON_READER = GWT.create(ShopJsonReader.class);

    @Mappings({
        @Mapping("id"), 
        @Mapping("firstname"), 
        @Mapping("surname")})
    public interface CustomerJsonReader extends JsonReader<Customer> {}
    public static final CustomerJsonReader CUSTOMER_JSON_READER = GWT.create(CustomerJsonReader.class);

    @Mappings({
        @Mapping("id"), 
        @Mapping("name"), 
        @Mapping("price"),
        @Mapping("available")})
    public interface ProductJsonReader extends JsonReader<Product> {}
    public static final ProductJsonReader PRODUCT_JSON_READER = GWT.create(ProductJsonReader.class);

    @Mappings({
        @Mapping(value = "date", format = "dd.MM.yyyy"), 
        @Mapping("customer"),
        @Mapping("items")})
    public interface OrderJsonReader extends JsonReader<Order> {}
    public static final OrderJsonReader ORDER_JSON_READER = GWT.create(OrderJsonReader.class);

    @Mappings({
        @Mapping("product"), 
        @Mapping("amount")})
    public interface OrderItemJsonReader extends JsonReader<OrderItem> {}
    public static final OrderItemJsonReader ORDER_ITEM_JSON_READER = GWT.create(OrderItemJsonReader.class);


    // ----------------------------------------------------------- json writers

    @Mappings({
        @Mapping("customers"), 
        @Mapping("products"), 
        @Mapping("order")})
    public interface ShopJsonWriter extends JsonWriter<Shop> {}
    public static final ShopJsonWriter SHOP_JSON_WRITER = GWT.create(ShopJsonWriter.class);

    @Mappings({
        @Mapping("id"), 
        @Mapping("firstname"), 
        @Mapping("surname")})
    public interface CustomerJsonWriter extends JsonWriter<Customer> {}
    public static final CustomerJsonWriter CUSTOMER_JSON_WRITER = GWT.create(CustomerJsonWriter.class);

    @Mappings({
        @Mapping("id"), 
        @Mapping("name"), 
        @Mapping("price"),
        @Mapping("available")})
    public interface ProductJsonWriter extends JsonWriter<Product> {}
    public static final ProductJsonWriter PRODUCT_JSON_WRITER = GWT.create(ProductJsonWriter.class);

    @Mappings({
        @Mapping(value = "date", format = "dd.MM.yyyy"), 
        @Mapping("customer"),
        @Mapping("items")})
    public interface OrderJsonWriter extends JsonWriter<Order> {}
    public static final OrderJsonWriter ORDER_JSON_WRITER = GWT.create(OrderJsonWriter.class);

    @Mappings({
        @Mapping("product"), 
        @Mapping("amount")})
    public interface OrderItemJsonWriter extends JsonWriter<OrderItem> {}
    public static final OrderItemJsonWriter ORDER_ITEM_JSON_WRITER = GWT.create(OrderItemJsonWriter.class);

    
    // ------------------------------------------------------------ xml readers

    @Mappings({
        @Mapping(value = "customers", path = "customers/customer"),
        @Mapping(value = "products", path = "products/product"), 
        @Mapping("order")})
    public interface ShopXmlReader extends XmlReader<Shop> {}
    public static final ShopXmlReader SHOP_XML_READER = GWT.create(ShopXmlReader.class);

    @Mappings(
        id = @Mapping("id"), 
        value = {
            @Mapping("firstname"),
            @Mapping("surname")})
    public interface CustomerXmlReader extends XmlReader<Customer> {}
    public static final CustomerXmlReader CUSTOMER_XML_READER = GWT.create(CustomerXmlReader.class);

    @Mappings(
        id = @Mapping(value = "id", path = "@id"), 
        value = {
            @Mapping("name"), 
            @Mapping("price"),
            @Mapping("available")})
    public interface ProductXmlReader extends XmlReader<Product> {}
    public static final ProductXmlReader PRODUCT_XML_READER = GWT.create(ProductXmlReader.class);

    @Mappings(
        value = {
            @Mapping(value = "date", path = "@date", format = "dd.MM.yyyy"),
            @Mapping(value = "items", path = "items/item")}, 
        references = @Mapping(value = "customer", path = "customer/@ref"))
    public interface OrderXmlReader extends XmlReader<Order> {}
    public static final OrderXmlReader ORDER_XML_READER = GWT.create(OrderXmlReader.class);

    @Mappings(
        value = @Mapping(value = "amount", path = "@amount"), 
        references = @Mapping(value = "product", path = "@ref"))
    public interface OrderItemXmlReader extends XmlReader<OrderItem> {}
    public static final OrderItemXmlReader ORDER_ITEM_XML_READER = GWT.create(OrderItemXmlReader.class);

    // ------------------------------------------------------------ xml writers

    @Mappings({
        @Mapping(value = "customers", path = "customers/customer"),
        @Mapping(value = "products", path = "products/product"), 
        @Mapping("order")})
    public interface ShopXmlWriter extends XmlWriter<Shop> {}
    public static final ShopXmlWriter SHOP_XML_WRITER = GWT.create(ShopXmlWriter.class);

    @Mappings(
        id = @Mapping(value = "id", path = "id/text()"), 
        value = {
            @Mapping("firstname"),
            @Mapping("surname")})
    public interface CustomerXmlWriter extends XmlWriter<Customer> {}
    public static final CustomerXmlWriter CUSTOMER_XML_WRITER = GWT.create(CustomerXmlWriter.class);

    @Mappings(
        id = @Mapping("id"), 
        value = {
            @Mapping("name"), 
            @Mapping("price"),
            @Mapping("available")})
    public interface ProductXmlWriter extends XmlWriter<Product> {}
    public static final ProductXmlWriter PRODUCT_XML_WRITER = GWT.create(ProductXmlWriter.class);

    @Mappings(
        value = {
            @Mapping(value = "date", path = "@date", format = "dd.MM.yyyy"),
            @Mapping(value = "items", path = "items/item")}, 
        references = @Mapping(value = "customer", path = "customer/@ref"))
    public interface OrderXmlWriter extends XmlWriter<Order> {}
    public static final OrderXmlWriter ORDER_XML_WRITER = GWT.create(OrderXmlWriter.class);

    @Mappings(
        value = @Mapping(value = "amount", path = "@amount"), 
        references = @Mapping(value= "product", path = "@ref"))
    public interface OrderItemXmlWriter extends XmlWriter<OrderItem> {}
    public static final OrderItemXmlWriter ORDER_ITEM_XML_WRITER = GWT.create(OrderItemXmlWriter.class);
}
