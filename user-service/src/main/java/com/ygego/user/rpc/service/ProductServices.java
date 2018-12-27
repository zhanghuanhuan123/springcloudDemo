package com.ygego.user.rpc.service;

import com.ygego.user.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName: ProductServices
 * Description:
 * Author: zhanghuan
 * Date: 2018/12/12 10:35
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
//@FeignClient(name = "product-service")
public interface ProductServices {
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Product findById(@RequestParam(value = "id") long id);
}
