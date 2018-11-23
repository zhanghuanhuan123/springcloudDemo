package com.ygego.product.web.controller;

import com.ygego.product.model.Product;
import com.ygego.product.service.ProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;

    @ApiOperation(value = "添加用户", httpMethod = "POST", produces = "application/json")
    @ApiResponse(code = 200, message = "success", response = String.class)
    @RequestMapping("/add")
    @ResponseBody
    @HystrixCommand(fallbackMethod = "addFallback")
    public String add(Product product, String operationType) {
        try {
            productService.add(product, operationType);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public String addFallback() {
        return "addFallback";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(Product product, String operationType) {
        try {
            productService.update(product, operationType);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Long id, String operationType) {
        try {
            productService.delete(id, operationType);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
    @ApiOperation(value = "根据id查询产品信息", httpMethod = "GET")
    @ApiResponse(code = 200, message = "success", response = Product.class)
    @RequestMapping("/findById")
    @ResponseBody
    public Product findById(
            @ApiParam(name = "id", required = true, value = "产品Id") @RequestParam(value = "id") Long id) {
        try {
            logger.info("findById" + id);
            //Thread.sleep(1000);
            return productService.findById(id);
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return productService.hello();
    }

    @RequestMapping("/port")
    @ResponseBody
    public String port() {
        return productService.hello();
    }
}
