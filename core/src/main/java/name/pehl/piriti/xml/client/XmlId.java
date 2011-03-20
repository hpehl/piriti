package name.pehl.piriti.xml.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import name.pehl.piriti.property.client.NoopPropertyGetter;
import name.pehl.piriti.property.client.NoopPropertySetter;
import name.pehl.piriti.property.client.PropertyGetter;
import name.pehl.piriti.property.client.PropertySetter;

/**
 * Annotation to select an identifier fo a POJO. The annotation can only be
 * specified for one property in the POJO. The properties type must be string.
 * In most cases the id will be mapped to an ID attribute in the XML. That's why
 * the default value of the annotation is "{@code @}id".
 * 
 * @see XmlIdRef
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 82 $
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface XmlId
{
    /**
     * The properties name. Only needed if the property cannot be annotated
     * direclty and this annotation is used inside {@link XmlMappings}.
     */
    String property() default "";


    /**
     * An XPath expression to select the XML Id. Defaults to "@id".
     * 
     * @return
     */
    String value() default "@id";


    /**
     * If <code>true</code> white spaces and new lines are stripped from the
     * selected XPath value. Defaults to <code>true</code>.
     * 
     * @return
     */
    boolean stripWsnl() default true;


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
}
