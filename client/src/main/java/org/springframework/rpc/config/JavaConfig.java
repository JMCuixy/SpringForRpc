package org.springframework.rpc.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.rpc.rmi.RmiService;

/**
 * Created by XiuYin.Cui on 2018/5/7.
 */
@Configuration
@ComponentScan(basePackages = "org.springframework.rpc")
public class JavaConfig {


    /**
     * RmiProxyFactoryBean 是一个工厂bean，该bean可以为RMI创建服务代理
     * 客户端 通过代理对象调用
     */
    @Bean(name = "rmiServiceClient")
    public RmiProxyFactoryBean RmiServiceClient(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://127.0.0.1:1099/RmiService");
        rmiProxyFactoryBean.setServiceInterface(RmiService.class);
        rmiProxyFactoryBean.setLookupStubOnStartup(false);//不在容器启动后创建与Server端的连接
        rmiProxyFactoryBean.setRefreshStubOnConnectFailure(true);//连接出错的时候自动重连
        rmiProxyFactoryBean.afterPropertiesSet();
        return rmiProxyFactoryBean;
    }
}
