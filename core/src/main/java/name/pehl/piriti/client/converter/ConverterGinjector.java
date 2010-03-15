package name.pehl.piriti.client.converter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

/**
 * Injector used to provide the implementation for {@link ConverterRegistry}.
 * 
 * @see ConverterModule
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 8 $
 */
@GinModules(ConverterModule.class)
public interface ConverterGinjector extends Ginjector
{
    /**
     * Injector instance
     */
    ConverterGinjector INJECTOR = GWT.create(ConverterGinjector.class);


    /**
     * Provides the implementation for the {@link ConverterRegistry}
     * 
     * @return
     */
    ConverterRegistry getConverterRegistry();
}
