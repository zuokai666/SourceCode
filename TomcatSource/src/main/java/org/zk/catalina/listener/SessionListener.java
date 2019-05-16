package org.zk.catalina.listener;

import java.util.EventListener;

import org.zk.catalina.event.SessionEvent;

public interface SessionListener extends EventListener {


    /**
     * Acknowledge the occurrence of the specified event.
     *
     * @param event SessionEvent that has occurred
     */
    public void sessionEvent(SessionEvent event);
}