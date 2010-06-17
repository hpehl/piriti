/**
 * Copyright (c) 2010 olivier.monaco@free.fr
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package name.pehl.piriti.client.json.internal;

/**
 * This implementation is a port of http://www.JSON.org/json2.js. It is used on
 * browser not supporting the JSON top-level object and it's methods
 * <tt>parse</tt> and <tt>stringify</tt>. </p>
 * <p>
 * <strong>JavaScript file header</strong>
 * </p>
 * <p>
 * 2010-03-20
 * </p>
 * <p>
 * Public Domain.
 * </p>
 * <p>
 * NO WARRANTY EXPRESSED OR IMPLIED. USE AT YOUR OWN RISK.
 * </p>
 * <p>
 * See http://www.JSON.org/js.html
 * </p>
 * <p>
 * This code should be minified before deployment. See
 * http://javascript.crockford.com/jsmin.html
 * </p>
 * <p>
 * USE YOUR OWN COPY. IT IS EXTREMELY UNWISE TO LOAD CODE FROM SERVERS YOU DO
 * NOT CONTROL.
 * </p>
 * <p>
 * This file creates a global JSON object containing two methods: stringify and
 * parse.
 * </p>
 * 
 * <pre>
 * JSON.stringify(value, replacer, space)
 *         value       any JavaScript value, usually an object or array.
 * 
 *         replacer    an optional parameter that determines how object
 *                     values are stringified for objects. It can be a
 *                     function or an array of strings.
 * 
 *         space       an optional parameter that specifies the indentation
 *                     of nested structures. If it is omitted, the text will
 *                     be packed without extra whitespace. If it is a number,
 *                     it will specify the number of spaces to indent at each
 *                     level. If it is a string (such as '\t' or '&nbsp;'),
 *                     it contains the characters used to indent at each level.
 * 
 *         This method produces a JSON text from a JavaScript value.
 * 
 *         When an object value is found, if the object contains a toJSON
 *         method, its toJSON method will be called and the result will be
 *         stringified. A toJSON method does not serialize: it returns the
 *         value represented by the name/value pair that should be serialized,
 *         or undefined if nothing should be serialized. The toJSON method
 *         will be passed the key associated with the value, and this will be
 *         bound to the value
 * 
 *         For example, this would serialize Dates as ISO strings.
 * 
 *             Date.prototype.toJSON = function (key) {
 *                 function f(n) {
 *                     // Format integers to have at least two digits.
 *                     return n < 10 ? '0' + n : n;
 *                 }
 * 
 *                 return this.getUTCFullYear()   + '-' +
 *                      f(this.getUTCMonth() + 1) + '-' +
 *                      f(this.getUTCDate())      + 'T' +
 *                      f(this.getUTCHours())     + ':' +
 *                      f(this.getUTCMinutes())   + ':' +
 *                      f(this.getUTCSeconds())   + 'Z';
 *             };
 * 
 *         You can provide an optional replacer method. It will be passed the
 *         key and value of each member, with this bound to the containing
 *         object. The value that is returned from your method will be
 *         serialized. If your method returns undefined, then the member will
 *         be excluded from the serialization.
 * 
 *         If the replacer parameter is an array of strings, then it will be
 *         used to select the members to be serialized. It filters the results
 *         such that only members with keys listed in the replacer array are
 *         stringified.
 * 
 *         Values that do not have JSON representations, such as undefined or
 *         functions, will not be serialized. Such values in objects will be
 *         dropped; in arrays they will be replaced with null. You can use
 *         a replacer function to replace those with JSON values.
 *         JSON.stringify(undefined) returns undefined.
 * 
 *         The optional space parameter produces a stringification of the
 *         value that is filled with line breaks and indentation to make it
 *         easier to read.
 * 
 *         If the space parameter is a non-empty string, then that string will
 *         be used for indentation. If the space parameter is a number, then
 *         the indentation will be that many spaces.
 * 
 *         Example:
 * 
 *         text = JSON.stringify(['e', {pluribus: 'unum'}]);
 *         // text is '["e",{"pluribus":"unum"}]'
 * 
 * 
 *         text = JSON.stringify(['e', {pluribus: 'unum'}], null, '\t');
 *         // text is '[\n\t"e",\n\t{\n\t\t"pluribus": "unum"\n\t}\n]'
 * 
 *         text = JSON.stringify([new Date()], function (key, value) {
 *             return this[key] instanceof Date ?
 *                 'Date(' + this[key] + ')' : value;
 *         });
 *         // text is '["Date(---current time---)"]'
 * 
 * 
 *     JSON.parse(text, reviver)
 *         This method parses a JSON text to produce an object or array.
 *         It can throw a SyntaxError exception.
 * 
 *         The optional reviver parameter is a function that can filter and
 *         transform the results. It receives each of the keys and values,
 *         and its return value is used instead of the original value.
 *         If it returns what it received, then the structure is not modified.
 *         If it returns undefined then the member is deleted.
 * 
 *         Example:
 * 
 *         // Parse the text. Values that look like ISO date strings will
 *         // be converted to Date objects.
 * 
 *         myData = JSON.parse(text, function (key, value) {
 *             var a;
 *             if (typeof value === 'string') {
 *                 a =
 * /^(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2}(?:\.\d*)?)Z$/.exec(value);
 *                 if (a) {
 *                     return new Date(Date.UTC(+a[1], +a[2] - 1, +a[3], +a[4],
 *                         +a[5], +a[6]));
 *                 }
 *             }
 *             return value;
 *         });
 * 
 *         myData = JSON.parse('["Date(09/09/2001)"]', function (key, value) {
 *             var d;
 *             if (typeof value === 'string' &&
 *                     value.slice(0, 5) === 'Date(' &&
 *                     value.slice(-1) === ')') {
 *                 d = new Date(value.slice(5, -1));
 *                 if (d) {
 *                     return d;
 *                 }
 *             }
 *             return value;
 *         });
 * </pre>
 * <p>
 * This is a reference implementation. You are free to copy, modify, or
 * redistribute.
 * </p>
 * 
 * @see "http://www.json.org/json2.js"
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class EmulatedJsonParserImpl extends NativeJsonParserImpl
{
    static
    {
        init();
    }


    /**
     * Creates the JSON top-level object and its methods <tt>parse</tt> and
     * <tt>stringify</tt>. It doesn't override any existing one, allowing to use
     * this class only to add missing methods.
     */
    private native static void init()
    /*-{
        if (!$wnd.JSON) {
            $wnd.JSON = {};
        }

        (function() {
            // Format integers to have at least two digits.
            function f(n) {
                return n < 10 ? '0' + n : n;
            }

            if (typeof(Date.prototype.toJSON) !== 'function') {
                Date.prototype.toJSON = function (key) {
                    if (isFinite(this.valueOf())) {
                        return this.getUTCFullYear()  + '-' +
                            f(this.getUTCMonth() + 1) + '-' +
                            f(this.getUTCDate())      + 'T' +
                            f(this.getUTCHours())     + ':' +
                            f(this.getUTCMinutes())   + ':' +
                            f(this.getUTCSeconds())   + 'Z';
                    }
                    return null;
                };
                String.prototype.toJSON =
                Number.prototype.toJSON =
                Boolean.prototype.toJSON = function (key) {
                    return this.valueOf();
                };
            }

            var escapeRegex = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g;
            var unicodeRegex = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g;

            function escapeFunc(c) {
                switch (c) {
                    case '\b': return '\\b';
                    case '\t': return '\\t';
                    case '\n': return '\\n';
                    case '\f': return '\\f';
                    case '\r': return '\\r';
                    case '"': return '\\"';
                    case '\\': return '\\\\';
                    default:
                    return '\\u' + ('0000' + c.charCodeAt(0).toString(16)).slice(-4);
                }
            };

            function str(key, holder, gap, indent, replacer) {
                var value = holder[key];

                // If the value has a toJSON method, call it to obtain a replacement value.
                if (value && (typeof(value) === 'object') &&
                    (typeof(value.toJSON) === 'function')) {
                    value = value.toJSON(key);
                }

                // If we were called with a replacer function, then call the replacer to
                // obtain a replacement value.
                if (typeof(replacer) === 'function') {
                    value = replacer.call(holder, key, value);
                }

                // What happens next depends on the value's type.
                switch (typeof(value)) {
                    case 'string':
                        return quote(value);

                    // JSON numbers must be finite. Encode non-finite numbers as null.
                    case 'number':
                        return isFinite(value) ? String(value) : 'null';

                    // If the value is a boolean or null, convert it to a string. Note:
                    // typeof null does not produce 'null'. The case is included here in
                    // the remote chance that this gets fixed someday.
                    case 'boolean':
                    case 'null':
                        return String(value);

                    // If the type is 'object', we might be dealing with an object or an array or
                    // null.
                    case 'object':
                        // Due to a specification blunder in ECMAScript, typeof null is 'object',
                        // so watch out for that case.
                        if (!value) {
                            return 'null';
                        }

                        // Make an array to hold the partial results of stringifying this object value.
                        gap += indent;
                        var mind = gap;
                        var partial = [];

                        // Is the value an array?
                        if (Object.prototype.toString.apply(value) === '[object Array]') {
                            // The value is an array. Stringify every element. Use null as a placeholder
                            // for non-JSON values.
                            var length = value.length;
                            for (var i = 0; i < length; i += 1) {
                                partial[i] = str(i, value, gap, indent, replacer) || 'null';
                            }

                            // Join all of the elements together, separated with commas, and wrap them in
                            // brackets.
                            if (partial.length === 0) {
                                return '[]';
                            }
                            else if (gap) {
                                return '[\n' + gap + partial.join(',\n' + gap) + '\n' + mind + ']';
                            }
                            return '[' + partial.join(',') + ']';
                        }

                        // If the replacer is an array, use it to select the members to be stringified.
                        if (replacer && (typeof(replacer) === 'object')) {
                            var length = replacer.length;
                            for (var i = 0; i < length; i += 1) {
                                var k = replacer[i];
                                if (typeof(k) === 'string') {
                                    var v = str(k, value, gap, index, replacer);
                                    if (v) {
                                        partial.push(quote(k) + (gap ? ': ' : ':') + v);
                                    }
                                }
                            }
                        }
                        // Otherwise, iterate through all of the keys in the object.
                        else {
                            for (var k in value) {
                                if (Object.hasOwnProperty.call(value, k)) {
                                    var v = str(k, value, gap, indent, replacer);
                                    if (v) {
                                        partial.push(quote(k) + (gap ? ': ' : ':') + v);
                                    }
                                }
                            }
                        }

                        // Join all of the member texts together, separated with commas,
                        // and wrap them in braces.
                        if (partial.length === 0) {
                            return '{}';
                        }
                        else if (gap) {
                            return '{\n' + gap + partial.join(',\n' + gap) + '\n' +
                            mind + '}';
                        }
                        return '{' + partial.join(',') + '}';
                }
            }

            function parseImpl(text, reviver) {
                // Parsing happens in four stages. In the first stage, we replace certain
                // Unicode characters with escape sequences. JavaScript handles many characters
                // incorrectly, either silently deleting them, or treating them as line endings.        
                unicodeRegex.lastIndex = 0;
                text = text.replace(unicodeRegex, escapeFunc);

                // In the second stage, we run the text against regular expressions that look
                // for non-JSON patterns. We are especially concerned with '()' and 'new'
                // because they can cause invocation, and '=' because it can cause mutation.
                // But just to be safe, we want to reject all unexpected forms.

                // We split the second stage into 4 regexp operations in order to work around
                // crippling inefficiencies in IE's and Safari's regexp engines. First we
                // replace the JSON backslash pairs with '@' (a non-JSON character). Second, we
                // replace all simple value tokens with ']' characters. Third, we delete all
                // open brackets that follow a colon or comma or that begin the text. Finally,
                // we look to see that the remaining characters are only whitespace or ']' or
                // ',' or ':' or '{' or '}'. If that is so, then the text is safe for eval.
                var regexp1 = /\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g;
                var regexp2 = /"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g;
                var regexp3 = /(?:^|:|,)(?:\s*\[)+/g;
                var regexp4 = /^[\],:{}\s]*$/;
                var s = text.replace(regexp1, '@').replace(regexp2, ']').replace(regexp3, '');
                if (regexp4.test(s)) { 
                    // In the third stage we use the eval function to compile the text into a
                    // JavaScript structure. The '{' operator is subject to a syntactic ambiguity
                    // in JavaScript: it can begin a block or an object literal. We wrap the text
                    // in parens to eliminate the ambiguity.
                    var j = eval('(' + text + ')');

                    // In the optional fourth stage, we recursively walk the new structure, passing
                    // each name/value pair to a reviver function for possible transformation.
                    return typeof(reviver) === 'function' ?
                        walk({'': j}, '') : j;
                }

                // If the text is not JSON parseable, then a SyntaxError is thrown.
                throw new SyntaxError('JSON.parse: unsupported');
            }


            function quote(text) {
                escapeRegex.lastIndex = 0;
                return '"' + text.replace(escapeRegex, escapeFunc) + '"';
            }

            function stringifyImpl(value, replacer, space) {
                var indent = '';

                // If the space parameter is a number, make an indent string containing that
                // many spaces.
                if (typeof(space) === 'number') {
                    for (var i = 0 ; i < space; ++i) {
                        indent += ' ';
                    }
                }

                // If the space parameter is a string, it will be used as the indent string.
                else if (typeof space === 'string') {
                    indent = space;
                }

                // If there is a replacer, it must be a function or an array.
                // Otherwise, throw an error.
                if (replacer && (typeof(replacer) !== 'function') &&
                    ((typeof(replacer) !== 'object') ||
                     (typeof(replacer.length) !== 'number'))) {
                    throw new Error('JSON.stringify: invalid replacer');
                }

                // Make a fake root object containing our value under the key of ''.
                // Return the result of stringifying the value.
                return str('', {'': value}, '', indent, replacer);
            }

            if (typeof(JSON.parse) !== 'function') {
                $wnd.JSON.parse = parseImpl;
            }
            if (typeof(JSON.stringify) !== 'function') {
                $wnd.JSON.stringify = stringifyImpl;
            }
        })();
    }-*/;
}
