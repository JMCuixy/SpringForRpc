package org.springframework.rpc.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.rpc.rmi.RmiService;

/**
 * Created by XiuYin.Cui on 2018/5/7.
 */
@Configuration
public class JavaConfig {


    /**
     * 服务端：
     * 默认情况下，RmiServiceExporter 会尝试绑定到本地机器1099端口上的RMI注册表。
     * 如果在这个端口没有发现RMI注册表，RmiServiceExporter 将会启动一个注册表。
     * 可重写注册表的路径和端口
     * @param rmiService
     * @return
     */
    @Bean(name = "rmiServiceExporter")
    public RmiServiceExporter rmiServiceExporter(RmiService rmiService){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setService(rmiService); //要把该bean发布为一个RMI服务
        rmiServiceExporter.setServiceName("rmiService"); //命名RMI 服务
        rmiServiceExporter.setServiceInterface(RmiService.class); //指定服务所实现的接口
        rmiServiceExporter.setRegistryHost("127.0.0.1");
        rmiServiceExporter.setRegistryPort(1099);
        return rmiServiceExporter;
    }

    /**
     * 客户端 通过代理对象调用
     */
    @Bean(name = "rmiServiceClient")
    public RmiProxyFactoryBean RmiServiceClient(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost/rmiService");
        rmiProxyFactoryBean.setServiceInterface(RmiService.class);
        return rmiProxyFactoryBean;
    }
}
