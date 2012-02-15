package name.pehl.piriti.client.external;

/**
 * @author $Author$
 * @version $Date$ $Revision:
 *          1454 $
 */
public class Product
{
    private String id;
    private String name;
    private double price;
    private boolean available;


    public String getId()
    {
        return id;
    }


    public void setId(String id)
    {
        this.id = id;
    }


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public double getPrice()
    {
        return price;
    }


    public void setPrice(double price)
    {
        this.price = price;
    }


    public boolean isAvailable()
    {
        return available;
    }


    public void setAvailable(boolean available)
    {
        this.available = available;
    }
}
