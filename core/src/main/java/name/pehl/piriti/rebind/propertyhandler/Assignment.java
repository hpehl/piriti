package name.pehl.piriti.rebind.propertyhandler;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public class Assignment
{
    private final AssignmentType type;
    private final AssignmentPolicy policy;


    public Assignment(AssignmentType type, AssignmentPolicy policy)
    {
        super();
        this.type = type;
        this.policy = policy;
    }


    public AssignmentType getType()
    {
        return type;
    }


    public AssignmentPolicy getPolicy()
    {
        return policy;
    }

    public static enum AssignmentType
    {
        ID,
        MAPPING,
        IDREF
    }

    public static enum AssignmentPolicy
    {
        FIELD_ONLY,
        PROPERTY_ONLY,
        PROPERTY_FIRST,
        FIELD_FIRST,
        GXT
    }
}
