package name.pehl.piriti.rebind;

import org.apache.velocity.VelocityContext;

public final class VelocityContextHolder
{
    private static final VelocityContextHolder INSTANCE = new VelocityContextHolder();
    private VelocityContext context;


    /**
     * Private constructor to ensure that the class acts as a true utility class
     * i.e. it isn't instantiable and extensible.
     */
    private VelocityContextHolder()
    {
    }


    public static VelocityContextHolder get()
    {
        return INSTANCE;
    }


    public void setup(VelocityContext context)
    {
        this.context = context;
    }


    public VelocityContext getContext()
    {
        if (context == null)
        {
            throw new IllegalStateException("Context was not setup. Call setup(VelocityContext) first!");
        }
        return context;
    }
}
