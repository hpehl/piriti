package name.pehl.piriti.xml.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import name.pehl.piriti.commons.client.Mapping;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;

/**
 * Annotation for mapping XML data to POJO properties. The XML is selected by an
 * XPath expression and converted if necessary to the type of the annotated
 * property. For some types you can specify a format and a custom converter
 * which is used to parse / serialize the XML data to / from the properties
 * type.
 * <p>
 * The annotation can be placed either on the POJO, single fields or must be
 * part of the {@code @}{@link XmlMappings} annotation. When placed on a field
 * and the field is not accessible, Piriti tries to use setters / getters
 * instead.
 * <p>
 * The following types are supported:
 * <table border="1" cellspacing="2" cellpadding="2">
 * <tr>
 * <th>Type</th>
 * <th>Default XPath expression</th>
 * <th>Format options</th>
 * <th>Converter options</th>
 * </tr>
 * <tr>
 * <td>boolean, Boolean</td>
 * <td>&lt;propertyname&gt;/text()</td>
 * <td>No format supported. If specified it is ignored.</td>
 * <td>No custom converter supported. If specified it is ignored.</td>
 * </tr>
 * <tr>
 * <td>byte, Byte</td>
 * <td>&lt;propertyname&gt;/text()</td>
 * <td>No format supported. If specified it is ignored.</td>
 * <td>No custom converter supported. If specified it is ignored.</td>
 * </tr>
 * <tr>
 * <td>char, Character</td>
 * <td>&lt;propertyname&gt;/text()</td>
 * <td>No format supported. If specified it is ignored.</td>
 * <td>No custom converter supported. If specified it is ignored.</td>
 * </tr>
 * <tr>
 * <td>java.util.Date</td>
 * <td>&lt;propertyname&gt;/text()</td>
 * <td>If no format is specified a
 * {@linkplain name.pehl.piriti.converter.client.DateConverter#DEFAULT_FORMAT
 * default format} is used. Otherwise must be a valid date format as described
 * by {@link DateTimeFormat}</td>
 * <td>Custom converter supported</td>
 * </tr>
 * <tr>
 * <td>double, Double</td>
 * <td>&lt;propertyname&gt;/text()</td>
 * <td>If no format is specified the XML data is converted using
 * {@link Double#parseDouble(String)}. Otherwise must be a valid number format
 * as described by {@link NumberFormat}</td>
 * <td>Custom converter supported</td>
 * </tr>
 * <tr>
 * <td>float, Float</td>
 * <td>&lt;propertyname&gt;/text()</td>
 * <td>If no format is specified the XML data is converted using
 * {@link Float#parseFloat(String)}. Otherwise must be a valid number format as
 * described by {@link NumberFormat}</td>
 * <td>Custom converter supported</td>
 * </tr>
 * <tr>
 * <td>int, Integer</td>
 * <td>&lt;propertyname&gt;/text()</td>
 * <td>If no format is specified the XML data is converted using
 * {@link Integer#parseInt(String)}. Otherwise must be a valid number format as
 * described by {@link NumberFormat}</td>
 * <td>Custom converter supported</td>
 * </tr>
 * <tr>
 * <td>long, Long</td>
 * <td>&lt;propertyname&gt;/text()</td>
 * <td>If no format is specified the XML data is converted using
 * {@link Long#parseLong(String)}. Otherwise must be a valid number format as
 * described by {@link NumberFormat}</td>
 * <td>Custom converter supported</td>
 * </tr>
 * <tr>
 * <td>short, Short</td>
 * <td>&lt;propertyname&gt;/text()</td>
 * <td>No format supported. If specified it is ignored.</td>
 * <td>No custom converter supported. If specified it is ignored.</td>
 * </tr>
 * <tr>
 * <td>String</td>
 * <td>&lt;propertyname&gt;/text()</td>
 * <td>No format supported. If specified it is ignored.</td>
 * <td>Custom converter supported</td>
 * </tr>
 * <tr>
 * <td>Enums</td>
 * <td>&lt;propertyname&gt;/text()</td>
 * <td>No format supported. If specified it is ignored.</td>
 * <td>Custom converter supported</td>
 * </tr>
 * <tr>
 * <td>All types for which a {@link XmlReader} is registered</td>
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
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Xml
{
    Mapping[] value();

    // Class<? extends InstanceCreator<?, ?>> createWith() default
    // NoopInstanceCreator.class;
}
