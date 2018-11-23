package com.geny.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class LastSuperFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(LastSuperFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        try {
            if (ctx.getResponseStatusCode() >= 200 && ctx.getResponseStatusCode() < 300) {
                //正常请求
            } else {
                //返回数据出问题对返回数据进一步验证，防止应用处理不严谨导致不友好的提示。
                   /* ctx.setResponseStatusCode(401);
                 ctx.setResponseBody("{\"result\":\"password is not correct!\"}");*/
                //ctx.set("isSuccess", false);
            }

        } catch (Exception e) {
            ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ctx.set("error.exception", e);
        }

        return null;
    }

}