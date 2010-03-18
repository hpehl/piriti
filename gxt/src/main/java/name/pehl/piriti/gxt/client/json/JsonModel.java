package name.pehl.piriti.gxt.client.json;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.extjs.gxt.ui.client.data.ModelData;

/**
 * Class annotations for {@link ModelData GXT models} which contains several
 * {@link JsonField} annotations. In case of a successful mapping each
 * annotation results in a {@link ModelData#set(String, Object)} call.
 * 
 * @author $LastChangedBy:$
 * @version $LastChangedRevision:$
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface JsonModel
{
    JsonField[] value();
}
