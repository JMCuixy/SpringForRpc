package org.springframework.rpc.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.BurlapServiceExporter;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.rpc.service.UserService;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import java.util.Properties;

/**
 * Created by XiuYin.Cui on 2018/5/7.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.springframework.rpc")
public class WebConfig extends WebMvcConfigurerAdapter {

    /*-Hessian-*/

    /**
     * 1、essianServiceExporter是一个Spring MVC控制器，它接收Hessian请求，
     * 并将这些请求转换成对被导出POJO的方法调用。
     * 2、Hession没有注册表，不需要设置 serviceName
     *
     * @param userService
     * @return
     */
    @Bean(name = "hessianServiceExporter")
    public HessianServiceExporter hessianServiceExporter(UserService userService) {
        HessianServiceExporter hessianServiceExporter = new HessianServiceExporter();
        hessianServiceExporter.setService(userService);
        hessianServiceExporter.setServiceInterface(UserService.class);
        return hessianServiceExporter;
    }

    /*Bualap*/

    /**
     * 1、BurlapServiceExporter 在4.0后被废弃，不再提供支持。5.0 后直接从开发包丢弃了
     *
     * @param userService
     * @return
     */
    @Bean(name = "burlapServiceExporter")
    public BurlapServiceExporter burlapServiceExporter(UserService userService) {
        BurlapServiceExporter burlapServiceExporter = new BurlapServiceExporter();
        burlapServiceExporter.setService(userService);
        burlapServiceExporter.setServiceInterface(UserService.class);
        return burlapServiceExporter;
    }

    /*Http Invoker*/
    @Bean(name = "httpInvokerServiceExporter")
    public HttpInvokerServiceExporter httpInvokerServiceExporter(UserService userService){
        HttpInvokerServiceExporter httpInvokerServiceExporter = new HttpInvokerServiceExporter();
        httpInvokerServiceExporter.setService(userService);
        httpInvokerServiceExporter.setServiceInterface(UserService.class);
        return httpInvokerServiceExporter;
    }


    /*-------------------------华丽的分割线-----------------------------*/
    /**
     * @return 需要配置一个URL映射来确保DispatcherServlet把请求转给HessianServiceExporter
     */
    @Bean(name = "handlerMapping")
    public HandlerMapping handlerMapping() {
        SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
        Properties mappings = new Properties();
        mappings.setProperty("/user.service", "hessianServiceExporter");
        mappings.setProperty("/userInvoker.service", "httpInvokerServiceExporter");
        handlerMapping.setMappings(mappings);
        return handlerMapping;
    }
}
