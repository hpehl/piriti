package name.pehl.piriti.client.events;

import name.pehl.piriti.commons.client.ReadModelEvent;
import name.pehl.piriti.commons.client.ReadModelHandler;
import name.pehl.piriti.commons.client.WriteModelEvent;
import name.pehl.piriti.commons.client.WriteModelHandler;

public class CountHandler implements WriteModelHandler<Person>, ReadModelHandler<Person>
{
    int readCount = 0;
    int writeCount = 0;


    @Override
    public void onReadModel(ReadModelEvent<Person> event)
    {
        readCount++;
    }


    @Override
    public void onWriteModel(WriteModelEvent<Person> event)
    {
        writeCount++;
    }
}
