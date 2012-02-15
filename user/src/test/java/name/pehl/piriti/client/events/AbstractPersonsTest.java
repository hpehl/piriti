package name.pehl.piriti.client.events;

import static java.util.Arrays.asList;

import java.util.List;

import name.pehl.piriti.client.AbstractPiritiTest;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2011-03-20 21:02:59 +0100 (So, 20 Mrz 2011) $ $Revision: 295
 *          $
 */
public abstract class AbstractPersonsTest extends AbstractPiritiTest
{
    List<Person> persons()
    {
        return asList(new Person("Jack", "Shepard"), new Person("Kate", "Austen"), new Person("John", "Locke"));
    }
}
