package name.pehl.piriti.rebind;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.inject.Singleton;

import name.pehl.piriti.rebind.property.PropertyContextCreator;
import name.pehl.piriti.rebind.property.PropertyContextValidator;
import name.pehl.piriti.rebind.type.PojoTypeProcessor;
import name.pehl.piriti.rebind.type.RwTypeProcessor;
import name.pehl.piriti.rebind.type.TypeProcessor;

import org.apache.velocity.app.VelocityEngine;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class RebindModule extends AbstractModule
{
    private final TreeLogger treeLogger;
    private final GeneratorContext generatorContext;


    public RebindModule(TreeLogger treeLogger, GeneratorContext generatorContext)
    {
        super();
        this.treeLogger = treeLogger;
        this.generatorContext = generatorContext;
    }


    @Override
    protected void configure()
    {
        bindConstant().annotatedWith(VelocityProperties.class).to("name/pehl/piriti/rebind/velocity.properties");
    }


    @Provides
    public TreeLogger provideTreeLogger()
    {
        return treeLogger;
    }


    @Provides
    public TypeOracle provideTypeOracle()
    {
        return generatorContext.getTypeOracle();
    }


    @Provides
    public TypeProcessor provideTypeProcessor(PropertyContextCreator propertyContextCreator,
            PropertyContextValidator propertyContextValidator, Logger logger)
    {
        TypeProcessor typeProcessor = new PojoTypeProcessor(propertyContextCreator, propertyContextValidator, logger);
        typeProcessor.setNext(new RwTypeProcessor(propertyContextCreator, propertyContextValidator, logger));
        return typeProcessor;
    }


    @Provides
    @Singleton
    public VelocityEngine provideVelocityEngine(@VelocityProperties
    String velocityProperties, Logger logger) throws UnableToCompleteException
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
            logger.die("Cannot load velocity properties from " + velocityProperties);
        }
        return engine;
    }
}
