package name.pehl.piriti.commons.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import name.pehl.piriti.converter.client.Converter;
import name.pehl.piriti.converter.client.NoopConverter;
import name.pehl.piriti.property.client.NoopPropertyGetter;
import name.pehl.piriti.property.client.NoopPropertySetter;
import name.pehl.piriti.property.client.PropertyGetter;
import name.pehl.piriti.property.client.PropertySetter;
import name.pehl.totoe.commons.client.WhitespaceHandling;

/**
 * Annotation used inside {@link Mappings} to specify the mapping of one
 * property.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 82 $
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Mapping
{
    /**
     * The properties name.
     */
    String value();


    /**
     * A "path" expression to select the JSON / XML data. This can be either
     * just the key / element name or a <a
     * href="http://code.google.com/p/jsonpath/">JSONPath</a> or XPath
     * expression. Defaults to "" which means that the properties name is taken
     * as a default.
     * 
     * @return
     */
    String path() default "";


    /**
     * The format to use when converting the JSON data to the properties type.
     * Defaults to "".
     * 
     * @return
     */
    String format() default "";


    /**
     * How to handle how leading and trailing whitespace. Please note that this
     * does not effect whitespace <strong>within</strong> the mapped value.
     * 
     * @return
     */
    WhitespaceHandling whitespace() default WhitespaceHandling.REMOVE;


    /**
     * Whether to use an {@link InstanceCreator}.
     * 
     * @return
     */
    Class<? extends InstanceCreator<?, ?>> createWith() default NoopInstanceCreator.class;


    /**
     * A custom converter which is used for the parsing and serialization of the
     * value. Defaults to {@link NoopConverter} which means no custom converter
     * should be used.
     * 
     * @return
     */
    Class<? extends Converter<?>> convert() default NoopConverter.class;


    /**
     * A custom property getter for reading the property. Defaults to
     * {@link NoopPropertyGetter} which means that the property is read using
     * the field or a getter.
     * 
     * @return
     */
    Class<? extends PropertyGetter<?, ?>> getter() default NoopPropertyGetter.class;


    /**
     * A custom property setter for setting the property. Defaults to
     * {@link NoopPropertySetter} which means that the property is set using the
     * field or a setter.
     * 
     * @return
     */
    Class<? extends PropertySetter<?, ?>> setter() default NoopPropertySetter.class;


    /**
     * Wehther this property should be handled nativly.
     * 
     * @return
     */
    boolean isNative() default false;
}
