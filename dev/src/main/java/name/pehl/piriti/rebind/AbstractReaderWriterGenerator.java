package name.pehl.piriti.rebind;

import name.pehl.piriti.rebind.type.TypeContext;

import org.apache.velocity.VelocityContext;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;

public abstract class AbstractReaderWriterGenerator extends VelocityGenerator
{
    @Override
    protected void populateVelocityContext(VelocityContext velocityContext, TypeContext typeContext, TypeOracle typeOracle)
    {
        super.populateVelocityContext(velocityContext, typeContext, typeOracle);

        velocityContext.put("readerType", getReaderType(typeOracle));
        velocityContext.put("writerType", getWriterType(typeOracle));
        velocityContext.put("format", getFormat());
    }

    @Override
    protected String getTemplateName()
    {
        return "name/pehl/piriti/rebind/readerwriter.vm";
    }

    protected abstract String getFormat();

    protected abstract Class<?> getReaderClass();

    protected abstract Class<?> getWriterClass();

    private JClassType getReaderType(TypeOracle typeOracle)
    {
        return typeOracle.findType(getReaderClass().getName());
    }

    private JClassType getWriterType(TypeOracle typeOracle)
    {
        return typeOracle.findType(getWriterClass().getName());
    }
}
