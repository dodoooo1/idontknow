package com.idontknow.business.infra.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.idontknow.business.core.constants.SymbolConstants;
import com.idontknow.business.core.utilities.CoreJsonUtils;
import com.idontknow.business.core.utilities.SystemClock;
import com.idontknow.business.domain.entities.system.OperateLogEntity;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class OperateLogAop {

    @SneakyThrows
    @Around("@annotation(operateLog)")
    public Object doAround(ProceedingJoinPoint point, com.idontknow.business.infra.annotation.OperateLog operateLog) {
        long startTime = SystemClock.now();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        Assert.notNull(requestAttributes, "requestAttributes not be null");
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        Object[] args = point.getArgs();
        String method = className + SymbolConstants.DOT + methodName + SymbolConstants.LEFT_PARENTHESIS + SymbolConstants.RIGHT_PARENTHESIS;
        OperateLogEntity.OperateLogEntityBuilder<?, ?> logBuilder = OperateLogEntity.builder().moduleName(operateLog.module()).method(method).operatorType(operateLog.operation())
                .operUrl(request.getRequestURL().toString()).requestMethod(request.getMethod()).requestParam(getRequestParam(request));

        Object proceed;
        Throwable throwable = null;
        try {
            proceed = point.proceed();
        } catch (Throwable e) {
            throw throwable = e;
        } finally {
            logBuilder.costTime(calculateCostTime(startTime));
            logBuilder.build();
        }
        return proceed;
    }

    private String getRequestParam(HttpServletRequest request) {
        StringBuilder requestParam = new StringBuilder();

        // 获取查询参数并转换为 JSON
        String queryString = request.getQueryString();
        if (HttpMethod.GET.matches(request.getMethod()) && StringUtils.hasLength(queryString)) {
            Map<String, String> queryParamsMap = new HashMap<>();
            String[] pairs = queryString.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                if (idx > 0) {
                    String key = pair.substring(0, idx);
                    String value = pair.substring(idx + 1);
                    queryParamsMap.put(key, value);
                }
            }
            try {
                requestParam.append(CoreJsonUtils.serialize(queryParamsMap));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else
            // 获取请求体参数（假设请求体是 JSON、纯文本或 HTML 格式）
            if (HttpMethod.POST.matches(request.getMethod()) || HttpMethod.PUT.matches(request.getMethod())
                    || HttpMethod.PATCH.matches(request.getMethod()) || HttpMethod.DELETE.matches(request.getMethod())) {
                String contentType = request.getContentType();

                if (contentType != null && (contentType.contains(MediaType.APPLICATION_JSON_VALUE)
                        || contentType.contains(MediaType.MULTIPART_FORM_DATA_VALUE)
                        || contentType.contains(MediaType.TEXT_HTML_VALUE)
                        || contentType.contains(MediaType.TEXT_PLAIN_VALUE))) {
                    try (BufferedReader reader = request.getReader()) {
                        char[] buff = new char[1024];
                        int len;
                        while ((len = reader.read(buff)) != -1) {
                            requestParam.append(buff, 0, len);
                        }
                    } catch (IOException e) {
                        log.error("Failed to read request body", e);
                    }
                }
            }

        return requestParam.toString();
    }

    private Long calculateCostTime(Long startTime) {
        return SystemClock.now() - startTime;
    }

}
