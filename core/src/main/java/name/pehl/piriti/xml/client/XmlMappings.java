package name.pehl.piriti.xml.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import name.pehl.piriti.commons.client.Id;
import name.pehl.piriti.commons.client.IdRef;

/**
 * Annotation which can be used in case the POJOs properties cannot be annotated
 * direclty. The annotation has to be placed at the interface which extends
 * {@link XmlReader}:
 * 
 * <pre>
 * public class Readers
 * {
 *     {@code @}XmlMappings(id = {@code @}XmlId(property = "id"), 
 *         value = {{@code @}Xml(property = "firstname"), {@code @}XmlField(property = "surname")})
 *     public interface CustomerReader extends XmlReader&lt;Customer&gt; { }
 *     public static final CustomerReader CUSTOMER = GWT.create(CustomerReader.class);
 *     
 *     // other readers
 *     ...
 * }
 * 
 * Document document = ...;
 * Customer c = Readers.CUSTOMER.read(document);
 * </pre>
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface XmlMappings
{
    String NO_ID = "This is a special no-id-marker. Do not use for real IDs!";


    Id id() default @Id(NO_ID);


    Xml[] value();


    IdRef[] references() default {};
}
