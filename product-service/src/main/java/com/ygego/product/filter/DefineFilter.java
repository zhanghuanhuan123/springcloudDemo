package com.ygego.product.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * ClassName: DefineFilter
 * Description:
 * Author: zhanghuan
 * Date: 2018/12/25 14:27
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

public class DefineFilter implements Filter {
    private final static Log logger = LogFactory.getLog(DefineFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        logger.info(httpRequest.getHeader("hotelId") + "------------------------>");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
