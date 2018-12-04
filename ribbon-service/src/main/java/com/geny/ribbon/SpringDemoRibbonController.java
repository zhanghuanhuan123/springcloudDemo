package com.geny.ribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SpringDemoRibbonController {
    @Autowired
    SpringDemoRibbonService springDemoRibbonService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("hello")
    public String port() {
        return springDemoRibbonService.hello();
    }

    /**
     * 通过服务id获取服务的地址
     *
     * @return
     */
    @GetMapping("ribbon")
    @HystrixCommand(fallbackMethod = "ribbonFallback")
    public String ribbon() {
        return restTemplate.getForObject("http://product-service/hello", String.class);
    }

    public String ribbonFallback() {
        return "sorry ribbon, it's error!";
    }

}
