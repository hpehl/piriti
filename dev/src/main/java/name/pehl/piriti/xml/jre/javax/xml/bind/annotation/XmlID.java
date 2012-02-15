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

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * <p>
 * Maps a JavaBean property to XML ID.
 *
 * <p>
 * To preserve referential integrity of an object graph across XML
 * serialization followed by a XML deserialization, requires an object
 * reference to be marshalled by reference or containment
 * appropriately. Annotations <tt>&#64;XmlID</tt> and <tt>&#64;XmlIDREF</tt>
 * together allow a customized mapping of a JavaBean property's
 * type by containment or reference. 
 *
 * <p><b>Usage</b> </p>
 * The <tt>&#64;XmlID</tt> annotation can be used with the following
 * program elements: 
 * <ul> 
 *   <li> a JavaBean property </li>
 *   <li> non static, non transient field </li>
 * </ul>
 * 
 * <p>See "Package Specification" in javax.xml.bind.package javadoc for
 * additional common information.</p>
 *
 * The usage is subject to the following constraints:
 * <ul> 
 *   <li> At most one field or property in a class can be annotated
 *        with <tt>&#64;XmlID</tt>.  </li>
 *   <li> The JavaBean property's type must be <tt>java.lang.String</tt>.</li>
 *   <li> The only other mapping annotations that can be used
 *        with <tt>&#64;XmlID</tt>
 *        are:<tt>&#64;XmlElement</tt> and <tt>&#64;XmlAttribute</tt>.</li>  
 * </ul>
 * 
 * <p><b>Example</b>: Map a JavaBean property's type to <tt>xs:ID</tt></p>
 * <pre>
 *    // Example: code fragment
 *    public class Customer {
 *        &#64;XmlAttribute
 *        &#64;XmlID
 *        public String getCustomerID();
 *        public void setCustomerID(String id);
 *        .... other properties not shown 
 *    }
 *
 *    &lt;!-- Example: XML Schema fragment -->
 *    &lt;xs:complexType name="Customer">
 *      &lt;xs:complexContent>
 *        &lt;xs:sequence>
 *          ....
 *        &lt;/xs:sequence>
 *        &lt;xs:attribute name="customerID" type="xs:ID"/>
 *      &lt;/xs:complexContent>
 *    &lt;/xs:complexType>
 * </pre>
 *
 * @author Sekhar Vajjhala, Sun Microsystems, Inc.
 * @see XmlIDREF
 * @since JAXB2.0
 */

@Retention(RUNTIME) @Target({FIELD, METHOD})
public @interface XmlID { }



