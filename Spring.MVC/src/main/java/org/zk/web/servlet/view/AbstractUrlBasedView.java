package org.zk.web.servlet.view;

public abstract class AbstractUrlBasedView extends AbstractView {
	
	private String url;
	
	//检查必要参数
	public void afterPropertiesSet() throws Exception {
		if(isUrlRequired() && getUrl() == null){
			throw new IllegalArgumentException("url是必须的");//开闭原则，充分的扩展性，如果子类不需要url，直接可以设置false，厉害厉害
		}
	}
	
	protected boolean isUrlRequired(){
		return true;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}