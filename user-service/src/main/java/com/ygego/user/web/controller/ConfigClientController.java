package com.ygego.user.web.controller;

import com.ygego.user.config.ConfigInfoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientController {

    @Autowired
    private ConfigInfoProperties configInfoValue;
    @GetMapping("/profile")
    public String getProfile() {
        return configInfoValue.getProfile();
    }
}
