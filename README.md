# What the heck is piriti
Piriti ([Maori for "bridge"](http://www.maoridictionary.co.nz/index.cfm?dictionaryKeywords=bridge)) is a JSON and XML mapper for GWT based on reasonable defaults, a handful of annotations and deferred binding. The following code snippets show the basic ideas behind Piriti. 


## XML Mapping
In case you have the following XML document
```xml
<vub readonly="true">
    <name>Foo</name>
    <createdAt>08.01.2010</createdAt>
    <count>20</count>
</vub>
```

and the model class

```java
public class VirtualUserBundle
{
    interface VubReader extends XmlReader<VirtualUserBundle> {}
    public static final VubReader XML = GWT.create(VubReader.class);

    int count;
    String name;
    @Path("@readonly") boolean readonly; 
    @Format("dd.MM.yyyy") Date createdAt;
}
```

you can turn the XML into an instance of VirtualUserBundle by calling

```java
Document document = new XmlParser().parse(xmlAsString); 
VirtualUserBundle vub = VirtualUserBundle.XML.read(document);
```

## JSON Mapping
JSON mapping works very similar. In case you have the following JSON data 
```json
{ "readonly": true, "name": "Foo", "createdAt": "08.01.2010", "count": 20 }
```

and the model class

```java
public class VirtualUserBundle
{
    interface VubReader extends JsonReader<VirtualUserBundle> {}
    public static final VubReader JSON = GWT.create(VubReader.class);

    int count;
    String name;
    boolean readonly; 
    @Format("dd.MM.yyyy") Date createdAt;
}
```
you can map the JSON data to an instance of !VirtualUserBundle by calling

```java
String jsonString = ... // the above JSON data
VirtualUserBundle vub = VirtualUserBundle.JSON.read(jsonString);
```
