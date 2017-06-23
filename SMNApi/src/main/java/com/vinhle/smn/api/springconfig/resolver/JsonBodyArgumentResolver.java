package com.vinhle.smn.api.springconfig.resolver;

import com.vinhle.smn.api.springconfig.singleton.JsonMapper;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by nguyenthuantan on 9/22/16.
 */
public class JsonBodyArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JsonBody.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);

        try {
            return JsonMapper.getInstant().readValue(servletRequest.getInputStream(), parameter.getParameterType());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
