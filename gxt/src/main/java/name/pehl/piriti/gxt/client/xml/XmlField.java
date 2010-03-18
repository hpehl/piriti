package name.pehl.piriti.gxt.client.xml;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.extjs.gxt.ui.client.data.ModelData;

/**
 * Annotation used in {@link XmlModel} to map one field. For a description of
 * the format and path semantics please refer to
 * {@link name.pehl.piriti.client.xml.XmlField}.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface XmlField
{
    /**
     * An XPath expression to select the XML data.
     * 
     * @return
     */
    String path();


    /**
     * The name of the property in the {@link ModelData GXT model}.
     * 
     * @return
     */
    String property();


    /**
     * The type for the property.
     * 
     * @return
     */
    Class<?> type();


    /**
     * The format to use when converting the XML data to the type. Defaults to
     * "".
     * 
     * @return
     */
    String format() default "";
}
