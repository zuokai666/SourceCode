package org.zk.catalina.core;

import java.util.Set;

import org.zk.catalina.Container;
import org.zk.catalina.Pipeline;
import org.zk.catalina.Valve;

public class StandardPipeline implements Pipeline{
	
//	private final Container container;
	
	public StandardPipeline(Container container) {
//		this.container = container;
	}
	
	@Override
	public Container getContainer() {
		return null;
	}

	@Override
	public void setContainer(Container container) {
	}

	@Override
	public Valve getBasic() {
		return null;
	}

	@Override
	public void setBasic(Valve basic) {
	}

	@Override
	public void addValve(Valve valve) {
	}

	@Override
	public Valve[] getValves() {
		return null;
	}

	@Override
	public void removeValve(Valve valve) {
	}

	@Override
	public Valve getFirst() {
		return null;
	}

	@Override
	public boolean isAsyncSupported() {
		return false;
	}

	@Override
	public void findNonAsyncValves(Set<String> result) {
	}
}