

CREATE TABLE `base_warehouse`
(
    `id`         bigint                                                       NOT NULL COMMENT '仓库ID',
    `name`       varchar(64)                                                  not null       DEFAULT '' COMMENT '仓库名称',
    `code`       varchar(64)                                                  not null       DEFAULT '' COMMENT '仓库编码',
    `created_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL       DEFAULT '' COMMENT '创建人',
    `updated_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL       DEFAULT '' COMMENT '修改人',
    `created_at` datetime                                                     NOT NULL       DEFAULT (now()) COMMENT '创建时间',
    `updated_at` datetime                                                     NOT NULL       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `remarks`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci               DEFAULT NULL COMMENT '备注信息',
    `status`                varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT 'ACTIVE' COMMENT 'ACTIVE：启用 ;INACTIVE：禁用',
    UNIQUE KEY `unique_code` (`code`) -- 添加唯一索引
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='仓库';


CREATE TABLE `base_product`
(
    `id`                    BIGINT primary key                                           NOT NULL COMMENT '商品ID',

    `code`                  VARCHAR(200)                                                 not null COMMENT '商品编码',
    `name`                  VARCHAR(255)                                                 not null COMMENT '商品名称',
    `name_en`               VARCHAR(255)                                                          default '' COMMENT '商品英文名称',
    `unit`                  VARCHAR(200)                                                 not null COMMENT '商品单位',
    `image_url`             TEXT                                                         null COMMENT '图片地址',
    `category_id`           VARCHAR(64)                                                  not null COMMENT '商品种类ID',
    `category_code`         VARCHAR(200)                                                 not null COMMENT '商品种类编码',
    `category_name`         VARCHAR(200)                                                 not null COMMENT '商品种类名称',
    `warning_quantity`      DECIMAL(18, 2)                                                        DEFAULT 0 COMMENT '预警数量',
    `validity_warning_days` INT                                                                   DEFAULT 0 COMMENT '预警天数',
    `max_quantity`          DECIMAL(18, 2)                                                        DEFAULT 1000 COMMENT '最大库存',
    `specification`         VARCHAR(200)                                                          DEFAULT '' COMMENT '规格',
    `unit_quantity`         DECIMAL(18, 2)                                               not null COMMENT '单位数量',
    `container_type`        VARCHAR(50)                                                  not null COMMENT '入库容器类型',
    `is_quality_checked`    TINYINT                                                               DEFAULT 0 COMMENT '质检需求状态 (0: 否, 1: 是)',
    `warehouse_code`        VARCHAR(200)                                                 not null COMMENT '仓库编码',
    `created_by`            varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
    `updated_by`            varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '修改人',
    `created_at`            datetime                                                     NOT NULL DEFAULT (now()) COMMENT '创建时间',
    `updated_at`            datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `remarks`               varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         DEFAULT '' COMMENT '备注信息',
    `status`                varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT 'ACTIVE' COMMENT 'ACTIVE：启用 ;INACTIVE：禁用'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  row_format = dynamic
  collate = utf8mb4_general_ci COMMENT ='商品信息表';

-- auto-generated definition
create table base_product_category
(
    `id`          BIGINT primary key                                           NOT NULL COMMENT 'id',
    `category_id` VARCHAR(64)                                                           DEFAULT NULL COMMENT '商品种类ID',
    `code`        VARCHAR(200)                                                          DEFAULT NULL COMMENT '商品编码',
    `name`        VARCHAR(255)                                                          DEFAULT NULL COMMENT '商品名称',
    parent_id     varchar(64)                                                  not null comment 'parent_id = 0 表示顶级',
    `created_by`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
    `updated_by`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '修改人',
    `created_at`  datetime                                                     NOT NULL DEFAULT (now()) COMMENT '创建时间',
    `updated_at`  datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `remarks`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         DEFAULT NULL COMMENT '备注信息',
    `status`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT 'ACTIVE' COMMENT 'ACTIVE：启用 ;INACTIVE：禁用'

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  row_format = dynamic
  collate = utf8mb4_general_ci
    comment '商品种类表';
-- auto-generated definition
create table wm_inbound_order
(
    id               bigint                                           not null comment 'id'
        primary key,
    code             varchar(64)                                      not null comment '单据编码',
    type             varchar(32)            default 'OTHER'           null comment 'PURCHASE(采购入库);CUSTOMER(客户预约入库);盘点入库(STOCKTAKING),OTHER(其他入库)',

    attachment       varchar(255)           default ''                null comment '附件',
    state            varchar(32)            default 'OTHER'           null comment 'NEW  新建，RECEIVING 收货中，PARTIAL_COMPLETED 部分收货， COMPLETED  结束',
    ref_order_number varchar(128)           default ''                null comment '来源单号',
    gen_type         enum ('MANUAL', 'API') default 'MANUAL'          null comment '来源类型 API,MANUAL',
    wh_code          varchar(64)                                      not null comment '仓库编码',
    created_by       varchar(64)                                      not null comment '创建人',
    updated_by       varchar(64)                                      not null comment '修改人',
    created_at       datetime               default (now())           not null comment '创建时间',
    updated_at       datetime               default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    remarks          varchar(255)           default ''                null comment '备注信息',
    status           varchar(32)            default 'ACTIVE'          null comment 'ACTIVE：启用 ;INACTIVE：禁用'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  row_format = dynamic
  collate = utf8mb4_general_ci
    comment '入库单';

-- auto-generated definition
create table wm_inbound_order_item
(
    id                   bigint                                                           not null comment '主键'
        primary key,
    biz_inbound_order_id bigint                                                           not null comment '到货通知单号',
    row_id               int                                                              not null comment '行号',
    item_id              varchar(64)                                                      not null comment '物料ID',
    item_name            varchar(200)                                                     not null comment '物料名称',
    item_code            varchar(200)                                                     not null comment '物料编码',
    production_date      datetime                                                         null comment '生产日期',
    lot_no               varchar(32)                            default ''                null comment '批次',
    volume               decimal(19, 4)                         default 0.0000            null comment '体积',
    weight               decimal(19, 4)                         default 0.0000            null comment '毛重',
    state                varchar(32)                            default 'OTHER'           null comment 'NEW  新建，RECEIVING 收货中，PARTIAL_COMPLETED 部分收货， COMPLETED  结束',
    storage_location     varchar(128)                                                     null comment '库位',
    unit                 varchar(36)                                                      null comment '单位',
    specifications       varchar(200)                                                     null comment '规格',
    quantity             decimal(19, 4)                                                   null comment '数量',
    storage_quantity     decimal(19, 4)                                                   null comment '已入库数量',
    container_barcode    varchar(64) collate utf8mb4_general_ci default ''                null comment '容器条码',
    signed_quantity      decimal(19, 4)                                                   null comment '已签数量',
    attribute            varchar(45)                                                      null comment '产品属性',
    created_by           varchar(64)                                                      not null comment '创建人',
    updated_by           varchar(64)                                                      not null comment '修改人',
    created_at           datetime                               default (now())           not null comment '创建时间',
    updated_at           datetime                               default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    remarks              varchar(255)                           default ''                null comment '备注信息',
    status               varchar(32)                            default 'ACTIVE'          null comment 'ACTIVE：启用 ;INACTIVE：禁用'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  row_format = dynamic
  collate = utf8mb4_general_ci
    comment '入库单明细';


-- auto-generated definition
create table wm_inbound_order_delivery
(
    `id`                BIGINT primary key                                           NOT NULL COMMENT 'id',

    wm_inbound_order_id bigint                                                       not null comment '入库单id',
    driver_name         varchar(32)                                                           default '' null comment '司机',
    driver_phone        varchar(32)                                                           default '' null comment '司机电话',
    car_number          varchar(32)                                                           default '' null comment '车号',
    tracking_number     varchar(255)                                                          default '' comment '跟踪号',
    `created_by`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
    `updated_by`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '修改人',
    `created_at`        datetime                                                     NOT NULL DEFAULT (now()) COMMENT '创建时间',
    `updated_at`        datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `remarks`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         DEFAULT NULL COMMENT '备注信息',
    `status`            varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT 'ACTIVE' COMMENT 'ACTIVE：启用 ;INACTIVE：禁用'

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  row_format = dynamic
  collate = utf8mb4_general_ci
    comment '入库单物流信息表';


-- auto-generated definition
create table base_container
(
    `id`           BIGINT primary key                                           NOT NULL COMMENT 'id',

    bar_code       varchar(200)                                                 not null comment '容器条码',
    container_type varchar(200)                                                 not null comment 'Box      纸箱
   Plastic  料箱',
    container_spec varchar(100)                                                 not null comment '容器规格',
    `created_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
    `updated_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '修改人',
    `created_at`   datetime                                                     NOT NULL DEFAULT (now()) COMMENT '创建时间',
    `updated_at`   datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `remarks`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         DEFAULT NULL COMMENT '备注信息',
    `status`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT 'ACTIVE' COMMENT 'ACTIVE：启用 ;INACTIVE：禁用'

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  row_format = dynamic
  collate = utf8mb4_general_ci
    comment '容器表';
-- auto-generated definition
create table base_container_type
(
    `id`                BIGINT primary key                                           NOT NULL COMMENT 'id',
    container_type_code varchar(200)                                                 not null comment '类型编码',
    container_type_name varchar(200)                                                 not null comment '类型名称',
    border_long         decimal(8, 2)                                                null comment '长',
    border_height       decimal(8, 2)                                                null comment '高',
    border_width        decimal(8, 2)                                                null comment '宽',
    `created_by`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
    `updated_by`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '修改人',
    `created_at`        datetime                                                     NOT NULL DEFAULT (now()) COMMENT '创建时间',
    `updated_at`        datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `remarks`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         DEFAULT NULL COMMENT '备注信息',
    `status`            varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT 'ACTIVE' COMMENT 'ACTIVE：启用 ;INACTIVE：禁用'

) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  row_format = dynamic
  collate = utf8mb4_general_ci
    comment '容器类型表';



-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '编号',
    `dict_type`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型',
    `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT '' COMMENT '描述',
    `created_by`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '创建人',
    `updated_by`  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '修改人',
    `created_at`  datetime                                                      NOT NULL DEFAULT (now()) COMMENT '创建时间',
    `updated_at`  datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remarks`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT '' COMMENT '备注信息',
    `status`      varchar(64)                                                            DEFAULT 'ACTIVE' COMMENT 'ACTIVE：启用 ;INACTIVE：禁用',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 23
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` (`id`, `dict_type`, `description`, `created_by`, `updated_by`, `created_at`, `updated_at`,
                        `remarks`, `status`)
VALUES (1, 'log_type', '日志类型', 'admin', 'admin', '2019-03-19 11:06:44', '2024-08-16 12:36:52', '异常、正常',
        'ACTIVE');
INSERT INTO `sys_dict` (`id`, `dict_type`, `description`, `created_by`, `updated_by`, `created_at`, `updated_at`,
                        `remarks`, `status`)
VALUES (11, 'status_type', '租户状态', 'admin', 'admin', '2019-05-15 16:31:08', '2024-08-16 12:36:52', '租户状态',
        'ACTIVE');
INSERT INTO `sys_dict` (`id`, `dict_type`, `description`, `created_by`, `updated_by`, `created_at`, `updated_at`,
                        `remarks`, `status`)
VALUES (18, 'lock_flag', '用户状态', 'admin', 'admin', '2023-02-01 16:55:31', '2024-08-16 12:36:52', NULL, 'ACTIVE');
INSERT INTO `sys_dict` (`id`, `dict_type`, `description`, `created_by`, `updated_by`, `created_at`, `updated_at`,
                        `remarks`, `status`)
VALUES (22, 'yes_no_type', '是否', 'admin', 'admin', '2023-02-01 16:55:31', '2024-08-16 12:36:52', NULL, 'ACTIVE');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`
(
    `id`         bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '编号',
    `dict_id`    bigint                                                        NOT NULL COMMENT '字典ID',
    `item_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典项值',
    `item_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典项名称',
    `sort`       int                                                           NOT NULL DEFAULT '0' COMMENT '排序（升序）',
    `created_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '创建人',
    `updated_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '修改人',
    `created_at` datetime                                                               DEFAULT (now()) COMMENT '创建时间',
    `updated_at` datetime                                                               DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remarks`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT '' COMMENT '备注信息',
    `status`     varchar(64)                                                            DEFAULT 'ACTIVE' COMMENT 'ACTIVE：启用 ;INACTIVE：禁用',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `dict_id` (`dict_id`, `item_value`),
    KEY `sys_dict_value` (`item_value`) USING BTREE,
    KEY `sys_dict_label` (`item_label`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 100
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='字典项';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `item_value`, `item_label`, `sort`, `created_by`, `updated_by`,
                             `created_at`, `updated_at`, `remarks`, `status`)
VALUES (1, 1, '9', '异常', 1, 'admin', 'admin', '2019-03-19 11:08:59', '2019-03-25 12:49:13', '', '');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `item_value`, `item_label`, `sort`, `created_by`, `updated_by`,
                             `created_at`, `updated_at`, `remarks`, `status`)
VALUES (2, 1, '0', '正常', 0, 'admin', 'admin', '2019-03-19 11:09:17', '2019-03-25 12:49:18', '', '');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `item_value`, `item_label`, `sort`, `created_by`, `updated_by`,
                             `created_at`, `updated_at`, `remarks`, `status`)
VALUES (33, 11, '0', '正常', 0, 'admin', 'admin', '2019-05-15 16:31:34', '2019-05-16 22:30:46', '状态正常', '');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `item_value`, `item_label`, `sort`, `created_by`, `updated_by`,
                             `created_at`, `updated_at`, `remarks`, `status`)
VALUES (34, 11, '9', '冻结', 1, 'admin', 'admin', '2019-05-15 16:31:56', '2019-05-16 22:30:50', '状态冻结', '');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `item_value`, `item_label`, `sort`, `created_by`, `updated_by`,
                             `created_at`, `updated_at`, `remarks`, `status`)
VALUES (58, 18, '0', '有效', 0, 'admin', 'admin', '2023-02-01 16:56:00', '2023-02-01 16:56:00', NULL, '');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `item_value`, `item_label`, `sort`, `created_by`, `updated_by`,
                             `created_at`, `updated_at`, `remarks`, `status`)
VALUES (59, 18, '9', '禁用', 1, 'admin', 'admin', '2023-02-01 16:56:09', '2023-02-01 16:56:09', NULL, '');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `item_value`, `item_label`, `sort`, `created_by`, `updated_by`,
                             `created_at`, `updated_at`, `remarks`, `status`)
VALUES (66, 22, '0', '否', 0, 'admin', 'admin', '2023-02-20 23:35:23', '2023-02-20 23:35:23', '0', '');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `item_value`, `item_label`, `sort`, `created_by`, `updated_by`,
                             `created_at`, `updated_at`, `remarks`, `status`)
VALUES (67, 22, '1', '是', 0, 'admin', 'admin', '2023-02-20 23:35:37', '2023-02-20 23:35:37', '1', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`
(
    `id`             bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '日志主键',
    `module_name`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '模块标题',
    `business_type`  int                                                           NOT NULL COMMENT '业务类型（0其它 1新增 2修改 3删除）',
    `method`         varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '方法名称',
    `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '请求方式',
    `operator_type`  varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci            DEFAULT 'PC' COMMENT '操作类别（PC,PDA,API,OTHER）',
    `oper_url`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT '' COMMENT '请求URL',
    `request_param`  varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         DEFAULT '' COMMENT '请求参数',
    `response`       varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         DEFAULT '' COMMENT '返回参数',
    `created_by`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '创建人',
    `updated_by`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '修改人',
    `created_at`     datetime                                                      NOT NULL DEFAULT (now()) COMMENT '创建时间',
    `updated_at`     datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `cost_time`      bigint                                                                 DEFAULT '0' COMMENT '消耗时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 100
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='操作日志记录';

DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `parent_id`   bigint       NOT NULL COMMENT '父机构ID',
    `name`        varchar(100) NOT NULL COMMENT '机构/部门名称',
    `sort`        int                   DEFAULT '0' COMMENT '排序',
    `description` varchar(500)          DEFAULT '' DEFAULT NULL COMMENT '描述',
    `type`        varchar(32)  NOT NULL DEFAULT 'CORPORATION' COMMENT '部门类型 CORPORATION公司，ORGANIZATION组织机构，POST岗位',
    `level`       varchar(32)  NOT NULL DEFAULT 'TOP_DEPARTMENT' COMMENT '部门级别 TOP_DEPARTMENT一级部门 CHILD_DEPARTMENT子部门',
    `code`        varchar(64)  NOT NULL COMMENT '机构编码',
    `mobile`      varchar(32)           DEFAULT '' COMMENT '手机号',
    `address`     varchar(100)          DEFAULT '' COMMENT '地址',
    `created_by`  varchar(64)  NOT NULL COMMENT '创建人',
    `updated_by`  varchar(64)  NOT NULL COMMENT '修改人',
    `created_at`  datetime     NOT NULL DEFAULT (now()) COMMENT '创建时间',
    `updated_at`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `remarks`     varchar(255)          DEFAULT '' COMMENT '备注信息',
    `status`      varchar(64)           DEFAULT 'ACTIVE' COMMENT 'ACTIVE：启用 ;INACTIVE：禁用',
    `tenant_id`   bigint                DEFAULT '0' COMMENT '租户ID',
    `iz_leaf`     tinyint(1)            DEFAULT '1' COMMENT '是否有叶子节点',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uniq_depart_org_code` (`code`) USING BTREE,
    KEY `idx_sd_parent_id` (`parent_id`) USING BTREE,
    KEY `idx_sd_depart_order` (`sort`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='组织机构表';


-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`
(
    `id`             bigint       NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `parent_id`      bigint       NOT NULL COMMENT '父id',
    `name`           varchar(255) NOT NULL COMMENT '菜单标题',
    `url`            varchar(255) NOT NULL COMMENT '路径',
    `component`      varchar(255) NOT NULL COMMENT '组件',
    `is_route`       tinyint(1)            DEFAULT '1' COMMENT '是否路由菜单',
    `component_name` varchar(255) NOT NULL COMMENT '组件名字',
    `redirect`       varchar(255) NULL COMMENT '一级菜单跳转地址',
    `level`          varchar(32)  NOT NULL COMMENT '菜单级别(TOP_MENU:一级菜单; CHILD_MENU:子菜单:BUTTON:按钮权限)',
    `code`           varchar(32)  NOT NULL COMMENT '权限标识',
    `sort`           int                   DEFAULT 1 COMMENT '菜单排序',
    `icon`           varchar(255)          DEFAULT '' COMMENT '菜单图标',
    `is_leaf`        tinyint(1)            DEFAULT 1 COMMENT '是否叶子节点',
    `keep_alive`     tinyint(1)            DEFAULT 0 COMMENT '是否缓存该页面',
    `hidden`         tinyint(1)            DEFAULT 0 COMMENT '是否隐藏路由',
    `hide_tab`       tinyint(1)            DEFAULT 0 COMMENT '是否隐藏tab',
    `description`    varchar(255)          DEFAULT '' COMMENT '描述',
    `created_by`     varchar(64)  NOT NULL COMMENT '创建人',
    `updated_by`     varchar(64)  NOT NULL COMMENT '修改人',
    `created_at`     datetime     NOT NULL DEFAULT (now()) COMMENT '创建时间',
    `updated_at`     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `remarks`        varchar(255)          DEFAULT '' COMMENT '备注信息',
    `status`         varchar(64)           DEFAULT 'ACTIVE' COMMENT 'ACTIVE：启用 ;INACTIVE：禁用',
    `open_type`      varchar(255)          DEFAULT 'INTERNAL' COMMENT '外链菜单打开方式 internal/内部打开 external/外部打开',
    `tenant_id`      bigint                DEFAULT '0' COMMENT '租户ID',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `index_menu_type` (`level`) USING BTREE,
    KEY `index_menu_hidden` (`hidden`) USING BTREE,
    KEY `index_menu_status` (`status`) USING BTREE,
    KEY `index_menu_url` (`url`) USING BTREE,
    KEY `index_menu_sort_no` (`sort`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='菜单权限表';


DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `name`        varchar(200) NOT NULL COMMENT '角色名称',
    `code`        varchar(100) NOT NULL COMMENT '角色编码',
    `description` varchar(255)          DEFAULT '' COMMENT '描述',
    `created_by`  varchar(64)  NOT NULL DEFAULT '' COMMENT '创建人',
    `updated_by`  varchar(64)  NOT NULL DEFAULT '' COMMENT '修改人',
    `created_at`  datetime     NOT NULL DEFAULT (now()) COMMENT '创建时间',
    `updated_at`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `tenant_id`   bigint                DEFAULT '0' COMMENT '租户ID',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uniq_sys_role_role_code` (`code`) USING BTREE,
    KEY `idx_sys_role_tenant_id` (`tenant_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='角色表';

DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource`
(
    `id`           bigint NOT NULL AUTO_INCREMENT,
    `role_id`      bigint NOT NULL COMMENT '角色id',
    `resources_id` bigint NOT NULL COMMENT '权限id',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_srp_role_per_id` (`role_id`, `resources_id`) USING BTREE,
    KEY `idx_srp_role_id` (`role_id`) USING BTREE,
    KEY `idx_srp_resources_id` (`resources_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='角色权限表';
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`         bigint       NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username`   varchar(64)  NOT NULL COMMENT '用户名',
    `password`   varchar(255) NOT NULL COMMENT '密码',
    `salt`       varchar(255)          DEFAULT '123@abc*qwe' COMMENT '盐值',
    `phone`      varchar(20)           DEFAULT '' COMMENT '电话号码',
    `avatar`     varchar(255)          DEFAULT '' COMMENT '头像',
    `nickname`   varchar(64)           DEFAULT '' COMMENT '昵称',
    `name`       varchar(64)           DEFAULT '' COMMENT '姓名',
    `email`      varchar(128) NOT NULL COMMENT '邮箱地址',
    `created_by` varchar(64)  NOT NULL COMMENT '创建人',
    `updated_by` varchar(64)  NOT NULL COMMENT '修改人',
    `created_at` datetime     NOT NULL DEFAULT (now()) COMMENT '创建时间',
    `updated_at` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `status`     varchar(64)           DEFAULT 'ACTIVE' COMMENT 'ACTIVE：启用 ;INACTIVE：禁用',
    `tenant_id`  bigint       NOT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    KEY `user_idx1_username` (`username`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 104
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户表';
BEGIN;
INSERT INTO `sys_user` (`id`, `username`, `password`, `salt`, `phone`, `avatar`, `nickname`, `name`, `email`,
                        `created_by`, `updated_by`, `created_at`, `updated_at`, `status`, `tenant_id`)
VALUES (102, 'test', '{bcrypt}$2a$10$NBCtSz9V8cBV7imwsxjg5OtRdC6kl1a0woYZ7If5Ey9Kak0fZbbMu', '', '123-456-7890',
        'https://example.com/avatar.jpg', 'Test Nickname', NULL, 'test@example.com', 'admin', 'admin',
        '2024-08-26 12:33:45', '2024-08-26 12:35:33', 'ACTIVE', 1001);
INSERT INTO `sys_user` (`id`, `username`, `password`, `salt`, `phone`, `avatar`, `nickname`, `name`, `email`,
                        `created_by`, `updated_by`, `created_at`, `updated_at`, `status`, `tenant_id`)
VALUES (103, 'admin', '{bcrypt}$2a$10$GaU38yqig0IAWFQfoCkJQ.DWIfW8pflTMHxDZHZgXFnp1I31f7.iC', NULL, '123-456-7890',
        'https://example.com/admin_avatar.jpg', 'Admin Nickname', NULL, 'admin@example.com', 'admin', 'admin',
        '2024-08-26 12:35:12', '2024-08-26 12:35:12', 'ACTIVE', 1001);
COMMIT;
DROP TABLE IF EXISTS `sys_tenant`;
CREATE TABLE `sys_tenant`
(
    `id`         bigint       NOT NULL AUTO_INCREMENT COMMENT '租户编码',
    `name`       varchar(100) NOT NULL COMMENT '租户名称',
    `created_at` datetime     NOT NULL DEFAULT (now()) COMMENT '创建时间',
    `created_by` varchar(64)  NOT NULL COMMENT '创建人',
    `status`     varchar(64)           DEFAULT 'ACTIVE' COMMENT 'ACTIVE：启用 ;INACTIVE：禁用',
    `updated_by` varchar(64)  NOT NULL COMMENT '更新人',
    `updated_at` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 110
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='多租户信息表';

-- ----------------------------
-- Records of sys_tenant
-- ----------------------------
BEGIN;
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`)
VALUES (1, 'Tenant A', '2024-08-14 23:13:50', 'admin', 'ACTIVE', 'admin', '2024-08-14 23:13:50');
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`)
VALUES (2, 'Tenant B', '2024-08-14 23:13:50', 'admin', 'ACTIVE', 'admin', '2024-08-14 23:13:50');
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`)
VALUES (3, 'Tenant 1', '2024-08-21 09:48:54', 'admin', 'ACTIVE', 'admin', '2024-08-21 09:49:28');
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`)
VALUES (4, 'Tenant 2', '2024-08-21 09:48:54', 'admin', 'ACTIVE', 'admin', '2024-08-21 09:49:28');
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`)
VALUES (5, 'Tenant 3', '2024-08-21 09:48:54', 'admin', 'INACTIVE', 'admin', '2024-08-21 09:49:28');
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`)
VALUES (6, 'Tenant 4', '2024-08-21 09:48:54', 'admin', 'ACTIVE', 'admin', '2024-08-21 09:49:28');
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`)
VALUES (7, 'Tenant 5', '2024-08-21 09:48:54', 'admin', 'ACTIVE', 'admin', '2024-08-21 09:49:28');
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`)
VALUES (8, 'Tenant 6', '2024-08-21 09:48:54', 'admin', 'ACTIVE', 'admin', '2024-08-21 09:49:28');
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`)
VALUES (9, 'Tenant 7', '2024-08-21 09:48:54', 'admin', 'INACTIVE', 'admin', '2024-08-21 09:49:28');
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`)
VALUES (10, 'Tenant 8', '2024-08-21 09:48:54', 'admin', 'ACTIVE', 'admin', '2024-08-21 09:49:28');
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`)
VALUES (11, 'Tenant 9', '2024-08-21 09:48:54', 'admin', 'ACTIVE', 'admin', '2024-08-21 09:49:28');
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`)
VALUES (12, 'Tenant 10', '2024-08-21 09:48:54', 'admin', 'ACTIVE', 'admin', '2024-08-21 09:49:28');
COMMIT;

DROP TABLE IF EXISTS `sys_user_department`;
CREATE TABLE `sys_user_department`
(
    `id`            bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id`       bigint NOT NULL COMMENT '用户id',
    `department_id` bigint NOT NULL COMMENT '部门id',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `idx_sud_user_dep_id` (`user_id`, `department_id`) USING BTREE,
    KEY `idx_sud_user_id` (`user_id`) USING BTREE,
    KEY `idx_sud_dep_id` (`department_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`        bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id`   bigint NOT NULL COMMENT '用户id',
    `role_id`   bigint NOT NULL COMMENT '角色id',
    `tenant_id` bigint DEFAULT '0' COMMENT '租户ID',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_sur_user_id` (`user_id`) USING BTREE,
    KEY `idx_sur_role_id` (`role_id`) USING BTREE,
    KEY `idx_sur_user_role_id` (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='用户角色表';

