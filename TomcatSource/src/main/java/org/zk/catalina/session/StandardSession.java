package org.zk.catalina.session;

import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSession;

import org.zk.catalina.Globals;
import org.zk.catalina.Manager;
import org.zk.catalina.Session;
import org.zk.catalina.event.SessionEvent;
import org.zk.catalina.listener.SessionListener;

/**
 * 会话接口的标准实现，这个对象是序列化的，所以它可以永久存储
 * 
 * 
 * @author King
 * 
 */
@SuppressWarnings("unused")
public abstract class StandardSession implements HttpSession, Session, Serializable{
	
	private static final long serialVersionUID = -2458412535756500800L;
	
	protected static final boolean STRICT_SERVLET_COMPLIANCE;//strict_servlet_compliance
	protected static final boolean ACTIVITY_CHECK;//activity_check
	protected static final boolean LAST_ACCESS_AT_START;//last_access_at_start
	
	static{
		STRICT_SERVLET_COMPLIANCE = Globals.STRICT_SERVLET_COMPLIANCE;
		String activityCheck = Globals.ACTIVITY_CHECK;
		if(activityCheck == null){
			ACTIVITY_CHECK = STRICT_SERVLET_COMPLIANCE;
		}else {
			ACTIVITY_CHECK = Boolean.parseBoolean(activityCheck);
		}
		String lastAccessAtStart = Globals.LAST_ACCESS_AT_START;
        if (lastAccessAtStart == null) {
            LAST_ACCESS_AT_START = STRICT_SERVLET_COMPLIANCE;
        } else {
            LAST_ACCESS_AT_START = Boolean.parseBoolean(lastAccessAtStart);
        }
	}
	private transient AtomicInteger accessCount = null;
	
	public StandardSession(Manager manager){
		this.manager = manager;
		if(ACTIVITY_CHECK){
			accessCount = new AtomicInteger();
		}
	}
	protected static final String[] empty_array = new String[0];
	protected ConcurrentHashMap<String, Object> attributes = new ConcurrentHashMap<>();
	protected transient String authType = null;
	protected long creationTime = 0l;
	protected transient volatile boolean expiring = false;
	protected transient StandardSessionFacade facade = null;
	//Session的唯一标识
	protected String id = null;
	protected volatile long lastAccessedTime = creationTime;
	protected transient List<SessionListener> listeners = new ArrayList<>();
	protected volatile boolean isNew = false;
	protected volatile boolean isValid = false;
	protected transient Manager manager = null;
	protected final transient PropertyChangeSupport support = new PropertyChangeSupport(this);
	
	public void tellNew(){
		fireSessionEvent(Session.SESSION_CREATED_EVENT, null);
	}
	
	/**
	 * Thread-Safe
	 * 
	 */
	private void fireSessionEvent(String type, Object data) {
		if(listeners.size() < 1){
			return;
		}
		SessionEvent event = new SessionEvent(this, type, data);
		SessionListener[] copyList = new SessionListener[0];
		synchronized (listeners) {
			copyList = listeners.toArray(copyList);
		}
		for(int i = 0;i < copyList.length;i++){
			copyList[i].sessionEvent(event);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}