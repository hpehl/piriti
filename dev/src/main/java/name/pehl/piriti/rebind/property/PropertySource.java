package name.pehl.piriti.rebind.property;

import name.pehl.piriti.commons.client.InstanceCreator;
import name.pehl.piriti.converter.client.Converter;
import name.pehl.piriti.property.client.PropertyGetter;
import name.pehl.piriti.property.client.PropertySetter;
import name.pehl.totoe.commons.client.WhitespaceHandling;

import com.google.gwt.core.ext.typeinfo.JType;

/**
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
public interface PropertySource
{
    int getOrder();


    JType getType();


    String getName();


    String getPath();


    Class<? extends Converter<?>> getConverter();


    String getFormat();


    WhitespaceHandling getWhitespaceHandling();


    boolean isNative();


    Class<? extends InstanceCreator<?, ?>> getInstanceCreator();


    Class<? extends PropertyGetter<?, ?>> getGetter();


    Class<? extends PropertySetter<?, ?>> getSetter();
}
