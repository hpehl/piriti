package name.pehl.piriti.json.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import name.pehl.piriti.commons.client.Node;

/**
 * A registry for {@linkplain JsonReader}s and {@linkplain JsonWriter}s. All
 * readers and writers are registered against this registry. Thereby references
 * between POJOs can be resolved.
 *
 * @author $LastChangedBy: harald.pehl $
 * @version $LastChangedRevision: 8 $
 */
public final class JsonRegistry
{
    private final Map<Class<?>, JsonReader<?>> readers;
    private final Map<Class<?>, JsonWriter<?>> writers;
    private final Map<Class<?>, Node<Class<?>, JsonReader<?>>> typedReaders;
    private final Map<Class<?>, Node<Class<?>, JsonWriter<?>>> typedWriters;


    public JsonRegistry()
    {
        readers = new HashMap<Class<?>, JsonReader<?>>();
        writers = new HashMap<Class<?>, JsonWriter<?>>();
        typedReaders = new HashMap<Class<?>, Node<Class<?>, JsonReader<?>>>();
        typedWriters = new HashMap<Class<?>, Node<Class<?>, JsonWriter<?>>>();
    }


    /**
     * Registers the {@link JsonReader} for the specified type
     *
     * @param <T>
     *            The type
     * @param clazz
     *            The class
     * @param reader
     *            The {@link JsonReader}
     */
    public <T> void register(Class<T> clazz, JsonReader<T> reader)
    {
        readers.put(clazz, reader);
    }

    public <T> void register(Class<T> clazz, List<Class<?>> typeClasses, JsonReader reader)
    {
        Node<Class<?>, JsonReader<?>> typeTree = typedReaders.get(clazz);
        if (typeTree == null)
        {
            typeTree = new Node<Class<?>, JsonReader<?>>(clazz);
        }

        registerPath(typeTree, typeClasses, reader);
        typedReaders.put(clazz, typeTree);
    }

    public <T> void register(Class<T> clazz, List<Class<?>> typeClasses, JsonWriter writer)
    {
        Node<Class<?>, JsonWriter<?>> typeTree = typedWriters.get(clazz);
        if (typeTree == null)
        {
            typeTree = new Node<Class<?>, JsonWriter<?>>(clazz);
        }

        registerPath(typeTree, typeClasses, writer);
        typedWriters.put(clazz, typeTree);
    }

    private <T> void registerPath(Node<Class<?>, T> node, List<Class<?>> typeClasses, T value) {
        Node<Class<?>, T> child = node.addChildNode(typeClasses.get(0));
        if (typeClasses.size() > 1) {
            registerPath(child, typeClasses.subList(1, typeClasses.size()), value);
        } else {
            child.setValue(value);
        }
    }


    /**
     * Registers the {@link JsonWriter} for the specified type
     *
     * @param <T>
     *            The type
     * @param clazz
     *            The class
     * @param writer
     *            The {@link JsonWriter}
     */
    public <T> void register(Class<T> clazz, JsonWriter<T> writer)
    {
        writers.put(clazz, writer);
    }


    /**
     * Returns the {@link JsonReader} for the specified type
     *
     * @param <T>
     * @param clazz
     * @return The {@link JsonReader} or {@code null} if no {@link JsonReader}
     *         is found
     */
    @SuppressWarnings("unchecked")
    public <T> JsonReader<T> getReader(Class<T> clazz)
    {
        return (JsonReader<T>) readers.get(clazz);
    }


    /**
     * Returns the {@link JsonWriter} for the specified type
     *
     * @param <T>
     * @param clazz
     * @return The {@link JsonWriter} or {@code null} if no {@link JsonWriter}
     *         is found
     */
    @SuppressWarnings("unchecked")
    public <T> JsonWriter<T> getWriter(Class<T> clazz)
    {
        return (JsonWriter<T>) writers.get(clazz);
    }

    @SuppressWarnings("unchecked")
    public <T> JsonWriter getWriter(Class<T> clazz, List<Class<?>> typeClasses)
    {
        JsonWriter writer = getWriter(clazz);
        if (writer == null)
        {
            Node<Class<?>, JsonWriter<?>> typeTree = typedWriters.get(clazz);
            writer = getFromPath(typeTree, typeClasses);
        }
        return writer;
    }

    @SuppressWarnings("unchecked")
    public <T> JsonReader getReader(Class<T> clazz, List<Class<?>> typeClasses)
    {
        JsonReader reader = getReader(clazz);
        if (reader == null)
        {
            Node<Class<?>, JsonReader<?>> typeTree = typedReaders.get(clazz);
            reader = getFromPath(typeTree, typeClasses);
        }
        return reader;
    }

    private <T> T getFromPath(Node<Class<?>, T> typeTree, List<Class<?>> typeClasses)
    {
        Node<Class<?>, T> childNode = typeTree.findChildNode(typeClasses.get(0));
        if (typeClasses.size() > 1)
        {
            return getFromPath(childNode, typeClasses.subList(1, typeClasses.size()));
        } else
        {
            return childNode.getValue();
        }
    }
}
