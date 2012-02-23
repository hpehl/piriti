package name.pehl.piriti.rebind;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.inject.AbstractModule;

public class RebindModule extends AbstractModule
{
    private final TreeLogger treeLogger;
    private final GeneratorContext context;


    public RebindModule(TreeLogger treeLogger, GeneratorContext context)
    {
        super();
        this.treeLogger = treeLogger;
        this.context = context;
    }


    @Override
    protected void configure()
    {
    }
}
