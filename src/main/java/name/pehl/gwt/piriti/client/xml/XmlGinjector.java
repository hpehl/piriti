package name.pehl.gwt.piriti.client.xml;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

/**
 * Injector used to provide the implementation for {@link XmlRegistry}.
 * 
 * @see XmlModule
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
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
