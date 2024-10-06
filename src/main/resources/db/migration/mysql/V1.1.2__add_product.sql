CREATE TABLE `base_product`
(
    `id`                        BIGINT                     primary key                              NOT NULL COMMENT '物料ID',
    `category_id`               VARCHAR(64)                                                                 DEFAULT '' COMMENT '物料种类ID',
    `code`                      VARCHAR(200)                                                                DEFAULT '' COMMENT '物料编码',
    `name`                      VARCHAR(255)                                                                DEFAULT '' COMMENT '物料名称',
    `name_en`                   VARCHAR(255)                                                                DEFAULT '' COMMENT '商品英文名称',
    `unit`                      VARCHAR(200)                                                                DEFAULT '' COMMENT '物料单位',
    `image_url`                 TEXT                                                                         COMMENT '图片地址',
    `validity_days`             INT                                                                     default 0 COMMENT '有效期天数',
    `category_code`             VARCHAR(200)                                                                DEFAULT '' COMMENT '物料种类编码',
    `category_name`             VARCHAR(200)                                                                DEFAULT '' COMMENT '物料种类名称',
    `warning_quantity`          DECIMAL(18, 2)                                                               DEFAULT 0 COMMENT '预警数量',
    `validity_warning_days`     INT                                                                     DEFAULT 0 COMMENT '预警天数',
    `max_quantity`              DECIMAL(18, 2)                                                               DEFAULT 0 COMMENT '最大库存',
    `min_quantity`              DECIMAL(18, 2)                                                               DEFAULT 0 COMMENT '保留库存（非紧急出库不可使用）',
    `specification`             VARCHAR(200)                                                                DEFAULT '' COMMENT '规格',
    `is_damp_proof`              TINYINT                                                                 DEFAULT 0 COMMENT '是否防潮 (0: 否, 1: 是)',
    `is_allow_mixed_lots`       TINYINT                                                                 DEFAULT 0 COMMENT '是否允许混批 (0: 否, 1: 是)',
    `is_allow_mixed_items`      TINYINT                                                                  DEFAULT 0 COMMENT '是否允许混料 (0: 否, 1: 是)',
    `is_priority`               TINYINT                                                                  DEFAULT 0 COMMENT '是否优先 (0: 否, 1: 是)',
    `is_allow_mixed_quality`    TINYINT                                                                  DEFAULT 0 COMMENT '是否允许合格不合格混放 (0: 否, 1: 是)',
    `manufacturer`              VARCHAR(200)                                                                DEFAULT '' COMMENT '生产厂家',
    `unit_quantity`             DECIMAL(18, 2)                                                               DEFAULT 0 COMMENT '单位数量',
    `inspection_percentage`     DECIMAL(18, 2)                                                              DEFAULT 0 COMMENT '来料抽检百分比',
    `box_inspection_percentage` DECIMAL(18, 2)                                                              DEFAULT 0 COMMENT '每箱抽检百分比',
    `stacking_type`             VARCHAR(50)                                                                 DEFAULT '' COMMENT '码垛类型',
    `container_type`            VARCHAR(50)                                                                 DEFAULT '' COMMENT '入库容器类型',
    `is_marked`                 TINYINT                                                                  DEFAULT 0 COMMENT '贴标状态 (0: 否, 1: 是)',
    `is_quality_checked`        TINYINT                                                                  DEFAULT 0 COMMENT '质检需求状态 (0: 否, 1: 是)',
    `is_special`                TINYINT                                                                  DEFAULT 0 COMMENT '特殊物料状态 (0: 否, 1: 是)',
    `is_damaged`                TINYINT                                                                  DEFAULT 0 COMMENT '破坏性质检状态 (0: 否, 1: 是)',
    `is_lot_controlled`         TINYINT                                                                 DEFAULT 0 COMMENT '批号控制状态 (0: 否, 1: 是)',

    `warehouse_code`            VARCHAR(200)                  not null                                              DEFAULT '' COMMENT '仓库编码',
    `created_by`                varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL       DEFAULT '' COMMENT '创建人',
    `updated_by`                varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL       DEFAULT '' COMMENT '修改人',
    `created_at`                datetime                                                     NOT NULL       DEFAULT (now()) COMMENT '创建时间',
    `updated_at`                datetime                                                     NOT NULL       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `remarks`                   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci               DEFAULT NULL COMMENT '备注信息',
    `status`                    enum ('ACTIVE','INACTIVE') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'INACTIVE' COMMENT 'ACTIVE：启用 ;INACTIVE：禁用'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  collate = utf8mb4_general_ci COMMENT ='商品信息表';

-- auto-generated definition
create table base_product_category
(
    id               bigint  not null comment '物料种类ID'
        primary key,
    code           varchar(200) not null default '' comment '物料种类编码',
    name           varchar(200) not null default '' comment '物料种类名称',
    parent_id           bigint  not null comment 'parent_id = 0 表示顶级',
    `warehouse_code`            VARCHAR(200)                  not null                                              DEFAULT '' COMMENT '仓库编码',
    `created_by`                varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL       DEFAULT '' COMMENT '创建人',
    `updated_by`                varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL       DEFAULT '' COMMENT '修改人',
    `created_at`                datetime                                                     NOT NULL       DEFAULT (now()) COMMENT '创建时间',
    `updated_at`                datetime                                                     NOT NULL       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `remarks`                   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci               DEFAULT NULL COMMENT '备注信息',
    `status`                    enum ('ACTIVE','INACTIVE') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'INACTIVE' COMMENT 'ACTIVE：启用 ;INACTIVE：禁用',
        UNIQUE KEY `unique_code` (`code`)  -- 添加唯一索引
)
    comment '产品种类表';

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
    `status`     enum ('ACTIVE','INACTIVE') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'INACTIVE' COMMENT 'ACTIVE：启用 ;INACTIVE：禁用',
    UNIQUE KEY `unique_code` (`code`)  -- 添加唯一索引
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='仓库';


