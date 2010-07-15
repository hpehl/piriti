package name.pehl.piriti.client.gwttest.shop;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class OrderItem implements Comparable<OrderItem>
{
    private Product product;
    private int amount;


    @Override
    public int compareTo(OrderItem o)
    {
        int diff = amount - o.amount;
        if (diff < 0)
        {
            return -1;
        }
        else if (diff > 0)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }


    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + amount;
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        OrderItem other = (OrderItem) obj;
        if (amount != other.amount)
        {
            return false;
        }
        if (product == null)
        {
            if (other.product != null)
            {
                return false;
            }
        }
        else if (!product.equals(other.product))
        {
            return false;
        }
        return true;
    }


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
