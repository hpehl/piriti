package name.pehl.piriti.client.map;

import com.google.gwt.core.client.GWT;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

import java.util.List;

public class PojoObject {
    interface PojoObjectWriter extends XmlWriter<PojoObject> {
    }

    interface PojoObjectReader extends XmlReader<PojoObject> {
    }

    public static final PojoObjectWriter WRITER = GWT.create(PojoObjectWriter.class);
    public static final PojoObjectReader READER = GWT.create(PojoObjectReader.class);

    private List<Integer> integerList;
    private String name;
    private boolean admin;

    public PojoObject()
    {
    }

    public PojoObject(List<Integer> integerList, String name, boolean admin) {
        this.integerList = integerList;
        this.name = name;
        this.admin = admin;
    }

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public void setIntegerList(List<Integer> integerList) {
        this.integerList = integerList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PojoObject that = (PojoObject) o;

        if (admin != that.admin) {
            return false;
        }
        if (integerList != null ? !integerList.equals(that.integerList) : that.integerList != null) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = integerList != null ? integerList.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (admin ? 1 : 0);
        return result;
    }
}
