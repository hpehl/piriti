package name.pehl.piriti.rebind;

import com.google.gwt.core.ext.GeneratorContext;

public final class GeneratorContextHolder
{
    private static final GeneratorContextHolder INSTANCE = new GeneratorContextHolder();
    private GeneratorContext context;


    /**
     * Private constructor to ensure that the class acts as a true utility class
     * i.e. it isn't instantiable and extensible.
     */
    private GeneratorContextHolder()
    {
    }


    public static GeneratorContextHolder get()
    {
        return INSTANCE;
    }


    public void setup(GeneratorContext context)
    {
        this.context = context;
    }


    public GeneratorContext getContext()
    {
        if (context == null)
        {
            throw new IllegalStateException("Context was not setup. Call setup(GeneratorContext) first!");
        }
        return context;
    }
}
