package name.pehl.piriti.rebind.property;

import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import name.pehl.piriti.rebind.type.TypeUtils;

import java.util.Map;

/**
 * Class which contains information needed to generate code for the evaluation,
 * conversion and assignment of one property.
 *
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 140 $
 */
public class MapPropertyContext extends PropertyContext {
    private String valueConverter;
    private JType valueType;

    public MapPropertyContext(TypeOracle typeOracle, PropertySource propertySource, Map<PropertyAccess, String> access,
                              ReferenceType referenceType) {
        super(typeOracle, propertySource, access, referenceType);

        assert TypeUtils.isMap(getType());

        valueType = ((JParameterizedType) getType()).getTypeArgs()[1];
    }

    public String getValueConverter() {
        return valueConverter;
    }

    public void setValueConverter(String valueConverter) {
        this.valueConverter = valueConverter;
    }

    public JType getValueType() {
        return valueType;
    }

    public void setValueType(JType valueType) {
        this.valueType = valueType;
    }
}
