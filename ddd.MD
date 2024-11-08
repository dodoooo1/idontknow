# WMS 系统 DDD 开发规范

## 1. 项目结构

```plaintext
src
├── application
│   ├── service                # 应用服务层
│   ├── command                # 命令对象，处理写操作
│   ├── query                  # 查询对象，处理读操作
│   └── dto                    # 数据传输对象
├── domain
│   ├── model
│   │   ├── entity             # 实体类，如仓库、库存、订单
│   │   ├── aggregate          # 聚合根，处理复杂业务逻辑
│   │   └── valueobject        # 值对象，仓位、商品信息
│   ├── service                # 领域服务
│   ├── event                  # 领域事件，如入库、出库完成
│   └── repository             # 仓储接口
├── infrastructure
│   ├── repository             # 仓储实现，基于JPA/MyBatis
│   ├── acl                    # 防腐层，处理外部系统适配
│   ├── external               # 外部系统集成，如物流平台
│   └── configuration          # 基础设施配置
├── interfaces
│   ├── controller             # RESTful 控制器
│   └── api                    # 门面接口，对外提供的 API
└── common
    └── utils                  # 公共工具类
```

### 2. 各层说明

##### 2.1 应用层（application）

含义与作用： 应用层负责协调领域层的业务逻辑，处理用户请求和应用程序的工作流。它将用户请求转换为领域模型的操作，不包含业务逻辑。

    service：应用服务层，用于定义业务操作。
    command：命令对象，用于处理写操作。
    query：查询对象，用于处理读操作。
    dto：数据传输对象，用于在不同层之间传递数据。

示例：

```java

// services/InbountOrderService.java
package com.example.wms.application.service;

import com.example.wms.application.dto.InboundOrderDTO;
import com.example.wms.domain.model.InboundOrder;
import com.example.wms.domain.repository.InboundOrderRepository;

public class InboundOrderService {
    private final InboundOrderRepository inboundOrderRepository;

    public InboundOrderService(InboundOrderRepository inboundOrderRepository) {
        this.inboundOrderRepository = inboundOrderRepository;
    }

    public void createInboundOrder(InboundOrderDTO orderDTO) {
        InboundOrder order = new InboundOrder(orderDTO.getId());
        // 处理入库单创建逻辑
        inboundOrderRepository.save(order);
    }
}
```

##### 2.2 领域层（domain）

含义与作用： 领域层是 DDD 的核心，包含领域模型和领域逻辑。领域模型由实体、聚合和值对象构成。

    entity：实体类，具有唯一标识的业务对象。
    aggregate：聚合根，负责维护聚合内部的一致性和复杂业务逻辑。
    valueobject：值对象，不具有唯一标识的业务对象。
    service：领域服务，包含复杂的业务逻辑，不属于任何实体或值对象。
    event：领域事件，表示在领域模型中发生的重要事件。
    repository：仓储接口，负责与数据源交互。

示例：

```java

// model/entity/InbountOrder.java
package com.example.wms.domain.model.entity;

import java.util.List;

public class InboundOrder {
    private Long id;
    private String status;
    private List<InboundOrderItem> items;

    public InboundOrder(Long id) {
        this.id = id;
        this.status = "PENDING";
    }

    public void complete() {
        this.status = "COMPLETED";
    }
}
```

// model/valueobject/InboundOrderItem.java
package com.example.wms.domain.model.valueobject;

public class InboundOrderItem {
private final Long productId;
private final int quantity;

    public InboundOrderItem(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

}

##### 2.3 基础设施层（infrastructure）

含义与作用： 基础设施层提供与外部系统的交互，包括数据存储、消息队列、API 集成等。

    repository：仓储实现，负责数据持久化和访问。
    acl：防腐层，用于处理外部系统的适配。
    external：外部系统集成模块，如物流平台的集成。
    configuration：基础设施的配置。

示例：

```java

// infrastructure/repository/InboundOrderRepositoryImpl.java
package com.example.wms.infrastructure.repository;

import com.example.wms.domain.model.entity.InboundOrder;
import com.example.wms.domain.repository.InboundOrderRepository;

public class InboundOrderRepositoryImpl implements InboundOrderRepository {
    private final JpaInboundOrderRepository jpaRepository;

    public InboundOrderRepositoryImpl(JpaInboundOrderRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public InboundOrder findById(Long id) {
        return jpaRepository.findById(id).orElse(null);
    }

    @Override
    public void save(InboundOrder order) {
        jpaRepository.save(order);
    }
}
```

##### 2.4 接口层（interfaces）

含义与作用： 接口层是用户与系统交互的接口，通常是 RESTful API，用于接收用户请求并返回结果。

    controller：处理 HTTP 请求的控制器。
    api：对外提供的 API 接口。

示例：

```java

// interfaces/controller/InboundOrderController.java
package com.example.wms.interfaces.controller;

import com.example.wms.application.service.InboundOrderService;
import com.example.wms.application.dto.InboundOrderDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inbound-orders")
public class InboundOrderController {
    private final InboundOrderService inboundOrderService;

    public InboundOrderController(InboundOrderService inboundOrderService) {
        this.inboundOrderService = inboundOrderService;
    }

    @PostMapping
    public void createInboundOrder(@RequestBody InboundOrderDTO orderDTO) {
        inboundOrderService.createInboundOrder(orderDTO);
    }
}
```

##### 2.5 公共工具层（common）

含义与作用： 公共工具层用于存放全局通用的工具类，例如日期处理、字符串处理等。

示例：

```java

// common/utils/DateUtils.java
package com.example.wms.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }
}
```

### 3. 领域事件（Domain Events）

含义与作用： 领域事件表示在领域模型中发生的重要事件，用于通知其他部分系统状态的变化。

示例：

```java

// domain/event/InboundOrderCompletedEvent.java
package com.example.wms.domain.event;

import java.time.LocalDateTime;

public class InboundOrderCompletedEvent {
    private final Long inboundOrderId;
    private final LocalDateTime completedAt;

    public InboundOrderCompletedEvent(Long inboundOrderId) {
        this.inboundOrderId = inboundOrderId;
        this.completedAt = LocalDateTime.now();
    }

    // Getters
}
```
