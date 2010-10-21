package name.pehl.piriti.client.json;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import name.pehl.piriti.client.converter.Converter;
import name.pehl.piriti.client.converter.NoopConverter;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;

/**
 * Annotation for mapping JSON data to POJO properties. The JSON data is
 * selected by a "path" expression (the key of the JSON data) and converted if
 * necessary to the type of the annotated property. For some types you can
 * specify a format and a custom converter which is used to parse / serialize
 * the JSON data to / from the properties type.
 * <p>
 * The annotation can be placed either on the field or on the related setter. If
 * placed on the field, please note the annotated fields must not be private!
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
public @interface JsonField
{
    /**
     * The fields name. Only needed if the field cannot be annotated direclty
     * and this annotation is used inside {@link JsonFields}.
     */
    String name() default "";


    /**
     * A "path" expression (the key of the JSON data) to select the JSON data.
     * Defaults to "" which means that the fields name is taken as a default.
     * 
     * @return
     */
    String value() default "";


    /**
     * The format to use when converting the JSON data to the fields type.
     * Defaults to "".
     * 
     * @return
     */
    String format() default "";


    /**
     * A custom converter which is used for the parsing and serialization of the
     * json value. Defaults to {@link NoopConverter}, which means no custom
     * converter should be used.
     * 
     * @return
     */
    Class<? extends Converter<?>> converter() default NoopConverter.class;


    /**
     * The order in which the properties are processed. Default to -1 which
     * means order does not matter.
     * 
     * @return
     */
    int order() default -1;
}
