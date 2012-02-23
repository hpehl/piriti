package name.pehl.piriti.rebind;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import name.pehl.piriti.rebind.type.PojoTypeProcessor;
import name.pehl.piriti.rebind.type.RwTypeProcessor;
import name.pehl.piriti.rebind.type.TypeProcessor;

import org.apache.velocity.app.VelocityEngine;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

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
        bindConstant().annotatedWith(VelocityProperties.class).to("name/pehl/piriti/rebind/velocity.properties");
    }


    @Provides
    TypeProcessor provideTypeProcessor()
    {
        TypeProcessor typeProcessor = new PojoTypeProcessor();
        typeProcessor.setNext(new RwTypeProcessor());
        return typeProcessor;
    }


    @Provides
    VelocityEngine provideVelocityEngine(@VelocityProperties String velocityProperties)
            throws UnableToCompleteException
    {
        VelocityEngine engine = null;
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(velocityProperties);
        Properties properties = new Properties();
        try
        {
            properties.load(inputStream);
            engine = new VelocityEngine(properties);
        }
        catch (IOException e)
        {
            Logger.get().die("Cannot load velocity properties from " + velocityProperties);
        }
        return engine;
    }
}
