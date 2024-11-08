 一、异常
ProblemDetailsExceptionHandler 是一个 @ControllerAdvice集中处理系统异常
处理以下异常。如果系统出现以下异常，会被SpringBoot支持以 RFC 7807规范方式返回错误数据

```java
@ExceptionHandler({
HttpRequestMethodNotSupportedException.class, //请求方式不支持
HttpMediaTypeNotSupportedException.class,
HttpMediaTypeNotAcceptableException.class,
MissingPathVariableException.class,
MissingServletRequestParameterException.class,
MissingServletRequestPartException.class,
ServletRequestBindingException.class,
MethodArgumentNotValidException.class,
NoHandlerFoundException.class,
AsyncRequestTimeoutException.class,
ErrorResponseException.class,
ConversionNotSupportedException.class,
TypeMismatchException.class,
HttpMessageNotReadableException.class,
HttpMessageNotWritableException.class,
BindException.class
})
```

