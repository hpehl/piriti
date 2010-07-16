package name.pehl.piriti.gxt.client.gwttest.shop;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-07-15 15:31:16 +0200 (Do, 15. Jul 2010) $ $Revision: 725
 *          $
 */
public class OrderItem extends BaseModel implements Comparable<OrderItem>
{
    @Override
    public int compareTo(OrderItem o)
    {
        Integer amount = get("amount");
        Integer otherAmount = o.get("amount");
        if (amount != null && otherAmount != null)
        {
            int diff = amount - otherAmount;
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
