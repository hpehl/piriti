package name.pehl.piriti.client.xml;

/**
 * Enum which refers to the types as specified by the W3C.
 * 
 * @see http://www.w3.org/TR/DOM-Level-2-Core/core.html#ID-1950641247
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
public enum NodeType
{
    ELEMENT(1),
    ATTRIBUTE(2),
    TEXT(3),
    CDATA(4),
    ENTITY_REFERENCE(5),
    ENTITY(6),
    PROCESSING_INSTRUCTION(7),
    COMMENT(8),
    DOCUMENT(9),
    DOCUMENT_TYPE(10),
    DOCUMENT_FRAGMENT(11),
    NOTATION(12),
    UNDEFINED(Integer.MAX_VALUE);

    private int type;


    private NodeType(int type)
    {
        this.type = type;
    }


    /**
     * The type of this node as specified by the W3C
     * 
     * @return
     */
    public int type()
    {
        return type;
    }


    /**
     * Returns the {@link NodeType} for the specified type or
     * {@link NodeType#UNDEFINED} if no type was found.
     * 
     * @param type
     * @return the {@link NodeType} for the specified type or
     *         {@link NodeType#UNDEFINED} if no type was found.
     */
    public static NodeType typeOf(int type)
    {
        for (NodeType nt : NodeType.values())
        {
            if (type == nt.type())
            {
                return nt;
            }
        }
        return NodeType.UNDEFINED;
    }
}
