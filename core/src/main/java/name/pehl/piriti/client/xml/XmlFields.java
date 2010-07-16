package name.pehl.piriti.client.xml;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation which can be used in case the POJOs fields cannot be annotated
 * direclty. The annotation has to be placed at the interface which extends
 * {@link XmlReader}:
 * 
 * <pre>
 * public class Readers
 * {
 *     &#64;XmlFields(id = &#64;XmlId(name = "id"), 
 *         value = {&#64;XmlField(name = "firstname"), &#64;XmlField(name = "surname")})
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
public @interface XmlFields
{
    String NO_ID = "This is a special no-id-marker. Do not use for real IDs!";


    XmlId id() default @XmlId(NO_ID);


    XmlField[] value();


    XmlIdRef[] references() default {};
}
