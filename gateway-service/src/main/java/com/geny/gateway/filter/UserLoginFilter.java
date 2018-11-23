package com.geny.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserLoginFilter extends ZuulFilter {
	
	private static Logger logger = LoggerFactory.getLogger(UserLoginFilter.class);

	public Object run() {
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();
		logger.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
		
		Object userId = request.getParameter("userId");
		
		if(userId == null) {
			logger.warn("userId is empry, not allow to access website");   
			requestContext.setSendZuulResponse(false); 
			requestContext.setResponseStatusCode(401); 
			try {
				requestContext.getResponse().getWriter().write(
						"userId is empry, not allow to access website");  
			} catch (Exception e) {
				logger.error("send response error", e);  
			}
			
			return null;
		}
		
		logger.info("userId is ok, allow to access website");  
		
		return null;
	}

	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
        return 40;
    }

	@Override
	public String filterType() {
		return "pre";
	}

}
