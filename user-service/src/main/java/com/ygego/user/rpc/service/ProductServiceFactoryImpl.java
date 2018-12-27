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
                Product o = new Product();
                o.setBrandId(999l);
                o.setId(9999l);
                o.setName("我是降级");

                return o;
            }
        };
    }
}
