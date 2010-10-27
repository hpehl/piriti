package name.pehl.piriti.client.json;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import name.pehl.piriti.client.converter.Converter;
import name.pehl.piriti.client.converter.NoopConverter;
import name.pehl.piriti.client.property.NoopPropertyGetter;
import name.pehl.piriti.client.property.NoopPropertySetter;
import name.pehl.piriti.client.property.PropertyGetter;
import name.pehl.piriti.client.property.PropertySetter;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;

/**
 * Annotation for mapping JSON data to POJO properties. The JSON data is
 * selected by a "path" expression. In the simplest case the path is just the
 * key of the JSON data. For more complex cases, you can also use a <a
 * href="http://code.google.com/p/jsonpath/">JSONPath expression</a>. If
 * necessary, the selected data is converted to the type of the annotated
 * property. For some types you can specify a format and a custom converter
 * which is used to parse / serialize the JSON data to / from the properties
 * type.
 * <p>
 * The annotation must be placed on a field in the POJO or must be part of the
 * {@code @}{@link JsonMappings} annotation. When placed on a field and the
 * field is not accessible, Piriti tries to use setters / getters instead.
 * <p>
 * The following types are supported:
 * <table border="1" cellspacing="2" cellpadding="2">
 * <tr>
 * <th>Type</th>
 * <th>Default path expression</th>
 * <th>Format options</th>
 * <th>Converter options</th>
 * </tr>
 * <tr>
 * <td>boolean, Boolean</td>
 * <td>&lt;propertyname&gt;</td>
 * <td>No format supported. If specified it is ignored.</td>
 * <td>No custom converter supported. If specified it is ignored.</td>
 * </tr>
 * <tr>
 * <td>byte, Byte</td>
 * <td>&lt;propertyname&gt;</td>
 * <td>No format supported. If specified it is ignored.</td>
 * <td>No custom converter supported. If specified it is ignored.</td>
 * </tr>
 * <tr>
 * <td>char, Character</td>
 * <td>&lt;propertyname&gt;</td>
 * <td>No format supported. If specified it is ignored.</td>
 * <td>No custom converter supported. If specified it is ignored.</td>
 * </tr>
 * <tr>
 * <td>java.util.Date</td>
 * <td>&lt;propertyname&gt;</td>
 * <td>If no format is specified a
 * {@linkplain name.pehl.piriti.client.converter.DateConverter#DEFAULT_FORMAT
 * default format} is used. Otherwise must be a valid date format as described
 * by {@link DateTimeFormat}</td>
 * <td>Custom converter supported</td>
 * </tr>
 * <tr>
 * <td>double, Double</td>
 * <td>&lt;propertyname&gt;</td>
 * <td>If no format is specified the JSON data is expected to be a
 * {@link JSONNumber}, otherwise the JSON data is expected to be a
 * {@link JSONString} and the format must be a valid number format as described
 * by {@link NumberFormat}</td>
 * <td>Custom converter supported</td>
 * </tr>
 * <tr>
 * <td>float, Float</td>
 * <td>&lt;propertyname&gt;</td>
 * <td>If no format is specified the JSON data is expected to be a
 * {@link JSONNumber}, otherwise the JSON data is expected to be a
 * {@link JSONString} and the format must be a valid number format as described
 * by {@link NumberFormat}</td>
 * <td>Custom converter supported</td>
 * </tr>
 * <tr>
 * <td>int, Integer</td>
 * <td>&lt;propertyname&gt;</td>
 * <td>If no format is specified the JSON data is expected to be a
 * {@link JSONNumber}, otherwise the JSON data is expected to be a
 * {@link JSONString} and the format must be a valid number format as described
 * by {@link NumberFormat}</td>
 * <td>Custom converter supported</td>
 * </tr>
 * <tr>
 * <td>long, Long</td>
 * <td>&lt;propertyname&gt;</td>
 * <td>If no format is specified the JSON data is expected to be a
 * {@link JSONNumber}, otherwise the JSON data is expected to be a
 * {@link JSONString} and the format must be a valid number format as described
 * by {@link NumberFormat}</td>
 * <td>Custom converter supported</td>
 * </tr>
 * <tr>
 * <td>short, Short</td>
 * <td>&lt;propertyname&gt;</td>
 * <td>No format supported. If specified it is ignored.</td>
 * <td>No custom converter supported. If specified it is ignored.</td>
 * </tr>
 * <tr>
 * <td>String</td>
 * <td>&lt;propertyname&gt;</td>
 * <td>No format supported. If specified it is ignored.</td>
 * <td>No custom converter supported. If specified it is ignored.</td>
 * </tr>
 * <tr>
 * <td>Enums</td>
 * <td>&lt;propertyname&gt;</td>
 * <td>No format supported. If specified it is ignored.</td>
 * <td>Custom converter supported</td>
 * </tr>
 * <tr>
 * <td>All types for which a {@link JsonReader} is registered</td>
 * <td>&lt;propertyname&gt;</td>
 * <td>No format supported. If specified it is ignored.</td>
 * <td>No custom converter supported. If specified it is ignored.</td>
 * </tr>
 * <tr>
 * <td>Arrays of the above types</td>
 * <td>&lt;propertyname&gt;</td>
 * <td>If a format is specified it is applied to all array elements.</td>
 * <td>If a custom converter is specified it is applied to all array elements.</td>
 * </tr>
 * <tr>
 * <td>Typed collections of the above types</td>
 * <td>&lt;propertyname&gt;</td>
 * <td>If a format is specified it is applied to all collection elements.</td>
 * <td>If a custom converter is specified it is applied to all collection
 * elements.</td>
 * </tr>
 * </table>
 * <p>
 * Supported collections:
 * <ul>
 * <li>Collection
 * <li>List
 * <li>ArrayList
 * <li>LinkedList
 * <li>Set
 * <li>HashSet
 * <li>SortedSet
 * <li>TreeSet
 * </ul>
 * <p>
 * Please note that all collections must have type parameters, otherwise they
 * cannot be mapped.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 82 $
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Json
{
    /**
     * The order in which the properties are processed. Default to
     * {@link Integer#MAX_VALUE} which means the property will appear last.
     * 
     * @return
     */
    int order() default Integer.MAX_VALUE;


    /**
     * The properties name. Only needed if the property cannot be annotated
     * direclty and this annotation is used inside {@code @}{@link JsonMappings}
     * .
     */
    String property() default "";


    /**
     * A "path" expression to select the JSON data. This can be either just the
     * key of the JSON data or a <a
     * href="http://code.google.com/p/jsonpath/">JSONPath</a> expression.
     * Defaults to "" which means that the properties name is taken as a
     * default.
     * 
     * @return
     */
    String value() default "";


    /**
     * The format to use when converting the JSON data to the properties type.
     * Defaults to "".
     * 
     * @return
     */
    String format() default "";


    /**
     * A custom converter which is used for the parsing and serialization of the
     * JSON value. Defaults to {@link NoopConverter} which means no custom
     * converter should be used.
     * 
     * @return
     */
    Class<? extends Converter<?>> converter() default NoopConverter.class;


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
