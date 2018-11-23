package com.ygego.user.rpc.service;

import com.ygego.user.model.Product;
import feign.hystrix.FallbackFactory;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceFactoryImpl implements FallbackFactory<ProductService> {
    private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(ProductServiceFactoryImpl.class);

    @Override
    public ProductService create(Throwable throwable) {
        Logger.info("fallback; reason was: {}", throwable.getMessage());
        return new ProductServiceFactoryProxy() {
            @Override
            public Product findById(long id) {
                return new Product();
            }
        };
    }
}
