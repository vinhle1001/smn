package com.vinhle.smn.api.springconfig.resolver;

import com.fasterxml.jackson.databind.JsonNode;
import com.vinhle.smn.api.springconfig.singleton.JsonMapper;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class JsonArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger logger = Logger.getLogger("JsonArgumentResolver");
    private static final String JSON_BODY_ATTRIBUTE = "JSON_REQUEST_BODY";

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(JsonArg.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        JsonArg jsonArg = methodParameter.getParameterAnnotation(JsonArg.class);
        JsonNode jsonNode = getRequestBody(nativeWebRequest);
        if (jsonNode.has(jsonArg.name())) {
            return read(jsonNode.path(jsonArg.name()), methodParameter.getParameterType());
        }
        return jsonArg.defaultValue();
    }

    // Read json from request
    private JsonNode readTree(InputStream inputStream) throws IOException {
        return JsonMapper.getInstant().readTree(inputStream);
    }

    // Store and Parse
    private JsonNode getRequestBody(NativeWebRequest webRequest) throws IOException {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        JsonNode jsonNode = (JsonNode) servletRequest.getAttribute(JSON_BODY_ATTRIBUTE);
        if (jsonNode == null) {
            jsonNode = readTree(servletRequest.getInputStream());
            servletRequest.setAttribute(JSON_BODY_ATTRIBUTE, jsonNode);
        }
        return jsonNode;
    }

    // Read object to value
    private <T> T read(JsonNode node, Class<T> type){
        try {
            return JsonMapper.getInstant().treeToValue(node, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
