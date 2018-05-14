package org.springframework.rpc.rmi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.rpc.config.JavaConfig;

/**
 * Created by XiuYin.Cui on 2018/5/14.
 */
public class RmiServerStart {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("服务启动中...");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(JavaConfig.class);
        applicationContext.getBean("rmiServiceExporter");
        System.out.println("服务启动完毕...");
    }
}
