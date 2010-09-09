package name.pehl.piriti.client.gwttest.shop;

import name.pehl.piriti.client.json.JsonField;
import name.pehl.piriti.client.json.JsonFields;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.json.JsonWriter;
import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlFields;
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
public class ShopReader
{
    // ----------------------------------------------------------- json readers

    // @formatter:off
    @JsonFields({
        @JsonField(name = "customers"), 
        @JsonField(name = "products"), 
        @JsonField(name = "order")})
    // @formatter:on
    public interface ShopJsonReader extends JsonReader<Shop>
    {
    }

    public static final ShopJsonReader SHOP_JSON_READER = GWT.create(ShopJsonReader.class);

    // @formatter:off
    @JsonFields({
        @JsonField(name = "id"), 
        @JsonField(name = "firstname"), 
        @JsonField(name = "surname")})
    // @formatter:on
    public interface CustomerJsonReader extends JsonReader<Customer>
    {
    }

    public static final CustomerJsonReader CUSTOMER_JSON_READER = GWT.create(CustomerJsonReader.class);

    // @formatter:off
    @JsonFields({
        @JsonField(name = "id"), 
        @JsonField(name = "name"), 
        @JsonField(name = "price")})
    // @formatter:on
    public interface ProductJsonReader extends JsonReader<Product>
    {
    }

    public static final ProductJsonReader PRODUCT_JSON_READER = GWT.create(ProductJsonReader.class);

    // @formatter:off
    @JsonFields({
        @JsonField(name = "date", format = "dd.MM.yyyy"), 
        @JsonField(name = "customer"),
        @JsonField(name = "items")})
    // @formatter:on
    public interface OrderJsonReader extends JsonReader<Order>
    {
    }

    public static final OrderJsonReader ORDER_JSON_READER = GWT.create(OrderJsonReader.class);

    // @formatter:off
    @JsonFields({
        @JsonField(name = "product"), 
        @JsonField(name = "amount")})
    // @formatter:on
    public interface OrderItemJsonReader extends JsonReader<OrderItem>
    {
    }

    public static final OrderItemJsonReader ORDER_ITEM_JSON_READER = GWT.create(OrderItemJsonReader.class);

    // ----------------------------------------------------------- json writers

    // @formatter:off
    @JsonFields({
        @JsonField(name = "customers"), 
        @JsonField(name = "products"), 
        @JsonField(name = "order")})
    // @formatter:on
    public interface ShopJsonWriter extends JsonWriter<Shop>
    {
    }

    public static final ShopJsonWriter SHOP_JSON_WRITER = GWT.create(ShopJsonWriter.class);

    // @formatter:off
    @JsonFields({
        @JsonField(name = "id"), 
        @JsonField(name = "firstname"), 
        @JsonField(name = "surname")})
    // @formatter:on
    public interface CustomerJsonWriter extends JsonWriter<Customer>
    {
    }

    public static final CustomerJsonWriter CUSTOMER_JSON_WRITER = GWT.create(CustomerJsonWriter.class);

    // @formatter:off
    @JsonFields({
        @JsonField(name = "id"), 
        @JsonField(name = "name"), 
        @JsonField(name = "price")})
    // @formatter:on
    public interface ProductJsonWriter extends JsonWriter<Product>
    {
    }

    public static final ProductJsonWriter PRODUCT_JSON_WRITER = GWT.create(ProductJsonWriter.class);

    // @formatter:off
    @JsonFields({
        @JsonField(name = "date", format = "dd.MM.yyyy"), 
        @JsonField(name = "customer"),
        @JsonField(name = "items")})
    // @formatter:on
    public interface OrderJsonWriter extends JsonWriter<Order>
    {
    }

    public static final OrderJsonWriter ORDER_JSON_WRITER = GWT.create(OrderJsonWriter.class);

    // @formatter:off
    @JsonFields({
        @JsonField(name = "product"), 
        @JsonField(name = "amount")})
    // @formatter:on
    public interface OrderItemJsonWriter extends JsonWriter<OrderItem>
    {
    }

    public static final OrderItemJsonWriter ORDER_ITEM_JSON_WRITER = GWT.create(OrderItemJsonWriter.class);

    // ------------------------------------------------------------ xml readers

