/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2005-2011 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package javax.xml.bind.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * Used to map a property to a list simple type.
 *
 * <p><b>Usage</b> </p>
 * <p>
 * The <tt>@XmlList</tt> annotation can be used with the
 * following program elements: 
 * <ul> 
 *   <li> JavaBean property </li>
 *   <li> field </li>
 * </ul>
 *
 * <p>
 * When a collection property is annotated just with @XmlElement,
 * each item in the collection will be wrapped by an element.
 * For example,
 *
 * <pre>
 * &#64;XmlRootElement
 * class Foo {
 *     &#64;XmlElement
 *     List&lt;String> data;
 * }
 * </pre>
 *
 * would produce XML like this:
 *
 * <pre>
 * &lt;foo>
 *   &lt;data>abc</data>
 *   &lt;data>def</data>
 * &lt;/foo>
 * </pre>
 *
 * &#64;XmlList annotation, on the other hand, allows multiple values to be 
 * represented as whitespace-separated tokens in a single element. For example,
 *
 * <pre>
 * &#64;XmlRootElement
 * class Foo {
 *     &#64;XmlElement
 *     &#64;XmlList
 *     List&lt;String> data;
 * }
 * </pre>
 *
 * the above code will produce XML like this:
 *
 * <pre>
 * &lt;foo>
 *   &lt;data>abc def</data>
 * &lt;/foo>
 * </pre>
 *
 * <p>This annotation can be used with the following annotations:
 *        {@link XmlElement}, 
 *        {@link XmlAttribute},
 *        {@link XmlValue},
 *        {@link XmlIDREF}.
 *  <ul>
 *    <li> The use of <tt>@XmlList</tt> with {@link XmlValue} while
 *         allowed, is redundant since  {@link XmlList} maps a
 *         collection type to a simple schema type that derives by
 *         list just as {@link XmlValue} would. </li> 
 *
 *    <li> The use of <tt>@XmlList</tt> with {@link XmlAttribute} while
 *         allowed, is redundant since  {@link XmlList} maps a
 *         collection type to a simple schema type that derives by
 *         list just as {@link XmlAttribute} would. </li> 
 *  </ul>
 *
 * @author <ul><li>Kohsuke Kawaguchi, Sun Microsystems, Inc.</li><li>Sekhar Vajjhala, Sun Microsystems, Inc.</li></ul>
 * @since JAXB2.0
 */
@Retention(RUNTIME) @Target({FIELD,METHOD,PARAMETER})
public @interface XmlList {
}
