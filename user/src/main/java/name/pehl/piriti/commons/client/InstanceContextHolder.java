package name.pehl.piriti.commons.client;

/**
 * Class used during deserialization to store and get the context information
 * for an instance of T.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 * @param <T>
 *            the model type
 * @param <C>
 *            the context type
 */
public class InstanceContextHolder<T, C>
{
    private final T instance;
    private final C context;


    public InstanceContextHolder(T instance, C context)
    {
        super();
        this.instance = instance;
        this.context = context;
    }


    public T getInstance()
    {
        return instance;
    }


    public C getContext()
    {
        return context;
    }


    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (context == null ? 0 : context.hashCode());
        result = prime * result + (instance == null ? 0 : instance.hashCode());
        return result;
    }


    @Override
    @SuppressWarnings("rawtypes")
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
        InstanceContextHolder other = (InstanceContextHolder) obj;
        if (context == null)
        {
            if (other.context != null)
            {
                return false;
            }
        }
        else if (!context.equals(other.context))
        {
            return false;
        }
        if (instance == null)
        {
            if (other.instance != null)
            {
                return false;
            }
        }
        else if (!instance.equals(other.instance))
        {
            return false;
        }
        return true;
    }
}
