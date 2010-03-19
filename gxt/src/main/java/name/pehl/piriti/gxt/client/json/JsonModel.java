package name.pehl.piriti.gxt.client.json;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class annotations for {@linkplain com.extjs.gxt.ui.client.data.ModelData GXT
 * models}. For each property that should be mapped a {@link JsonField}
 * annotation must be specified.
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
