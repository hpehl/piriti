package name.pehl.piriti.client.option;

import java.util.List;

import name.pehl.piriti.client.AbstractPlaygroundTest;

/**
 * @author Harald Pehl
 */
public class OptionTest extends AbstractPlaygroundTest {

    public void testOptions() {
        String xml = OptionResources.INSTANCE.optionsXml().getText();
        List<Option> options = Option.READER.readList(xml);
        assertNotNull(options);
        assertEquals(4, options.size());
        assertOption(options.get(0), "1", "Java");
        assertOption(options.get(1), "2", "C++");
        assertOption(options.get(2), "3", "Pascal");
        assertOption(options.get(3), "4", "Modula-2");
    }

    private void assertOption(final Option option, final String id, final String name) {
        assertEquals(id, option.getId());
        assertEquals(name, option.getName());
    }
}
