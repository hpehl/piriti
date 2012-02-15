package name.pehl.piriti.client.external;

/**
 * @author $Author$
 * @version $Date$ $Revision:
 *          1478 $
 */
public class OrderItem
{
    private Product product;
    private int amount;


    public Product getProduct()
    {
        return product;
    }


    public void setProduct(Product product)
    {
        this.product = product;
    }


    public int getAmount()
    {
        return amount;
    }


    public void setAmount(int amount)
    {
        this.amount = amount;
    }
}