    // @formatter:off
    @XmlFields({
        @XmlField(name = "customers", value = "customers/customer"),
        @XmlField(name = "products", value = "products/product"), 
        @XmlField(name = "order")})
    // @formatter:on
    public interface ShopXmlReader extends XmlReader<Shop>
    {
    }

    public static final ShopXmlReader SHOP_XML_READER = GWT.create(ShopXmlReader.class);

    // @formatter:off
    @XmlFields(
        id = @XmlId(name = "id", value = "id/text()"), 
        value = {
            @XmlField(name = "firstname"),
            @XmlField(name = "surname")})
    // @formatter:on
    public interface CustomerXmlReader extends XmlReader<Customer>
    {
    }

    public static final CustomerXmlReader CUSTOMER_XML_READER = GWT.create(CustomerXmlReader.class);

    // @formatter:off
    @XmlFields(
        id = @XmlId(name = "id"), 
        value = {
            @XmlField(name = "name"), 
            @XmlField(name = "price")})
    // @formatter:on
    public interface ProductXmlReader extends XmlReader<Product>
    {
    }

    public static final ProductXmlReader PRODUCT_XML_READER = GWT.create(ProductXmlReader.class);

    // @formatter:off
    @XmlFields(
        value = {
            @XmlField(name = "date", value = "@date", format = "dd.MM.yyyy"),
            @XmlField(name = "items", value = "items/item")}, 
        references = @XmlIdRef(name = "customer", value = "customer/@ref"))
    // @formatter:on
    public interface OrderXmlReader extends XmlReader<Order>
    {
    }

    public static final OrderXmlReader ORDER_XML_READER = GWT.create(OrderXmlReader.class);

    // @formatter:off
    @XmlFields(
        value = @XmlField(name = "amount", value = "@amount"), 
        references = @XmlIdRef(name = "product", value = "@ref"))
    // @formatter:on
    public interface OrderItemXmlReader extends XmlReader<OrderItem>
    {
    }

    public static final OrderItemXmlReader ORDER_ITEM_XML_READER = GWT.create(OrderItemXmlReader.class);

    // ------------------------------------------------------------ xml writers

    // @formatter:off
    @XmlFields({
        @XmlField(name = "customers", value = "customers/customer"),
        @XmlField(name = "products", value = "products/product"), 
        @XmlField(name = "order")})
    // @formatter:on
    public interface ShopXmlWriter extends XmlWriter<Shop>
    {
    }

    public static final ShopXmlWriter SHOP_XML_WRITER = GWT.create(ShopXmlWriter.class);

    // @formatter:off
    @XmlFields(
        id = @XmlId(name = "id", value = "id/text()"), 
        value = {
            @XmlField(name = "firstname"),
            @XmlField(name = "surname")})
    // @formatter:on
    public interface CustomerXmlWriter extends XmlWriter<Customer>
    {
    }

    public static final CustomerXmlWriter CUSTOMER_XML_WRITER = GWT.create(CustomerXmlWriter.class);

    // @formatter:off
    @XmlFields(
        id = @XmlId(name = "id"), 
        value = {
            @XmlField(name = "name"), 
            @XmlField(name = "price")})
    // @formatter:on
    public interface ProductXmlWriter extends XmlWriter<Product>
    {
    }

    public static final ProductXmlWriter PRODUCT_XML_WRITER = GWT.create(ProductXmlWriter.class);

    // @formatter:off
    @XmlFields(
        value = {
            @XmlField(name = "date", value = "@date", format = "dd.MM.yyyy"),
            @XmlField(name = "items", value = "items/item")}, 
        references = @XmlIdRef(name = "customer", value = "customer/@ref"))
    // @formatter:on
    public interface OrderXmlWriter extends XmlWriter<Order>
    {
    }

    public static final OrderXmlWriter ORDER_XML_WRITER = GWT.create(OrderXmlWriter.class);

    // @formatter:off
    @XmlFields(
        value = @XmlField(name = "amount", value = "@amount"), 
        references = @XmlIdRef(name = "product", value = "@ref"))
    // @formatter:on
    public interface OrderItemXmlWriter extends XmlWriter<OrderItem>
    {
    }

    public static final OrderItemXmlWriter ORDER_ITEM_XML_WRITER = GWT.create(OrderItemXmlWriter.class);
}
