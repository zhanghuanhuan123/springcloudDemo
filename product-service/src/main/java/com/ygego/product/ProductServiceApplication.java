package com.ygego.product;

import com.ygego.product.filter.DefineFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import javax.servlet.*;
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrix
@EnableFeignClients
@MapperScan("com.ygego.product.mapper.**")
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(defineFilter());
        registration.addUrlPatterns("/*");
        registration.setName("defineFilter");
        registration.setOrder(1);
        return registration;
    }

    /**
     * 创建一个bean
     *
     * @return
     */
    @Bean(name = "defineFilter")
    public Filter defineFilter() {
        return new DefineFilter();
    }

}
