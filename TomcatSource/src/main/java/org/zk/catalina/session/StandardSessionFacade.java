package org.zk.catalina.session;

import javax.servlet.http.HttpSession;

public abstract class StandardSessionFacade implements HttpSession{
	
	private HttpSession session;

	public StandardSessionFacade(HttpSession session) {
		this.session = session;
	}
}