package name.pehl.piriti.restlet.client;

import java.io.IOException;
import java.util.List;

/**
 * Common interface for representations which rely on Piriti readers.
 * 
 * @param <T>
 *            The model type
 * @author $Author$
 * @version $Date$ $Revision: 264
 *          $
 */
public interface PiritiRepresentation<T>
{
    /**
     * Return an instance of T converted by a Piriti reader.
     * 
     * @return
     * @throws IOException
     */
    T getModel() throws IOException;


    /**
     * Return a list of Ts converted by a Piriti reader.
     * 
     * @return
     * @throws IOException
     */
    List<T> getModels() throws IOException;
}
