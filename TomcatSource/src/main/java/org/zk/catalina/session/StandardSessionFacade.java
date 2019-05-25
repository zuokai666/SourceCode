package org.zk.catalina.session;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

@SuppressWarnings("deprecation")
public class StandardSessionFacade implements HttpSession{
	
	private HttpSession session;
	
	public StandardSessionFacade(HttpSession session) {
		this.session = session;
	}
	
	@Override
	public long getCreationTime() {
		return session.getCreationTime();
	}

	@Override
	public String getId() {
		return session.getId();
	}

	@Override
	public long getLastAccessedTime() {
		return session.getLastAccessedTime();
	}

	@Override
	public ServletContext getServletContext() {
		return session.getServletContext();
	}

	@Override
	public void setMaxInactiveInterval(int interval) {
	}

	@Override
	public int getMaxInactiveInterval() {
		return 0;
	}

	
	@Override
	public HttpSessionContext getSessionContext() {
		return null;
	}

	@Override
	public Object getAttribute(String name) {
		return null;
	}

	@Override
	public Object getValue(String name) {
		return null;
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return null;
	}

	@Override
	public String[] getValueNames() {
		return null;
	}

	@Override
	public void setAttribute(String name, Object value) {
	}

	@Override
	public void putValue(String name, Object value) {
	}

	@Override
	public void removeAttribute(String name) {
	}

	@Override
	public void removeValue(String name) {
	}

	@Override
	public void invalidate() {
	}

	@Override
	public boolean isNew() {
		return false;
	}
}