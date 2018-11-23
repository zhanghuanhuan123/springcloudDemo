package com.geny.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 前置拦截器
 */
@Component
public class PreFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(PreFilter.class);

    @Override
    public String filterType() {
        return PRE_TYPE;    //过滤器类型 : pre
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 5;   //过滤器执行顺序，数字越小，优先级越高，越靠前,这里设置为 0
    }

    @Override
    public boolean shouldFilter() {
        return true;    //返回 true，拦截所有 URL
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        String ipAddr = this.getIpAddr(req);
        logger.info("请求IP地址为：[{}]", ipAddr);
        //配置本地IP白名单，生产环境可放入数据库或者redis中
        List<String> ips = new ArrayList<>();
        ips.add("172.0.0.1");

      /*  if(!ips.contains(ipAddr)){
            logger.info("IP地址校验不通过！！！");
            ctx.setResponseStatusCode(401);
            ctx.setSendZuulResponse(false);
            ctx.setResponseBody("IpAddr is forbidden!");
        }*/
        logger.info("IP校验通过。");
        return null;
    }

    /**
     * 获取Ip地址
     *
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
