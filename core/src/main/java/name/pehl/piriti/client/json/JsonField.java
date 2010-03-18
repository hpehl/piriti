package name.pehl.piriti.client.json;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;

/**
 * Annotation for mapping JSON data to POJO fields. The JSON data is selected by
 * a "path" expression (the key of the JSON data) and converted if necessary to
 * the type of the annotated field. For some types you can specify a format
 * which is used when converting the JSON data to the fields type.
 * <p>
 * Please note that the annotated fields must not be private!
 * <p>
 * The following types are supported:
 * <table>
 * <tr>
 * <th>Type</th>
 * <th>Default path expression</th>
 * <th>Format options</th>
 * </tr>
 * <tr>
 * <td>boolean, Boolean</td>
 * <td>&lt;fieldname&gt;</td>
 * <td>No format supported. If specified it is ignored.</td>
 * </tr>
 * <tr>
 * <td>byte, Byte</td>
 * <td>&lt;fieldname&gt;</td>
 * <td>No format supported. If specified it is ignored.</td>
 * </tr>
 * <tr>
 * <td>char, Character</td>
 * <td>&lt;fieldname&gt;</td>
 * <td>No format supported. If specified it is ignored.</td>
 * </tr>
 * <tr>
 * <td>java.util.Date</td>
 * <td>&lt;fieldname&gt;</td>
 * <td>If no format is specified a
 * {@linkplain name.pehl.piriti.client.converter.DateConverter#DEFAULT_FORMAT
 * default format} is used. Otherwise must be a valid date format as described
 * by {@link DateTimeFormat}</td>
 * </tr>
 * <tr>
 * <td>double, Double</td>
 * <td>&lt;fieldname&gt;</td>
 * <td>If no format is specified the JSON data is expected to be a
 * {@link JSONNumber}, otherwise the JSON data is expected to be a
 * {@link JSONString} and the format must be a valid number format as described
 * by {@link NumberFormat}</td>
 * </tr>
 * <tr>
 * <td>float, Float</td>
 * <td>&lt;fieldname&gt;</td>
 * <td>If no format is specified the JSON data is expected to be a
 * {@link JSONNumber}, otherwise the JSON data is expected to be a
 * {@link JSONString} and the format must be a valid number format as described
 * by {@link NumberFormat}</td>
 * </tr>
 * <tr>
 * <td>int, Integer</td>
 * <td>&lt;fieldname&gt;</td>
 * <td>If no format is specified the JSON data is expected to be a
 * {@link JSONNumber}, otherwise the JSON data is expected to be a
 * {@link JSONString} and the format must be a valid number format as described
 * by {@link NumberFormat}</td>
 * </tr>
 * <tr>
 * <td>long, Long</td>
 * <td>&lt;fieldname&gt;</td>
 * <td>If no format is specified the JSON data is expected to be a
 * {@link JSONNumber}, otherwise the JSON data is expected to be a
 * {@link JSONString} and the format must be a valid number format as described
 * by {@link NumberFormat}</td>
 * </tr>
 * <tr>
 * <td>short, Short</td>
 * <td>&lt;fieldname&gt;</td>
 * <td>No format supported. If specified it is ignored.</td>
 * </tr>
 * <tr>
 * <td>String</td>
 * <td>&lt;fieldname&gt;</td>
 * <td>No format supported. If specified it is ignored.</td>
 * </tr>
 * <tr>
 * <td>Enums</td>
 * <td>&lt;fieldname&gt;</td>
 * <td>No format supported. If specified it is ignored.</td>
 * </tr>
 * <tr>
 * <td>All types for which a {@link JsonReader} is registered</td>
 * <td>&lt;fieldname&gt;</td>
 * <td>No format supported. If specified it is ignored.</td>
 * </tr>
 * <tr>
 * <td>Arrays of the above types</td>
 * <td>&lt;fieldname&gt;</td>
 * <td>If a format is specified it is applied for all array elements.</td>
 * </tr>
 * <tr>
 * <td>Typed collections of the above types</td>
 * <td>&lt;fieldname&gt;</td>
 * <td>If a format is specified it is applied for all collection elements.</td>
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
}
