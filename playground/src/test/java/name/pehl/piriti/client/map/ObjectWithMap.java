package name.pehl.piriti.client.map;

import java.util.Map;

public interface ObjectWithMap<K, V>
{
    Map<K, V> getMap();
}
