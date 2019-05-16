package org.zk.catalina.event;

import java.util.EventObject;

import org.zk.catalina.Session;

public final class SessionEvent extends EventObject {

    private static final long serialVersionUID = 1L;


    /**
     * The event data associated with this event.
     */
    private final Object data;


    /**
     * The Session on which this event occurred.
     */
    private final Session session;


    /**
     * The event type this instance represents.
     */
    private final String type;


    /**
     * Construct a new SessionEvent with the specified parameters.
     *
     * @param session Session on which this event occurred
     * @param type Event type
     * @param data Event data
     */
    public SessionEvent(Session session, String type, Object data) {

        super(session);
        this.session = session;
        this.type = type;
        this.data = data;

    }


    /**
     * @return the event data of this event.
     */
    public Object getData() {

        return (this.data);

    }


    /**
     * @return the Session on which this event occurred.
     */
    public Session getSession() {

        return (this.session);

    }


    /**
     * @return the event type of this event.
     */
    public String getType() {

        return (this.type);

    }


    @Override
    public String toString() {

        return ("SessionEvent['" + getSession() + "','" +
                getType() + "']");

    }


}