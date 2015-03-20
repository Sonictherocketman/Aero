package com.brianschrader.aero.springextensions;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


public class SessionParamArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterAnnotation(SessionParam.class) != null;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Object resolveArgument(MethodParameter param, ModelAndViewContainer mavContainer, NativeWebRequest request, WebDataBinderFactory binderFactory) throws Exception {
		Annotation[] paramAnns = param.getParameterAnnotations();
		Class paramType = param.getParameterType();

		for (Annotation paramAnn : paramAnns) {                     
			if (SessionParam.class.isInstance(paramAnn)) {     
				SessionParam sessionParam = (SessionParam) paramAnn;
				String paramName = sessionParam.value();
				boolean required = sessionParam.required();
				String defaultValue = sessionParam.defaultValue();
				HttpServletRequest httprequest = (HttpServletRequest) request
						.getNativeRequest();
				HttpSession session = httprequest.getSession(false);
				Object result = null;
				if (session != null) {
					result = session.getAttribute(paramName);
				}
				if (result == null)
					result = defaultValue;
				if (result == null && required && session == null)
					raiseSessionRequiredException(paramName, paramType); 
				if (result == null && required)
					raiseMissingParameterException(paramName, paramType); 

				return result;
			}
		}

		return WebArgumentResolver.UNRESOLVED;   

	}

	// ..

	@SuppressWarnings("rawtypes")
	protected void raiseMissingParameterException(String paramName, Class paramType) throws Exception {
		throw new IllegalStateException("Missing parameter '" + paramName
				+ "' of type [" + paramType.getName() + "]");
	}

	@SuppressWarnings("rawtypes")
	protected void raiseSessionRequiredException(String paramName, Class paramType) throws Exception {
		throw new HttpSessionRequiredException(
				"No HttpSession found for resolving parameter '" + paramName
				+ "' of type [" + paramType.getName() + "]");
	}
}
