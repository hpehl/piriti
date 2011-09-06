/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2004-2010 Oracle and/or its affiliates. All rights reserved.
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

/**
 * Maps an enum constant in {@link Enum} type to XML representation.  
 * 
 * <p> <b>Usage</b> </p>
 *
 * <p> The <tt>@XmlEnumValue</tt> annotation can be used with the
 *     following program elements:  
 * <ul> 
 *   <li>enum constant</li>
 * </ul>
 *
 * <p>See "Package Specification" in javax.xml.bind.package javadoc for
 * additional common information.</p>
 *
 * <p>This annotation, together with {@link XmlEnum} provides a
 * mapping of enum type to XML representation.
 *
 * <p>An enum type is mapped to a schema simple type with enumeration
 * facets. The schema type is derived from the Java type specified in
 * <tt>@XmlEnum.value()</tt>. Each enum constant <tt>@XmlEnumValue</tt>
 * must have a valid lexical representation for the type
 * <tt>@XmlEnum.value()</tt> 
 *
 * <p> In the absence of this annotation, {@link Enum#name()} is used
 * as the XML representation.
 *
 * <p> <b>Example 1: </b>Map enum constant name -> enumeration facet</p>
 * <pre>
 *     //Example: Code fragment
 *     &#64;XmlEnum(String.class)
 *     public enum Card { CLUBS, DIAMONDS, HEARTS, SPADES }
 *
 *     &lt;!-- Example: XML Schema fragment -->
 *     &lt;xs:simpleType name="Card">
 *       &lt;xs:restriction base="xs:string"/>
 *         &lt;xs:enumeration value="CLUBS"/>
 *         &lt;xs:enumeration value="DIAMONDS"/>
 *         &lt;xs:enumeration value="HEARTS"/>
 *         &lt;xs:enumeration value="SPADES"/>
 *     &lt;/xs:simpleType>
 * </pre>
 *
 * <p><b>Example 2: </b>Map enum constant name(value) -> enumeration facet </p>
 * <pre>
 *     //Example: code fragment
 *     &#64;XmlType
 *     &#64;XmlEnum(Integer.class)
 *     public enum Coin { 
 *         &#64;XmlEnumValue("1") PENNY(1),
 *         &#64;XmlEnumValue("5") NICKEL(5),
 *         &#64;XmlEnumValue("10") DIME(10),
 *         &#64;XmlEnumValue("25") QUARTER(25) }
 *
 *     &lt;!-- Example: XML Schema fragment -->
 *     &lt;xs:simpleType name="Coin">
 *       &lt;xs:restriction base="xs:int">
 *         &lt;xs:enumeration value="1"/>
 *         &lt;xs:enumeration value="5"/>
 *         &lt;xs:enumeration value="10"/>
 *         &lt;xs:enumeration value="25"/>
 *       &lt;/xs:restriction>
 *     &lt;/xs:simpleType>
 * </pre>
 *
 * <p><b>Example 3: </b>Map enum constant name -> enumeration facet </p>
 * 
 * <pre>
 *     //Code fragment
 *     &#64;XmlType
 *     &#64;XmlEnum(Integer.class)
 *     public enum Code {
 *         &#64;XmlEnumValue("1") ONE,
 *         &#64;XmlEnumValue("2") TWO;
 *     }
 * 
 *     &lt;!-- Example: XML Schema fragment -->
 *     &lt;xs:simpleType name="Code">
 *       &lt;xs:restriction base="xs:int">
 *         &lt;xs:enumeration value="1"/>
 *         &lt;xs:enumeration value="2"/>
 *       &lt;/xs:restriction>
 *     &lt;/xs:simpleType>
 * </pre>
 *
 * @since JAXB 2.0
 */
@Retention(RUNTIME)
@Target({FIELD})
public @interface XmlEnumValue {
    String value();
}
