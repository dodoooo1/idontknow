package com.idontknow.business.domain;
//3. 领域层 (Domain Layer)

//领域服务直接包含用例逻辑，不再单独创建 CmdExe。
//复杂的聚合行为仍由 DomainService 处理。

//4. 防腐层 (Anti-Corruption Layer)

//仅在需要隔离外部服务（如远程 API）时使用。
//如果 Gateway 只是调用仓储，可以省略防腐层。
