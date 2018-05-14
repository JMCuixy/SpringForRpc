package org.springframework.rpc.rmi;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.rpc.config.JavaConfig;
import org.springframework.rpc.model.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by XiuYin.Cui on 2018/5/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JavaConfig.class)
public class RmiClientTest {

    private static Logger logger = LoggerFactory.getLogger(RmiClientTest.class);

    @Resource(name="rmiServiceClient")
    private RmiService rmiServiceClient;


    @Test
    public void test(){
        List<User> list = rmiServiceClient.listInfo();
        if (list != null && list.size() > 0){
            logger.info("集合的长度：{}", list.size());
            for (User user : list){
                logger.info(user.toString());
            }
        }

    }
}
