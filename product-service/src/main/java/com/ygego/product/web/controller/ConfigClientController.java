package com.ygego.product.web.controller;

import com.ygego.product.config.ConfigInfoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {

    @Autowired
    private ConfigInfoProperties configInfoValue;

    @GetMapping("/env")
    public String getProfile() {
        return configInfoValue.getProfile();
    }
}
