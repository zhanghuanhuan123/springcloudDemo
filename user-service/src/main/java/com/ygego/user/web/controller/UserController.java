package com.ygego.user.web.controller;

import java.util.List;
import java.util.Random;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.ygego.user.model.Product;
import com.ygego.user.model.User;
import com.ygego.user.rpc.service.ProductService;
import com.ygego.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService iUserService;
    @Autowired
    private ProductService productService;
    @RequestMapping("/findAllUsers")
    @ResponseBody
    @HystrixCommand
    public List<User> findAllUsers() {
        Product product =productService.findById(1);
        logger.info(product.getId()+"======="+product.getBrandId());
        return iUserService.findAllUsers();
    }

    /**
     * 添加一个student,使用postMapping接收post请求
     * <p>
     * http://localhost:8330/simple/addUser?username=user11&age=11&balance=11
     *
     * @return
     */
    @GetMapping("/simple/addUser")
    @HystrixCommand(fallbackMethod = "addUserFallback", ignoreExceptions = {
            Exception.class }, groupKey = "UserGroup", commandKey = "addUser", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500") }, threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "30"), @HystrixProperty(name = "maxQueueSize", value = "101"),
            @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
            @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
            @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440") })
    public User addUser() throws Exception {
        User user = new User();
        try {

            user.setId(new Random().nextLong());
            user.setName("wwww");
            user.setAge(new Random().nextInt());
            int result = iUserService.insertUser(user);
            if (result > 0) {
                return user;
            }
        } catch (Exception e) {
            logger.error("", e);
        }

        return user;
    }

    public User addUserFallback() {
        User user = new User();
        user.setId(new Random().nextLong());
        user.setName("addUserFallback");
        user.setAge(new Random().nextInt());
        return user;
    }
}
