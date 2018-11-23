package com.ygego.product.service.impl;

import com.ygego.product.model.Product;
import com.ygego.product.service.ProductService;
import com.ygego.product.mapper.ProductMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    public void add(Product product, String operationType) {
        productMapper.add(product);

	/*	String queue = null;
		
		if(operationType == null || "".equals(operationType)) {
			queue = RabbitQueue.DATA_CHANGE_QUEUE;
		} else if("refresh".equals(operationType)) {
			queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
		} else if("high".equals(operationType)) {
			queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		
		rabbitMQSender.send(queue, "{\"event_type\": \"add\", \"data_type\": \"product\", \"id\": " + product.getId() + "}");*/
    }

    public void update(Product product, String operationType) {
        productMapper.update(product);

        String queue = null;
		/*
		if(operationType == null || "".equals(operationType)) {
			queue = RabbitQueue.DATA_CHANGE_QUEUE;
		} else if("refresh".equals(operationType)) {
			queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
		} else if("high".equals(operationType)) {
			queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		
		rabbitMQSender.send(queue, "{\"event_type\": \"update\", \"data_type\": \"product\", \"id\": " + product.getId() + "}");*/
    }

    public void delete(Long id, String operationType) {
        productMapper.delete(id);
		
		/*String queue = null;
		
		if(operationType == null || "".equals(operationType)) {
			queue = RabbitQueue.DATA_CHANGE_QUEUE;
		} else if("refresh".equals(operationType)) {
			queue = RabbitQueue.REFRESH_DATA_CHANGE_QUEUE;
		} else if("high".equals(operationType)) {
			queue = RabbitQueue.HIGH_PRIORITY_DATA_CHANGE_QUEUE;
		}
		
		rabbitMQSender.send(queue, "{\"event_type\": \"delete\", \"data_type\": \"product\", \"id\": " + id + "}");
*/
    }

    public Product findById(Long id) {
        return productMapper.findById(id);
    }

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String hello() {
        return "hello";
    }

    public String helloFallback() {
        return "sorry ribbon, it's error!";
    }

    @HystrixCommand(fallbackMethod = "portFallback")
    public String port() {
        return "port";
    }

    public String portFallback() {
        return "sorry ribbon, it's error!";
    }
}
