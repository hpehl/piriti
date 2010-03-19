package name.pehl.piriti.gxt.client.json;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.extjs.gxt.ui.client.data.ModelData;

/**
 * Annotation used in {@link JsonModel} to map one property of a
 * {@linkplain com.extjs.gxt.ui.client.data.ModelData GXT model}. When the
 * mapping was successful the data is set using
 * {@link com.extjs.gxt.ui.client.data.ModelData#set(String, Object)}.
 * <p>
 * For a description of the format and path semantics please refer to
 * {@link name.pehl.piriti.client.json.JsonField}.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface JsonField
{
    /**
     * A "path" expression (the key of the JSON data) to select the JSON data.
     * If not specified the property is taken as path.
     * 
     * @return
     */
    String path() default "";


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
     * If the type is a collection, the type variable of the collection.
     * 
     * @return
     */
    Class<?> typeVariable() default Object.class;


    /**
     * The format to use when converting the JSON data to the type. Defaults to
     * "".
     * 
     * @return
     */
    String format() default "";
}
