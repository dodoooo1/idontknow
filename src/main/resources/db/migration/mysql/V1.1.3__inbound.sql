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
    status           varchar(32)            default 'ACTIVE'        null comment 'ACTIVE：启用 ;INACTIVE：禁用'
)ENGINE = InnoDB
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
    status               varchar(32)                            default 'ACTIVE'        null comment 'ACTIVE：启用 ;INACTIVE：禁用'
)ENGINE = InnoDB
 DEFAULT CHARSET = utf8mb4
 row_format = dynamic
 collate = utf8mb4_general_ci
    comment '入库单明细' ;


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
CREATE TABLE `sys_dict` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
                            `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型',
                            `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '描述',
                            `created_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
                            `updated_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人',
                            `created_at` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
                            `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注信息',
                            `status`varchar(64)  DEFAULT 'ACTIVE' COMMENT 'ACTIVE：启用 ;INACTIVE：禁用',
                            PRIMARY KEY (`id`) USING BTREE,
                            UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` (`id`, `dict_type`, `description`, `created_by`, `updated_by`, `created_at`, `updated_at`, `remarks`, `status`) VALUES (1, 'log_type', '日志类型', 'admin', 'admin', '2019-03-19 11:06:44', '2024-08-16 12:36:52', '异常、正常', 'ACTIVE');
INSERT INTO `sys_dict` (`id`, `dict_type`, `description`, `created_by`, `updated_by`, `created_at`, `updated_at`, `remarks`, `status`) VALUES (11, 'status_type', '租户状态', 'admin', 'admin', '2019-05-15 16:31:08', '2024-08-16 12:36:52', '租户状态', 'ACTIVE');
INSERT INTO `sys_dict` (`id`, `dict_type`, `description`, `created_by`, `updated_by`, `created_at`, `updated_at`, `remarks`, `status`) VALUES (18, 'lock_flag', '用户状态', 'admin', 'admin', '2023-02-01 16:55:31', '2024-08-16 12:36:52', NULL, 'ACTIVE');
INSERT INTO `sys_dict` (`id`, `dict_type`, `description`, `created_by`, `updated_by`, `created_at`, `updated_at`, `remarks`, `status`) VALUES (22, 'yes_no_type', '是否', 'admin', 'admin', '2023-02-01 16:55:31', '2024-08-16 12:36:52', NULL, 'ACTIVE');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item` (
                                 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
                                 `dict_id` bigint NOT NULL COMMENT '字典ID',
                                 `item_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典项值',
                                 `item_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典项名称',
                                 `sort` int NOT NULL DEFAULT '0' COMMENT '排序（升序）',
                                 `created_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
                                 `updated_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人',
                                 `created_at` datetime DEFAULT (now()) COMMENT '创建时间',
                                 `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '备注信息',
                                 `status` varchar(64) DEFAULT 'ACTIVE' COMMENT 'ACTIVE：启用 ;INACTIVE：禁用',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 UNIQUE KEY `dict_id` (`dict_id`,`item_value`),
                                 KEY `sys_dict_value` (`item_value`) USING BTREE,
                                 KEY `sys_dict_label` (`item_label`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典项';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `item_value`, `item_label`, `sort`, `created_by`, `updated_by`, `created_at`, `updated_at`, `remarks`, `status`) VALUES (1, 1, '9', '异常', 1, 'admin', 'admin', '2019-03-19 11:08:59', '2019-03-25 12:49:13', '', '');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `item_value`, `item_label`, `sort`, `created_by`, `updated_by`, `created_at`, `updated_at`, `remarks`, `status`) VALUES (2, 1, '0', '正常', 0, 'admin', 'admin', '2019-03-19 11:09:17', '2019-03-25 12:49:18', '', '');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `item_value`, `item_label`, `sort`, `created_by`, `updated_by`, `created_at`, `updated_at`, `remarks`, `status`) VALUES (33, 11, '0', '正常', 0, 'admin', 'admin', '2019-05-15 16:31:34', '2019-05-16 22:30:46', '状态正常', '');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `item_value`, `item_label`, `sort`, `created_by`, `updated_by`, `created_at`, `updated_at`, `remarks`, `status`) VALUES (34, 11, '9', '冻结', 1, 'admin', 'admin', '2019-05-15 16:31:56', '2019-05-16 22:30:50', '状态冻结', '');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `item_value`, `item_label`, `sort`, `created_by`, `updated_by`, `created_at`, `updated_at`, `remarks`, `status`) VALUES (58, 18, '0', '有效', 0, 'admin', 'admin', '2023-02-01 16:56:00', '2023-02-01 16:56:00', NULL, '');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `item_value`, `item_label`, `sort`, `created_by`, `updated_by`, `created_at`, `updated_at`, `remarks`, `status`) VALUES (59, 18, '9', '禁用', 1, 'admin', 'admin', '2023-02-01 16:56:09', '2023-02-01 16:56:09', NULL, '');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `item_value`, `item_label`, `sort`, `created_by`, `updated_by`, `created_at`, `updated_at`, `remarks`, `status`) VALUES (66, 22, '0', '否', 0, 'admin', 'admin', '2023-02-20 23:35:23', '2023-02-20 23:35:23', '0', '');
INSERT INTO `sys_dict_item` (`id`, `dict_id`, `item_value`, `item_label`, `sort`, `created_by`, `updated_by`, `created_at`, `updated_at`, `remarks`, `status`) VALUES (67, 22, '1', '是', 0, 'admin', 'admin', '2023-02-20 23:35:37', '2023-02-20 23:35:37', '1', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
                           `id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
                           `module_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模块标题',
                           `business_type` int NOT NULL COMMENT '业务类型（0其它 1新增 2修改 3删除）',
                           `method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '方法名称',
                           `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '请求方式',
                           `operator_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'PC' COMMENT '操作类别（PC,PDA,API,OTHER）',
                           `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求URL',
                           `request_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求参数',
                           `response` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '返回参数',
                           `created_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
                           `updated_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人',
                           `created_at` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
                           `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                           `cost_time` bigint DEFAULT '0' COMMENT '消耗时间',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='操作日志记录';

