package org.springframework.rpc.httpinvoker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.rpc.config.JavaConfig;
import org.springframework.rpc.model.User;
import org.springframework.rpc.service.UserService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by XiuYin.Cui on 2018/5/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JavaConfig.class)
public class HessianClientTest {

    private static Logger logger = LoggerFactory.getLogger(HessianClientTest.class);

    @Resource(name="httpInvokerUserServiceClient")
    private UserService userService;


    @Test
    public void test(){
        List<User> list = userService.listInfo();
        if (list != null && list.size() > 0){
            logger.info("集合的长度：{}", list.size());
            for (User user : list){
                logger.info(user.toString());
            }
        }
    }
}
