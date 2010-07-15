package name.pehl.piriti.client.gwttest.shop;

import name.pehl.piriti.client.xml.XmlField;
import name.pehl.piriti.client.xml.XmlFields;
import name.pehl.piriti.client.xml.XmlId;
import name.pehl.piriti.client.xml.XmlIdRef;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class ShopReader
{
    // ---------------------------------------------------------- xml lreaders
    
    @XmlFields({@XmlField(name = "customers", value = "customers/customer"),
            @XmlField(name = "products", value = "products/product"), @XmlField(name = "order")})
    public interface ShopXmlReader extends XmlReader<Shop> {}
    public static final ShopXmlReader SHOP_XML = GWT.create(ShopXmlReader.class);

    
    @XmlFields(id = @XmlId(name = "id", value = "id/text()"), value = {@XmlField(name = "firstname"),
            @XmlField(name = "surnamne")})
    public interface CustomerXmlReader extends XmlReader<Customer> {}
    public static final CustomerXmlReader CUSTOMER_XML = GWT.create(CustomerXmlReader.class);

    
    @XmlFields(id = @XmlId(name = "id"), value = {@XmlField(name = "name"), @XmlField(name = "price")})
    public interface ProductXmlReader extends XmlReader<Product> {}
    public static final ProductXmlReader PRODUCT_XML = GWT.create(ProductXmlReader.class);

    
    @XmlFields(value = {@XmlField(name = "date", value = "@date"), @XmlField(name = "items", value = "items/item")}, 
            references = @XmlIdRef(name = "customer", value = "customer/@ref"))
    public interface OrderXmlReader extends XmlReader<Order> {}
    public static final OrderXmlReader ORDER_XML = GWT.create(OrderXmlReader.class);

    
    @XmlFields(value = @XmlField(name = "amount", value = "@amount"), 
            references = @XmlIdRef(name = "product", value = "@ref"))
    public interface OrderItemXmlReader extends XmlReader<OrderItem> {}
    public static final OrderItemXmlReader ORDER_ITEM_XML = GWT.create(OrderItemXmlReader.class);
}
