package name.pehl.piriti.xml.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
@GinModules(XmlModule.class)
public interface XmlGinjector extends Ginjector
{
    /**
     * Injector instance
     */
    XmlGinjector INJECTOR = GWT.create(XmlGinjector.class);


    /**
     * Provides the implementation for the {@link XmlRegistry}
     * 
     * @return
     */
    XmlRegistry getXmlRegistry();
}
