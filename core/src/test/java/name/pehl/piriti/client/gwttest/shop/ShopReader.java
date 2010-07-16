package name.pehl.piriti.client.gwttest.shop;

import name.pehl.piriti.client.json.JsonField;
import name.pehl.piriti.client.json.JsonFields;
import name.pehl.piriti.client.json.JsonReader;
import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlFields;
import name.pehl.piriti.client.xml.XmlId;
import name.pehl.piriti.client.xml.XmlIdRef;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author$
 * @version $Date$ $Revision: 725
 *          $
 */
public class ShopReader
{
    // ----------------------------------------------------------- json readers

    @JsonFields({@JsonField(name = "customers"), @JsonField(name = "products"), @JsonField(name = "order")})
    public interface ShopJsonReader extends JsonReader<Shop>
    {
    }

    public static final ShopJsonReader SHOP_JSON = GWT.create(ShopJsonReader.class);

    @JsonFields({@JsonField(name = "id"), @JsonField(name = "firstname"), @JsonField(name = "surname")})
    public interface CustomerJsonReader extends JsonReader<Customer>
    {
    }

    public static final CustomerJsonReader CUSTOMER_JSON = GWT.create(CustomerJsonReader.class);

    @JsonFields({@JsonField(name = "id"), @JsonField(name = "name"), @JsonField(name = "price")})
    public interface ProductJsonReader extends JsonReader<Product>
    {
    }

    public static final ProductJsonReader PRODUCT_JSON = GWT.create(ProductJsonReader.class);

    @JsonFields({@JsonField(name = "date", format = "dd.MM.yyyy"), @JsonField(name = "customer"),
            @JsonField(name = "items")})
    public interface OrderJsonReader extends JsonReader<Order>
    {
    }

    public static final OrderJsonReader ORDER_JSON = GWT.create(OrderJsonReader.class);

    @JsonFields({@JsonField(name = "product"), @JsonField(name = "amount")})
    public interface OrderItemJsonReader extends JsonReader<OrderItem>
    {
    }

    public static final OrderItemJsonReader ORDER_ITEM_JSON = GWT.create(OrderItemJsonReader.class);

    // ------------------------------------------------------------ xml readers

    @XmlFields({@XmlField(name = "customers", value = "customers/customer"),
            @XmlField(name = "products", value = "products/product"), @XmlField(name = "order")})
    public interface ShopXmlReader extends XmlReader<Shop>
    {
    }

    public static final ShopXmlReader SHOP_XML = GWT.create(ShopXmlReader.class);

    @XmlFields(id = @XmlId(name = "id", value = "id/text()"), value = {@XmlField(name = "firstname"),
            @XmlField(name = "surname")})
    public interface CustomerXmlReader extends XmlReader<Customer>
    {
    }

    public static final CustomerXmlReader CUSTOMER_XML = GWT.create(CustomerXmlReader.class);

    @XmlFields(id = @XmlId(name = "id"), value = {@XmlField(name = "name"), @XmlField(name = "price")})
    public interface ProductXmlReader extends XmlReader<Product>
    {
    }

    public static final ProductXmlReader PRODUCT_XML = GWT.create(ProductXmlReader.class);

    @XmlFields(value = {@XmlField(name = "date", value = "@date", format = "dd.MM.yyyy"),
            @XmlField(name = "items", value = "items/item")}, references = @XmlIdRef(name = "customer", value = "customer/@ref"))
    public interface OrderXmlReader extends XmlReader<Order>
    {
    }

    public static final OrderXmlReader ORDER_XML = GWT.create(OrderXmlReader.class);

    @XmlFields(value = @XmlField(name = "amount", value = "@amount"), references = @XmlIdRef(name = "product", value = "@ref"))
    public interface OrderItemXmlReader extends XmlReader<OrderItem>
    {
    }

    public static final OrderItemXmlReader ORDER_ITEM_XML = GWT.create(OrderItemXmlReader.class);
}
