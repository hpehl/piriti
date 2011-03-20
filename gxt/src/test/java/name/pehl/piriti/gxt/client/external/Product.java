package name.pehl.piriti.gxt.client.external;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-07-15 15:31:16 +0200 (Do, 15. Jul 2010) $ $Revision: 725
 *          $
 */
public class Product extends BaseModel implements Comparable<Product>
{
    @Override
    public int compareTo(Product p)
    {
        Double price = get("price");
        Double otherPrice = p.get("price");
        if (price != null && otherPrice != null)
        {
            double diff = price - otherPrice;
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
        return 0;
    }
}
