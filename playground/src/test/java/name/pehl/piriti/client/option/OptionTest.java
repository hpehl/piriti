/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */
package name.pehl.piriti.client.option;

import java.util.List;

import name.pehl.piriti.client.AbstractPlaygroundTest;

/**
 * @author Harald Pehl
 */
public class OptionTest extends AbstractPlaygroundTest {

    public void testCampaigns() {
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
