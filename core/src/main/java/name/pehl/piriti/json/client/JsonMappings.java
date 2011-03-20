package name.pehl.piriti.json.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation which can be used in case the POJOs properties cannot be annotated
 * direclty. The annotation has to be placed at the interface which extends
 * {@link JsonReader}:
 * 
 * <pre>
 * public class Readers
 * {
 *     {@code @}JsonMappings({{@code @}Json(property = "id"), 
 *         {@code @}Json(property = "firstname"), 
 *         {@code @}Json(property = "surname")})
 *     public interface CustomerReader extends JsonReader&lt;Customer&gt; { }
 *     public static final CustomerReader CUSTOMER = GWT.create(CustomerReader.class);
 *     
 *     // other readers
 *     ...
 * }
 * 
 * String json = ...;
 * Customer c = Readers.CUSTOMER.read(json);
 * </pre>
 * 
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface JsonMappings
{
    /**
     * The mapping definitions for the POJOs fields.
     * 
     * @return
     */
    Json[] value();
}
