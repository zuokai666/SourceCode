package org.zk.catalina.event;

import java.util.EventObject;

import org.zk.catalina.Container;

/**
 * 容器有关的事件
 * 
 * @author king
 * @date 2019-05-04 20:59:24
 *
 */
public final class ContainerEvent extends EventObject {
	
	private static final long serialVersionUID = 9123200242017787129L;
	
	private final Object data;
    private final String type;
    public ContainerEvent(Container container, String type, Object data) {
        super(container);
        this.type = type;
        this.data = data;
    }
    public Object getData() {
        return this.data;
    }
    public Container getContainer() {
        return (Container) getSource();
    }
    public String getType() {
        return this.type;
    }
    @Override
    public String toString() {
        return ("ContainerEvent['" + getContainer() + "','" + getType() + "','" + getData() + "']");
    }
}