package name.pehl.piriti.client.ferranmaylinch;


public abstract class Property
{
    String name;
    String type;


    public Property()
    {
        this(null, null);
    }


    public Property(String name, String type)
    {
        this.name = name;
        this.type = type;
    }
}
