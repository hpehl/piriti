package name.pehl.piriti.xml.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public class XPath implements Iterable<String>
{
    private final List<String> elements;


    public XPath(String path)
    {
        elements = new ArrayList<String>();
        if (isNotEmpty(path))
        {
            String[] parts = path.split("/");
            for (String part : parts)
            {
                if (isNotEmpty(part))
                {
                    elements.add(part);
                }
            }
        }
    }


    @Override
    public Iterator<String> iterator()
    {
        return elements.iterator();
    }


    public int size()
    {
        return elements.size();
    }


    public boolean isEmpty()
    {
        return elements.isEmpty();
    }


    public String first()
    {
        if (!elements.isEmpty())
        {
            return elements.get(0);
        }
        return null;
    }


    public String allButLast()
    {
        if (elements.isEmpty())
        {
            return null;
        }
        else if (elements.size() == 1)
        {
            return elements.get(0);
        }
        List<String> sublist = elements.subList(0, elements.size() - 1);
        StringBuilder builder = new StringBuilder();
        for (Iterator<String> iter = sublist.iterator(); iter.hasNext();)
        {
            String element = iter.next();
            builder.append(element);
            if (iter.hasNext())
            {
                builder.append("/");
            }
        }
        return builder.toString();
    }


    public String last()
    {
        if (!elements.isEmpty())
        {
            return elements.get(elements.size() - 1);
        }
        return null;
    }


    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        for (Iterator<String> iter = elements.iterator(); iter.hasNext();)
        {
            String element = iter.next();
            builder.append(element);
            if (iter.hasNext())
            {
                builder.append("/");
            }
        }
        return builder.toString();
    }


    private boolean isNotEmpty(String string)
    {
        return string != null && string.trim().length() != 0;
    }
}
