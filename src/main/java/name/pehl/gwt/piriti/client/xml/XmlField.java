package name.pehl.gwt.piriti.client.xml;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;

/**
 * Annotation for mapping XML data to POJO fields. The XML is selected by an
 * xpath expression and converted if necessary to the type of the annotated
 * field. For some types you can specify a format which is used when converting
 * the XML data to the fields type.
 * <p>
 * Please note that the annotated fields must not be private!
 * <p>
 * The following types are supported:
 * <table>
 * <tr>
 * <th>Type</th>
 * <th>Default xpath expression</th>
 * <th>Format options</th>
 * </tr>
 * <tr>
 * <td>boolean, Boolean</td>
 * <td>&lt;fieldname&gt;/text()</td>
 * <td>No format supported. If specified it is ignored.</td>
 * </tr>
 * <tr>
 * <td>byte, Byte</td>
 * <td>&lt;fieldname&gt;/text()</td>
 * <td>No format supported. If specified it is ignored.</td>
 * </tr>
 * <tr>
 * <td>char, Character</td>
 * <td>&lt;fieldname&gt;/text()</td>
 * <td>No format supported. If specified it is ignored.</td>
 * </tr>
 * <tr>
 * <td>java.util.Date</td>
 * <td>&lt;fieldname&gt;/text()</td>
 * <td>If no format is specified a
 * {@linkplain name.pehl.gwt.piriti.client.converter.DateConverter#DEFAULT_FORMAT
 * default format} is used. Otherwise must be a valid date format as described
 * by {@link DateTimeFormat}</td>
 * </tr>
 * <tr>
 * <td>double, Double</td>
 * <td>&lt;fieldname&gt;/text()</td>
 * <td>If no format is specified the XML data is converted using
 * {@link Double#parseDouble(String)}. Otherwise must be a valid number format
 * as described by {@link NumberFormat}</td>
 * </tr>
 * <tr>
 * <td>float, Float</td>
 * <td>&lt;fieldname&gt;/text()</td>
 * <td>If no format is specified the XML data is converted using
 * {@link Float#parseFloat(String)}. Otherwise must be a valid number format as
 * described by {@link NumberFormat}</td>
 * </tr>
 * <tr>
 * <td>int, Integer</td>
 * <td>&lt;fieldname&gt;/text()</td>
 * <td>If no format is specified the XML data is converted using
 * {@link Integer#parseInt(String)}. Otherwise must be a valid number format as
 * described by {@link NumberFormat}</td>
 * </tr>
 * <tr>
 * <td>long, Long</td>
 * <td>&lt;fieldname&gt;/text()</td>
 * <td>If no format is specified the XML data is converted using
 * {@link Long#parseLong(String)}. Otherwise must be a valid number format as
 * described by {@link NumberFormat}</td>
 * </tr>
 * <tr>
 * <td>short, Short</td>
 * <td>&lt;fieldname&gt;/text()</td>
 * <td>No format supported. If specified it is ignored.</td>
 * </tr>
 * <tr>
 * <td>String</td>
 * <td>&lt;fieldname&gt;/text()</td>
 * <td>No format supported. If specified it is ignored.</td>
 * </tr>
 * <tr>
 * <td>POJOs for which a {@link XmlReader} is registered</td>
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
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface XmlField
{
    /**
     * An xpath expression to select the XML data. Defaults to "" which means
     * that the fields name is taken as a base for the xpath expression.
     * 
     * @return
     */
    String value() default "";


    /**
     * The format to use when converting the XML data to the fields type.
     * Defaults to "".
     * 
     * @return
     */
    String format() default "";
}
