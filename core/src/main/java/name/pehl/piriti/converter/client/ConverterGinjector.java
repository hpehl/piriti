package name.pehl.piriti.converter.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
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
