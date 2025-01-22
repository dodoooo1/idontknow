package com.idontknow.business.infra.configs.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.idontknow.business.core.exceptions.ApiErrorDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
private final ObjectMapper objectMapper;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // You can customize the error message and status code here.
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(objectMapper.writeValueAsString(buildProblemDetail(HttpStatus.UNAUTHORIZED, "Unauthorized", Collections.emptyList())));
    }
    private ProblemDetail buildProblemDetail(
            final HttpStatus status, final String detail, final List<ApiErrorDetails> errors) {

        final ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(status, StringUtils.normalizeSpace(detail));
        if (CollectionUtils.isNotEmpty(errors)) {
            problemDetail.setProperty("errors", errors);
        }

        return problemDetail;
    }
}
