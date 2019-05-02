package org.zk.catalina;

/**
 * 个人感想：系统分层设计中，一般下层不会依赖上层，也就是不会感知上层，降低了层与层之间的耦合，但是由于业务需求
 * 下层组件需要获得上层的引用，也就是依赖，所以增加了接口，当上层实现发现下层实现了该接口时，就知道下层想要了解他，
 * 这个时候进行依赖注入，将上层注入给下层，缺点是：提高耦合。
 * 
 * 这个时候不得不提的是Spring Framework框架中的Aware接口，就是上层感知下层，自动注入
 * 因为我个人喜欢Spring的设计，所以这里将Contained接口改为了ContainerAware接口
 * 
 * @author king
 * @date 2019-05-02 12:07:54
 */
public interface ContainerAware extends Aware{
	
	Container getContainer();
    void setContainer(Container container);
}