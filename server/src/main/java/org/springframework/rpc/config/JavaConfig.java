package org.springframework.rpc.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.rpc.rmi.RmiExporter;
import org.springframework.rpc.rmi.RmiService;
import org.springframework.rpc.rmi.impl.RmiServiceImpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by XiuYin.Cui on 2018/5/7.
 */
@Configuration
@ComponentScan(basePackages = "org.springframework.rpc")
@PropertySource("classpath:resources.properties")
public class JavaConfig {


    /**
     * 服务端：
     * RmiServiceExporter把bean 包装在一个适配器类中，然后适配器被绑定到RMI注册表中
     * <p>
     * 1、默认情况下，RmiServiceExporter 会尝试绑定到本地机器1099端口上的RMI注册表。
     * 2、如果在这个端口没有发现RMI注册表，RmiServiceExporter 将会启动一个注册表。
     * 3、可重写注册表的路径和端口,这个是个大坑，当你设置了registryHost属性的时候，
     *    源码中就不创建Registry，而是直接去获取，可是我们自己也没有创建，所以就会报连接不上。
     *
     * @param rmiService
     * @return
     */
    @Bean(name = "rmiServiceExporter")
    public RmiExporter rmiServiceExporter(RmiService rmiService, Environment environment) {
        String registryHost = environment.getProperty("registryHost");
        int registryPort = environment.getProperty("registryPort", Integer.class);
        RmiExporter rmiExporter = new RmiExporter();
        rmiExporter.setService(rmiService); //要把该bean(即rmiServiceImpl)发布为一个RMI服务
        rmiExporter.setServiceName("RmiService"); //命名RMI 服务
        rmiExporter.setServiceInterface(RmiService.class); //指定服务所实现的接口
        rmiExporter.setRegistryHost(registryHost);
        rmiExporter.setRegistryPort(registryPort);
        return rmiExporter;
    }

    public static void main(String[] args) {
        Properties properties = System.getProperties();
        Iterator<Map.Entry<Object, Object>> iterator = properties.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Object, Object> entry = iterator.next();
            System.out.println("key:" + entry.getKey() + ",value:" + entry.getValue());
        }

    }
}
