package name.pehl.piriti.gxt.client.gwttest.shop;

import java.util.Date;
import java.util.Set;
import java.util.SortedSet;

import name.pehl.piriti.gxt.client.json.JsonField;
import name.pehl.piriti.gxt.client.json.JsonFields;
import name.pehl.piriti.gxt.client.json.JsonModelReader;
import name.pehl.piriti.gxt.client.xml.XmlField;
import name.pehl.piriti.gxt.client.xml.XmlFields;
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

    @JsonFields({@JsonField(name = "customers", type = Set.class, typeVariable = Customer.class),
            @JsonField(name = "products", type = SortedSet.class, typeVariable = Product.class),
            @JsonField(name = "order", type = Order.class)})
    public interface ShopJsonReader extends JsonModelReader<Shop>
    {
    }

    public static final ShopJsonReader SHOP_JSON = GWT.create(ShopJsonReader.class);

    @JsonFields({@JsonField(name = "id", type = String.class), @JsonField(name = "firstname", type = String.class),
            @JsonField(name = "surname", type = String.class)})
    public interface CustomerJsonReader extends JsonModelReader<Customer>
    {
    }

    public static final CustomerJsonReader CUSTOMER_JSON = GWT.create(CustomerJsonReader.class);

    @JsonFields({@JsonField(name = "id", type = String.class), @JsonField(name = "name", type = String.class),
            @JsonField(name = "price", type = Double.class)})
    public interface ProductJsonReader extends JsonModelReader<Product>
    {
    }

    public static final ProductJsonReader PRODUCT_JSON = GWT.create(ProductJsonReader.class);

    @JsonFields({@JsonField(name = "date", type = Date.class, format = "dd.MM.yyyy"),
            @JsonField(name = "customer", type = Customer.class),
            @JsonField(name = "items", type = SortedSet.class, typeVariable = OrderItem.class)})
    public interface OrderJsonReader extends JsonModelReader<Order>
    {
    }

    public static final OrderJsonReader ORDER_JSON = GWT.create(OrderJsonReader.class);

    @JsonFields({@JsonField(name = "product", type = Product.class), @JsonField(name = "amount", type = Integer.class)})
    public interface OrderItemJsonReader extends JsonModelReader<OrderItem>
    {
    }

    public static final OrderItemJsonReader ORDER_ITEM_JSON = GWT.create(OrderItemJsonReader.class);

    // ------------------------------------------------------------ xml readers

    @XmlFields({
            @XmlField(name = "customers", path = "customers/customer", type = Set.class, typeVariable = Customer.class),
            @XmlField(name = "products", path = "products/product", type = SortedSet.class, typeVariable = Product.class),
            @XmlField(name = "order", type = Order.class)})
    public interface ShopXmlReader extends XmlModelReader<Shop>
    {
    }

    public static final ShopXmlReader SHOP_XML = GWT.create(ShopXmlReader.class);

    @XmlFields({@XmlField(name = "id", path = "id/text()", type = String.class),
            @XmlField(name = "firstname", type = String.class), @XmlField(name = "surname", type = String.class)})
    public interface CustomerXmlReader extends XmlModelReader<Customer>
    {
    }

    public static final CustomerXmlReader CUSTOMER_XML = GWT.create(CustomerXmlReader.class);

    @XmlFields({@XmlField(name = "id", path = "@id", type = String.class), @XmlField(name = "name", type = String.class),
            @XmlField(name = "price", type = Double.class)})
    public interface ProductXmlReader extends XmlModelReader<Product>
    {
    }

    public static final ProductXmlReader PRODUCT_XML = GWT.create(ProductXmlReader.class);

    @XmlFields({@XmlField(name = "date", path = "@date", type = Date.class, format = "dd.MM.yyyy"),
            @XmlField(name = "items", path = "items/item", type = SortedSet.class, typeVariable = OrderItem.class),
            @XmlField(name = "customer", type = Customer.class)})
    public interface OrderXmlReader extends XmlModelReader<Order>
    {
    }

    public static final OrderXmlReader ORDER_XML = GWT.create(OrderXmlReader.class);

    @XmlFields({@XmlField(name = "amount", path = "@amount", type = Integer.class),
            @XmlField(name = "product", type = Product.class)})
    public interface OrderItemXmlReader extends XmlModelReader<OrderItem>
    {
    }

    public static final OrderItemXmlReader ORDER_ITEM_XML = GWT.create(OrderItemXmlReader.class);
}
