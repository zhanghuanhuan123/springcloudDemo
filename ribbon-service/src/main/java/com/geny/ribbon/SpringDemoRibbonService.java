package com.geny.ribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpringDemoRibbonService {
    private static final Logger logger = LoggerFactory.getLogger(SpringDemoRibbonService.class);
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String hello() {
        //this.loadBalancerClient.choose("product-service");  //随机访问策略
        String result = restTemplate.getForObject("http://product-service/hello", String.class);
        logger.info("ribbon service rpc product-service:hello call back result---------->" + result);
        return result;
    }

    public String helloFallback() {
        return "sorry ribbon, it's error!";
    }
}
