package name.pehl.piriti.client.gwttest.shop;

import name.pehl.piriti.client.json.Json;
import name.pehl.piriti.client.json.JsonMappings;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.json.JsonWriter;
import name.pehl.piriti.client.xml.Xml;
import name.pehl.piriti.client.xml.XmlMappings;
import name.pehl.piriti.client.xml.XmlId;
import name.pehl.piriti.client.xml.XmlIdRef;
import name.pehl.piriti.client.xml.XmlReader;
import name.pehl.piriti.client.xml.XmlWriter;

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

    @JsonMappings({
        @Json(property = "customers"), 
        @Json(property = "products"), 
        @Json(property = "order")})
    public interface ShopJsonReader extends JsonReader<Shop> {}
    public static final ShopJsonReader SHOP_JSON_READER = GWT.create(ShopJsonReader.class);

    @JsonMappings({
        @Json(property = "id"), 
        @Json(property = "firstname"), 
        @Json(property = "surname")})
    public interface CustomerJsonReader extends JsonReader<Customer> {}
    public static final CustomerJsonReader CUSTOMER_JSON_READER = GWT.create(CustomerJsonReader.class);

    @JsonMappings({
        @Json(property = "id"), 
        @Json(property = "name"), 
        @Json(property = "price")})
    public interface ProductJsonReader extends JsonReader<Product> {}
    public static final ProductJsonReader PRODUCT_JSON_READER = GWT.create(ProductJsonReader.class);

    @JsonMappings({
        @Json(property = "date", format = "dd.MM.yyyy"), 
        @Json(property = "customer"),
        @Json(property = "items")})
    public interface OrderJsonReader extends JsonReader<Order> {}
    public static final OrderJsonReader ORDER_JSON_READER = GWT.create(OrderJsonReader.class);

    @JsonMappings({
        @Json(property = "product"), 
        @Json(property = "amount")})
    public interface OrderItemJsonReader extends JsonReader<OrderItem> {}
    public static final OrderItemJsonReader ORDER_ITEM_JSON_READER = GWT.create(OrderItemJsonReader.class);


    // ----------------------------------------------------------- json writers

    @JsonMappings({
        @Json(property = "customers"), 
        @Json(property = "products"), 
        @Json(property = "order")})
    public interface ShopJsonWriter extends JsonWriter<Shop> {}
    public static final ShopJsonWriter SHOP_JSON_WRITER = GWT.create(ShopJsonWriter.class);

    @JsonMappings({
        @Json(property = "id"), 
        @Json(property = "firstname"), 
        @Json(property = "surname")})
    public interface CustomerJsonWriter extends JsonWriter<Customer> {}
    public static final CustomerJsonWriter CUSTOMER_JSON_WRITER = GWT.create(CustomerJsonWriter.class);

    @JsonMappings({
        @Json(property = "id"), 
        @Json(property = "name"), 
        @Json(property = "price")})
    public interface ProductJsonWriter extends JsonWriter<Product> {}
    public static final ProductJsonWriter PRODUCT_JSON_WRITER = GWT.create(ProductJsonWriter.class);

    @JsonMappings({
        @Json(property = "date", format = "dd.MM.yyyy"), 
        @Json(property = "customer"),
        @Json(property = "items")})
    public interface OrderJsonWriter extends JsonWriter<Order> {}
    public static final OrderJsonWriter ORDER_JSON_WRITER = GWT.create(OrderJsonWriter.class);

    @JsonMappings({
        @Json(property = "product"), 
        @Json(property = "amount")})
    public interface OrderItemJsonWriter extends JsonWriter<OrderItem> {}
    public static final OrderItemJsonWriter ORDER_ITEM_JSON_WRITER = GWT.create(OrderItemJsonWriter.class);

    
    // ------------------------------------------------------------ xml readers

    @XmlMappings({
        @Xml(property = "customers", value = "customers/customer"),
        @Xml(property = "products", value = "products/product"), 
        @Xml(property = "order")})
    public interface ShopXmlReader extends XmlReader<Shop> {}
    public static final ShopXmlReader SHOP_XML_READER = GWT.create(ShopXmlReader.class);

    @XmlMappings(
        id = @XmlId(property = "id", value = "id/text()"), 
        value = {
            @Xml(property = "firstname"),
            @Xml(property = "surname")})
    public interface CustomerXmlReader extends XmlReader<Customer> {}
    public static final CustomerXmlReader CUSTOMER_XML_READER = GWT.create(CustomerXmlReader.class);

    @XmlMappings(
        id = @XmlId(property = "id"), 
        value = {
            @Xml(property = "name"), 
            @Xml(property = "price")})
    public interface ProductXmlReader extends XmlReader<Product> {}
    public static final ProductXmlReader PRODUCT_XML_READER = GWT.create(ProductXmlReader.class);

    @XmlMappings(
        value = {
            @Xml(property = "date", value = "@date", format = "dd.MM.yyyy"),
            @Xml(property = "items", value = "items/item")}, 
        references = @XmlIdRef(property = "customer", value = "customer/@ref"))
    public interface OrderXmlReader extends XmlReader<Order> {}
    public static final OrderXmlReader ORDER_XML_READER = GWT.create(OrderXmlReader.class);

    @XmlMappings(
        value = @Xml(property = "amount", value = "@amount"), 
        references = @XmlIdRef(property = "product", value = "@ref"))
    public interface OrderItemXmlReader extends XmlReader<OrderItem> {}
    public static final OrderItemXmlReader ORDER_ITEM_XML_READER = GWT.create(OrderItemXmlReader.class);

    // ------------------------------------------------------------ xml writers

    @XmlMappings({
        @Xml(property = "customers", value = "customers/customer"),
        @Xml(property = "products", value = "products/product"), 
        @Xml(property = "order")})
    public interface ShopXmlWriter extends XmlWriter<Shop> {}
    public static final ShopXmlWriter SHOP_XML_WRITER = GWT.create(ShopXmlWriter.class);

    @XmlMappings(
        id = @XmlId(property = "id", value = "id/text()"), 
        value = {
            @Xml(property = "firstname"),
            @Xml(property = "surname")})
    public interface CustomerXmlWriter extends XmlWriter<Customer> {}
    public static final CustomerXmlWriter CUSTOMER_XML_WRITER = GWT.create(CustomerXmlWriter.class);

    @XmlMappings(
        id = @XmlId(property = "id"), 
        value = {
            @Xml(property = "name"), 
            @Xml(property = "price")})
    public interface ProductXmlWriter extends XmlWriter<Product> {}
    public static final ProductXmlWriter PRODUCT_XML_WRITER = GWT.create(ProductXmlWriter.class);

    @XmlMappings(
        value = {
            @Xml(property = "date", value = "@date", format = "dd.MM.yyyy"),
            @Xml(property = "items", value = "items/item")}, 
        references = @XmlIdRef(property = "customer", value = "customer/@ref"))
    public interface OrderXmlWriter extends XmlWriter<Order> {}
    public static final OrderXmlWriter ORDER_XML_WRITER = GWT.create(OrderXmlWriter.class);

    @XmlMappings(
        value = @Xml(property = "amount", value = "@amount"), 
        references = @XmlIdRef(property = "product", value = "@ref"))
    public interface OrderItemXmlWriter extends XmlWriter<OrderItem> {}
    public static final OrderItemXmlWriter ORDER_ITEM_XML_WRITER = GWT.create(OrderItemXmlWriter.class);
}
