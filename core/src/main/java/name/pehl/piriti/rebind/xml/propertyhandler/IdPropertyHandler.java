package name.pehl.piriti.rebind.xml.propertyhandler;

import name.pehl.piriti.rebind.IndentedWriter;

import com.google.gwt.core.ext.UnableToCompleteException;

/**
 * @author $Author$
 * @version $Date$ $Revision: 638
 *          $
 */
public class IdPropertyHandler extends StringPropertyHandler
{
    @Override
    public void assign(IndentedWriter writer, PropertyContext propertyContext) throws UnableToCompleteException
    {
        super.assign(writer, propertyContext);
        writer.write("if (%s != null) {", propertyContext.getVariableNames().getValueVariable());
        writer.indent();
        writer.write("this.idMap.put(%s, model);", propertyContext.getVariableNames().getValueVariable());
        writer.outdent();
        writer.write("}");
    }
}
