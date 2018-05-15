package org.springframework.rpc.config;

import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.rpc.rmi.RmiExporter;
import org.springframework.rpc.service.UserService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by XiuYin.Cui on 2018/5/15.
 */
@Configuration
@ComponentScan(basePackages = {"org.springframework.rpc"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
        })
@PropertySource("classpath:resources.properties")
public class JavaConfig {

    /*-RMI-*/

    /**
     * 服务端：
     * RmiServiceExporter把bean 包装在一个适配器类中，然后适配器被绑定到RMI注册表中
     * <p>
     * 1、默认情况下，RmiServiceExporter 会尝试绑定到本地机器1099端口上的RMI注册表。
     * 2、如果在这个端口没有发现RMI注册表，RmiServiceExporter 将会启动一个注册表。
     * 3、可重写注册表的路径和端口,这个是个大坑，当你设置了registryHost属性的时候，
     * 源码中就不创建Registry，而是直接去获取，可是我们自己也没有创建，所以就会报连接不上。
     *
     * @param userService
     * @return
     */
    @Bean(name = "rmiServiceExporter")
    public RmiExporter rmiServiceExporter(UserService userService, Environment environment) {
        String registryHost = environment.getProperty("registryHost");
        int registryPort = environment.getProperty("registryPort", Integer.class);
        RmiExporter rmiExporter = new RmiExporter();
        rmiExporter.setService(userService); //要把该bean(即rmiServiceImpl)发布为一个RMI服务
        rmiExporter.setServiceName("RmiService"); //命名RMI 服务
        rmiExporter.setServiceInterface(UserService.class); //指定服务所实现的接口
        rmiExporter.setRegistryHost(registryHost);
        rmiExporter.setRegistryPort(registryPort);
        return rmiExporter;
    }

}
