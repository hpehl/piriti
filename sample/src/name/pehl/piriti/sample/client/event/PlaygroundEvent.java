package name.pehl.piriti.sample.client.event;

import name.pehl.piriti.sample.client.model.Playground;
import name.pehl.piriti.sample.client.util.TimeInterval;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-03-25 17:01:09 +0100 (Do, 25 Mrz 2010) $ $Revision: 280
 *          $
 */
public class PlaygroundEvent extends GwtEvent<PlaygroundHandler>
{
    private static Type<PlaygroundHandler> TYPE;


    public static Type<PlaygroundHandler> getType()
    {
        if (TYPE == null)
        {
            TYPE = new Type<PlaygroundHandler>();
        }
        return TYPE;
    }

    private final Playground playground;
    private final TimeInterval timeInterval;
    private final String sourceCode;


    public PlaygroundEvent(Playground playground, TimeInterval timeInterval, String sourceCode)
    {
        super();
        this.playground = playground;
        this.timeInterval = timeInterval;
        this.sourceCode = sourceCode;
    }


    @Override
    protected void dispatch(PlaygroundHandler handler)
    {
        handler.onPlayground(this);
    }


    @Override
    public Type<PlaygroundHandler> getAssociatedType()
    {
        return getType();
    }


    public Playground getPlayground()
    {
        return playground;
    }


    public TimeInterval getTimeInterval()
    {
        return timeInterval;
    }


    public String getSourceCode()
    {
        return sourceCode;
    }
}
