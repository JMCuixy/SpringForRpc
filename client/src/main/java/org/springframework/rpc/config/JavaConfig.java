package org.springframework.rpc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.rpc.service.UserService;

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
    @Bean(name = "rmiUserServiceClient")
    public RmiProxyFactoryBean RmiUserServiceClient(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://127.0.0.1:9999/RmiService");
        rmiProxyFactoryBean.setServiceInterface(UserService.class);
        rmiProxyFactoryBean.setLookupStubOnStartup(false);//不在容器启动后创建与Server端的连接
        rmiProxyFactoryBean.setRefreshStubOnConnectFailure(true);//连接出错的时候自动重连
        rmiProxyFactoryBean.afterPropertiesSet();
        return rmiProxyFactoryBean;
    }

    @Bean(name = "hessianUserServiceClient")
    public HessianProxyFactoryBean hessianUserServiceClient(){
        HessianProxyFactoryBean proxy = new HessianProxyFactoryBean();
        proxy.setServiceUrl("http://127.0.0.1:8080/user.service");
        proxy.setServiceInterface(UserService.class);
        return proxy;
    }

    @Bean(name = "httpInvokerUserServiceClient")
    public HttpInvokerProxyFactoryBean httpInvokerUserServiceClient(){
        HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
        proxy.setServiceUrl("http://127.0.0.1:8080//userInvoker.service");
        proxy.setServiceInterface(UserService.class);
        return proxy;
    }
}
