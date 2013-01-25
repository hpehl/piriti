package name.pehl.piriti.client.map;

import com.google.gwt.core.client.GWT;
import name.pehl.piriti.xml.client.XmlReader;
import name.pehl.piriti.xml.client.XmlWriter;

public class Result<T> {
    public static class PojoResult extends Result<PojoObject>
    {
        private String testField;

        PojoResult(){}

        public PojoResult(PojoObject object) {
            super(object);
        }

        public String getTestField() {
            return testField;
        }

        public void setTestField(String testField) {
            this.testField = testField;
        }

        interface ResultWriter extends XmlWriter<PojoResult> {
        }

        interface ResultReader extends XmlReader<PojoResult> {
        }

        public static final ResultWriter WRITER = GWT.create(ResultWriter.class);
        public static final ResultReader READER = GWT.create(ResultReader.class);
    }

    private T object;

    Result() {
    }

    public Result(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
