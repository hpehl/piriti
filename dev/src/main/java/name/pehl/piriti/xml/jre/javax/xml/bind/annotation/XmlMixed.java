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

import org.w3c.dom.Element;
import javax.xml.bind.JAXBElement;

/**
 * <p>
 * Annotate a JavaBean multi-valued property to support mixed content.
 *
 * <p>
 * The usage is subject to the following constraints:
 * <ul>
 *   <li> can be used with &#64;XmlElementRef, &#64;XmlElementRefs or &#64;XmlAnyElement</li>
 * </ul>
 * <p>
 * The following can be inserted into &#64;XmlMixed annotated multi-valued property
 * <ul>
 * <li>XML text information items are added as values of java.lang.String.</li>
 * <li>Children element information items are added as instances of
 * {@link JAXBElement} or instances with a class that is annotated with
 * &#64;XmlRootElement.</li>
 * <li>Unknown content that is not be bound to a JAXB mapped class is inserted
 * as {@link Element}. (Assumes property annotated with &#64;XmlAnyElement)</li>
 * </ul>
 *
 * Below is an example of binding and creation of mixed content.
 * <pre>
 *  &lt;!-- schema fragment having  mixed content -->
 *  &lt;xs:complexType name="letterBody" mixed="true">
 *    &lt;xs:sequence>
 *	&lt;xs:element name="name" type="xs:string"/>
 *	&lt;xs:element name="quantity" type="xs:positiveInteger"/>
 *	&lt;xs:element name="productName" type="xs:string"/>
 *	&lt;!-- etc. -->
 *    &lt;/xs:sequence>
 *  &lt;/xs:complexType>
 *  &lt;xs:element name="letterBody" type="letterBody"/>
 * 
 * // Schema-derived Java code: 
 * // (Only annotations relevant to mixed content are shown below, 
 * //  others are ommitted.)
 * import java.math.BigInteger;
 * public class ObjectFactory {
 * 	// element instance factories
 * 	JAXBElement&lt;LetterBody> createLetterBody(LetterBody value);
 * 	JAXBElement&lt;String>     createLetterBodyName(String value);
 * 	JAXBElement&lt;BigInteger> createLetterBodyQuantity(BigInteger value);
 * 	JAXBElement&lt;String>     createLetterBodyProductName(String value);
 *      // type instance factory
 * 	LetterBody> createLetterBody();
 * }
 * </pre>
 * <pre>
 * public class LetterBody {
 * 	// Mixed content can contain instances of Element classes
 * 	// Name, Quantity and ProductName. Text data is represented as
 *	// java.util.String for text.
 *	&#64;XmlMixed 
 * 	&#64;XmlElementRefs({
 *		&#64;XmlElementRef(name="productName", type=JAXBElement.class),
 *		&#64;XmlElementRef(name="quantity", type=JAXBElement.class),
 *		&#64;XmlElementRef(name="name", type=JAXBElement.class)})
 *	List getContent(){...}
 * }
 * </pre>
 * The following is an XML instance document with mixed content
 * <pre>
 * &lt;letterBody>
 * Dear Mr.&lt;name>Robert Smith&lt;/name>
 * Your order of &lt;quantity>1&lt;/quantity> &lt;productName>Baby
 * Monitor&lt;/productName> shipped from our warehouse. ....
 * &lt;/letterBody>
 * </pre>
 * that can be constructed using following JAXB API calls.
 * <pre>
 * LetterBody lb = ObjectFactory.createLetterBody();
 * JAXBElement&lt;LetterBody> lbe = ObjectFactory.createLetterBody(lb);
 * List gcl = lb.getContent();  //add mixed content to general content property.
 * gcl.add("Dear Mr.");  // add text information item as a String.
 * 
 * // add child element information item
 * gcl.add(ObjectFactory.createLetterBodyName("Robert Smith"));
 * gcl.add("Your order of "); // add text information item as a String
 * 
 * // add children element information items
 * gcl.add(ObjectFactory.
 * 	 		createLetterBodyQuantity(new BigInteger("1")));
 * gcl.add(ObjectFactory.createLetterBodyProductName("Baby Monitor"));
 * gcl.add("shipped from our warehouse");  // add text information item
 * </pre>
 *
 * <p>See "Package Specification" in javax.xml.bind.package javadoc for
 * additional common information.</p>
 * @author Kohsuke Kawaguchi
 * @since JAXB2.0
 */
@Retention(RUNTIME)
@Target({FIELD,METHOD})
public @interface XmlMixed {
}
