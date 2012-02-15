package name.pehl.piriti.commons.client;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to limit the traversal of the type hirarchy on a POJO. When
 * (de)serializing a POJO all of its properties and the properties of its
 * superclasses are processed. Using this annotation you can specify up to which
 * class the processing should occur.
 * <p>
 * Suppose you have the following class hirarchy:
 * 
 * <pre>
 * class Creature {}
 * 
 * class Animal extends Creature {}
 * 
 * class Mammal extends Animal {}
 * 
 * {@code @}MapUpTo(Animal.class)
 * class Dog extends Mammal
 * {
 *     interface DogReader extends JsonReader&lt;Dog&gt; {}
 *     static final DogReader JSON = GWT.create(DogReader.class);
 * }
 * 
 * class Application implements EntryPoint
 * {
 *     public void onModuleLoad()
 *     {
 *         String json = ...;
 *         Dog dog = Dog.JSON.read(json);
 *     }
 * }
 * </pre>
 * 
 * Then properties in <code>Creature</code> won't be processed.
 * 
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 82 $
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MapUpTo
{
    Class<?> value() default Object.class;
}
