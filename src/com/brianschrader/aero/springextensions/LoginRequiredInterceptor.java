package com.brianschrader.aero.springextensions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

public class LoginRequiredInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		//Ignore static file serving.
		if (handler.getClass() == ResourceHttpRequestHandler.class) return true;
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginRequired loginRequired = handlerMethod.getMethod().getAnnotation(LoginRequired.class);
        if (loginRequired == null) {
            return true;
        }
        
        String authenticationKey = loginRequired.key();
        boolean required = loginRequired.required();
 
        //Check auth
        boolean keyInSession = request.getSession().getAttribute(authenticationKey) != null;
        if (!required) {
        	return true;
        }
        if (keyInSession) {
        	return true;
        }
        
        //Set the reponse code and cancel the request processing.
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.addHeader("X-Authentication-Needed", "No valid login credentials found.");
        return false;
	}
}