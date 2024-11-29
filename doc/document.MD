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
## 常用的缓存注解

### @EnableCaching
- **作用**: 启用 Spring 的声明式缓存支持。
- **位置**: 通常放在主应用程序类或配置类上。

### @Cacheable
- **作用**: 在方法执行前检查缓存中是否存在对应的数据，如果存在则返回缓存中的数据；否则，调用方法并将结果放入缓存。
- **属性**:
  - `value`: 缓存名称。
  - `key`: 缓存键，默认使用方法参数作为键。
  - `condition`: 条件表达式，决定是否进行缓存操作。
  - `unless`: 条件表达式，决定是否阻止将结果放入缓存。

### @CachePut
- **作用**: 不管缓存中是否有对应的数据，都会调用方法并将结果放入缓存。
- **属性**:
  - `value`: 缓存名称。
  - `key`: 缓存键。
  - `condition`: 条件表达式，决定是否进行缓存操作。
  - `unless`: 条件表达式，决定是否阻止将结果放入缓存。

### @CacheEvict
- **作用**: 清除缓存中的指定数据。
- **属性**:
  - `value`: 缓存名称。
  - `key`: 缓存键。
  - `allEntries`: 是否清除所有条目。
  - `beforeInvocation`: 是否在方法调用之前清除缓存。
  - `condition`: 条件表达式，决定是否进行缓存清除操作。

### @Caching
- **作用**: 组合多个缓存操作（`@Cacheable`, `@CachePut`, `@CacheEvict`）在一个方法上。
- **属性**:
  - `cacheable`: `@Cacheable` 注解数组。
  - `put`: `@CachePut` 注解数组。
  - `evict`: `@CacheEvict` 注解数组。

### @CacheConfig
- **作用**: 共享公共缓存配置，例如缓存名称、缓存键生成器等。
- **属性**:
  - `cacheNames`: 默认的缓存名称。
  - `keyGenerator`: 缓存键生成器。
  - `cacheManager`: 缓存管理器。
  - `cacheResolver`: 缓存解析器。


事件驱动的业务规则模型可以用在wms系统的开发上吗，如果可以，那你一入库单为例，给出代码，下面是模型详细说明

一、模型的技术实现
1、事件捕捉与分发
使用消息队列（如RabbitMQ）或事件总线实现高效的事件捕捉和异步分发，确保事件处理的实时性和系统的高可用性。单据状态变更使用状态机（j-easy/easy-states）。
2、规则引擎实现
采用开源规则引擎（如j-easy/easy-rules），支持规则的动态配置、热加载和版本管理，满足业务快速变化的需求。
3、工作流引擎集成
使用工作流引擎（j-easy/easy-flows）管理复杂的业务流程，确保多步骤操作的顺序性和可追溯性。
4、数据服务支持
构建实时数据访问层，通过缓存(caffine、redis)和mysql数据库提高数据查询性能。
4、可视化规则配置工具
提供可视化界面，业务人员可以直接编辑和配置规则，降低技术门槛。
5、
二、模型的优势
1、实时响应
系统能够实时捕捉并响应业务事件，确保业务流程快速执行。
2、灵活配置
通过规则和流程的可视化配置，企业能够根据需求快速调整业务逻辑。
3、自动化与智能化
消除了大量人工操作，提升了业务流程的效率和准确性。
4、可扩展性
模型支持多种事件类型和复杂的规则逻辑，能够适应不同业务场景。
