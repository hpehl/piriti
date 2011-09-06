/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2004-2011 Oracle and/or its affiliates. All rights reserved.
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
import static java.lang.annotation.ElementType.TYPE;

/**
 * Maps a class or an enum type to an XML element.
 *
 * <p> <b>Usage</b> </p>
 * <p>
 * The &#64;XmlRootElement annotation can be used with the following program
 * elements: 
 * <ul> 
 *   <li> a top level class </li>
 *   <li> an enum type </li>
 * </ul>
 *
 * <p>See "Package Specification" in javax.xml.bind.package javadoc for
 * additional common information.</p>
 * 
 * <p>
 * When a top level class or an enum type is annotated with the 
 * &#64;XmlRootElement annotation, then its value is represented 
 * as XML element in an XML document.
 *
 * <p> This annotation can be used with the following annotations:
 * {@link XmlType}, {@link XmlEnum}, {@link XmlAccessorType}, 
 * {@link XmlAccessorOrder}.
 * <p>

 * <p>
 * <b>Example 1: </b> Associate an element with XML Schema type
 * <pre>
 *     // Example: Code fragment
 *     &#64;XmlRootElement
 *     class Point {
 *        int x;
 *        int y;
 *        Point(int _x,int _y) {x=_x;y=_y;}
 *     }
 * </pre>
 *
 * <pre>
 *     //Example: Code fragment corresponding to XML output
 *     marshal( new Point(3,5), System.out);
 * </pre>
 *
 * <pre>
 *     &lt;!-- Example: XML output -->
 *     &lt;point>
 *       &lt;x> 3 </x>
 *       &lt;y> 5 </y>
 *     &lt;/point>
 * </pre>
 *
 * The annotation causes an global element declaration to be produced
 * in the schema. The global element declaration is associated with
 * the XML schema type to which the class is mapped.
 *
 * <pre>
 *     &lt;!-- Example: XML schema definition -->
 *     &lt;xs:element name="point" type="point"/>
 *     &lt;xs:complexType name="point">
 *       &lt;xs:sequence>
 *         &lt;xs:element name="x" type="xs:int"/>
 *         &lt;xs:element name="y" type="xs:int"/>
 *       &lt;/xs:sequence>
 *     &lt;/xs:complexType>
 * </pre>
 *
 * <p>
 *
 * <b>Example 2: Orthogonality to type inheritance </b>
 * 
 * <p>
 * An element declaration annotated on a type is not inherited by its
 * derived types. The following example shows this.
 * <pre>
 *     // Example: Code fragment
 *     &#64;XmlRootElement
 *     class Point3D extends Point {
 *         int z;
 *         Point3D(int _x,int _y,int _z) {super(_x,_y);z=_z;}
 *     }
 *
 *     //Example: Code fragment corresponding to XML output * 
 *     marshal( new Point3D(3,5,0), System.out );
 *
 *     &lt;!-- Example: XML output -->
 *     &lt;!-- The element name is point3D not point -->
 *     &lt;point3D>
 *       &lt;x>3&lt;/x>
 *       &lt;y>5&lt;/y>
 *       &lt;z>0&lt;/z>
 *     &lt;/point3D>
 *
 *     &lt;!-- Example: XML schema definition -->
 *     &lt;xs:element name="point3D" type="point3D"/>
 *     &lt;xs:complexType name="point3D">
 *       &lt;xs:complexContent>
 *         &lt;xs:extension base="point">
 *           &lt;xs:sequence>
 *             &lt;xs:element name="z" type="xs:int"/>
 *           &lt;/xs:sequence>
 *         &lt;/xs:extension>
 *       &lt;/xs:complexContent>
 *     &lt;/xs:complexType>
 * </pre>
 *
 * <b>Example 3: </b> Associate a global element with XML Schema type
 * to which the class is mapped.
 * <pre>
 *     //Example: Code fragment
 *     &#64;XmlRootElement(name="PriceElement")
 *     public class USPrice {
 *         &#64;XmlElement
 *         public java.math.BigDecimal price;
 *     }
 *
 *     &lt;!-- Example: XML schema definition -->
 *     &lt;xs:element name="PriceElement" type="USPrice"/>
 *     &lt;xs:complexType name="USPrice">
 *       &lt;xs:sequence>
 *         &lt;xs:element name="price" type="xs:decimal"/>
 *       &lt;/sequence>
 *     &lt;/xs:complexType>
 * </pre>
 *
 * @author Sekhar Vajjhala, Sun Microsystems, Inc.
 * @since JAXB2.0
 */
@Retention(RUNTIME)
@Target({TYPE})
public @interface XmlRootElement {
    /**
     * namespace name of the XML element.
     * <p>
     * If the value is "##default", then the XML namespace name is derived
     * from the package of the class ( {@link XmlSchema} ). If the
     * package is unnamed, then the XML namespace is the default empty
     * namespace.
     */
    String namespace() default "##default";

    /**
     * local name of the XML element.
     * <p>
     * If the value is "##default", then the name is derived from the
     * class name. 
     *
     */
    String name() default "##default";

}
