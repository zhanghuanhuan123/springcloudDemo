package com.ygego.user.rpc.service;

import com.ygego.user.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "product-service", fallbackFactory = ProductServiceFactoryImpl.class)
public interface ProductService {

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Product findById(@RequestParam(value = "id") long id);

}
