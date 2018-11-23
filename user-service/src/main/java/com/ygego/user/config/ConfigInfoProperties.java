package com.ygego.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zzf
 * @date: 2018/3/16
 * @time: 23:55
 * @description : 配置信息
 */
@Component
@RefreshScope
public class ConfigInfoProperties {

    @Value("${profile}")
    private String profile;

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
