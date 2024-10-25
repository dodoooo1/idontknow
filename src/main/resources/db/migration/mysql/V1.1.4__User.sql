DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                  `parent_id` bigint NOT NULL COMMENT '父机构ID',
                                  `name` varchar(100) NOT NULL COMMENT '机构/部门名称',
                                  `sort` int DEFAULT '0' COMMENT '排序',
                                  `description` varchar(500) DEFAULT '' DEFAULT NULL COMMENT '描述',
                                  `type` varchar(32) NOT NULL DEFAULT 'CORPORATION' COMMENT '部门类型 CORPORATION公司，ORGANIZATION组织机构，POST岗位',
                                  `level` varchar(32) NOT NULL DEFAULT 'TOP_DEPARTMENT' COMMENT '部门级别 TOP_DEPARTMENT一级部门 CHILD_DEPARTMENT子部门',
                                  `code` varchar(64)  NOT NULL COMMENT '机构编码',
                                  `mobile` varchar(32)  DEFAULT ''  COMMENT '手机号',
                                  `address` varchar(100)  DEFAULT ''  COMMENT '地址',
                                  `created_by` varchar(64) NOT NULL COMMENT '创建人',
                                  `updated_by` varchar(64)  NOT NULL  COMMENT '修改人',
                                  `created_at` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
                                  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                  `remarks` varchar(255)  DEFAULT ''  COMMENT '备注信息',
                                  `status` varchar(64)  DEFAULT 'ACTIVE' COMMENT 'ACTIVE：启用 ;INACTIVE：禁用',
                                  `tenant_id` bigint DEFAULT '0' COMMENT '租户ID',
                                  `iz_leaf` tinyint(1) DEFAULT '1' COMMENT '是否有叶子节点',
                                  PRIMARY KEY (`id`) USING BTREE,
                                  UNIQUE KEY `uniq_depart_org_code` (`code`) USING BTREE,
                                  KEY `idx_sd_parent_id` (`parent_id`) USING BTREE,
                                  KEY `idx_sd_depart_order` (`sort`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='组织机构表';


-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
                                `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                `parent_id` bigint NOT NULL COMMENT '父id',
                                `name` varchar(255) NOT NULL COMMENT '菜单标题',
                                `url` varchar(255) NOT NULL COMMENT '路径',
                                `component` varchar(255) NOT NULL COMMENT '组件',
                                `is_route` tinyint(1) DEFAULT '1' COMMENT '是否路由菜单',
                                `component_name` varchar(255) NOT NULL COMMENT '组件名字',
                                `redirect` varchar(255)  NULL COMMENT '一级菜单跳转地址',
                                `level` varchar(32) NOT NULL COMMENT '菜单级别(TOP_MENU:一级菜单; CHILD_MENU:子菜单:BUTTON:按钮权限)',
                                `code` varchar(32) NOT NULL COMMENT '权限标识',
                                `sort` int DEFAULT 1 COMMENT '菜单排序',
                                `icon` varchar(255)  DEFAULT '' COMMENT '菜单图标',
                                `is_leaf` tinyint(1) DEFAULT 1 COMMENT '是否叶子节点',
                                `keep_alive` tinyint(1) DEFAULT 0 COMMENT '是否缓存该页面',
                                `hidden` tinyint(1) DEFAULT 0 COMMENT '是否隐藏路由',
                                `hide_tab` tinyint(1) DEFAULT 0 COMMENT '是否隐藏tab',
                                `description` varchar(255)  DEFAULT '' COMMENT '描述',
                                `created_by` varchar(64)  NOT NULL  COMMENT '创建人',
                                `updated_by` varchar(64)  NOT NULL COMMENT '修改人',
                                `created_at` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
                                `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                `remarks` varchar(255) DEFAULT '' COMMENT '备注信息',
                                `status` varchar(64)  DEFAULT 'ACTIVE' COMMENT 'ACTIVE：启用 ;INACTIVE：禁用',
                                `open_type` varchar(255) DEFAULT 'INTERNAL' COMMENT '外链菜单打开方式 internal/内部打开 external/外部打开',
                                `tenant_id` bigint DEFAULT '0' COMMENT '租户ID',
                                PRIMARY KEY (`id`) USING BTREE,
                                KEY `index_menu_type` (`level`) USING BTREE,
                                KEY `index_menu_hidden` (`hidden`) USING BTREE,
                                KEY `index_menu_status` (`status`) USING BTREE,
                                KEY `index_menu_url` (`url`) USING BTREE,
                                KEY `index_menu_sort_no` (`sort`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='菜单权限表';


DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                            `name` varchar(200)  NOT NULL COMMENT '角色名称',
                            `code` varchar(100)  NOT NULL COMMENT '角色编码',
                            `description` varchar(255)  DEFAULT '' COMMENT '描述',
                            `created_by` varchar(64) NOT NULL DEFAULT '' COMMENT '创建人',
                            `updated_by` varchar(64)  NOT NULL DEFAULT '' COMMENT '修改人',
                            `created_at` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
                            `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                            `tenant_id` bigint DEFAULT '0' COMMENT '租户ID',
                            PRIMARY KEY (`id`) USING BTREE,
                            UNIQUE KEY `uniq_sys_role_role_code` (`code`) USING BTREE,
                            KEY `idx_sys_role_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色表';

DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
                                     `id` bigint NOT NULL AUTO_INCREMENT,
                                     `role_id` bigint NOT NULL COMMENT '角色id',
                                     `resources_id` bigint NOT NULL COMMENT '权限id',
                                     PRIMARY KEY (`id`) USING BTREE,
                                     KEY `idx_srp_role_per_id` (`role_id`,`resources_id`) USING BTREE,
                                     KEY `idx_srp_role_id` (`role_id`) USING BTREE,
                                     KEY `idx_srp_resources_id` (`resources_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色权限表';
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                            `username` varchar(64)  NOT NULL COMMENT '用户名',
                            `password` varchar(255)  NOT NULL COMMENT '密码',
                            `salt` varchar(255)  DEFAULT '123@abc*qwe' COMMENT '盐值',
                            `phone` varchar(20)  DEFAULT '' COMMENT '电话号码',
                            `avatar` varchar(255)  DEFAULT '' COMMENT '头像',
                            `nickname` varchar(64)  DEFAULT '' COMMENT '昵称',
                            `name` varchar(64)  DEFAULT '' COMMENT '姓名',
                            `email` varchar(128)  NOT NULL COMMENT '邮箱地址',
                            `created_by` varchar(64)  NOT NULL  COMMENT '创建人',
                            `updated_by` varchar(64) NOT NULL COMMENT '修改人',
                            `created_at` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
                            `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                            `status` varchar(64)  DEFAULT 'ACTIVE' COMMENT 'ACTIVE：启用 ;INACTIVE：禁用',
                            `tenant_id` bigint NOT NULL,
                            PRIMARY KEY (`id`) USING BTREE,
                            KEY `user_idx1_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';
BEGIN;
INSERT INTO `sys_user` (`id`, `username`, `password`, `salt`, `phone`, `avatar`, `nickname`, `name`, `email`, `created_by`, `updated_by`, `created_at`, `updated_at`, `status`, `tenant_id`) VALUES (102, 'test', '{bcrypt}$2a$10$NBCtSz9V8cBV7imwsxjg5OtRdC6kl1a0woYZ7If5Ey9Kak0fZbbMu', '', '123-456-7890', 'https://example.com/avatar.jpg', 'Test Nickname', NULL, 'test@example.com', 'admin', 'admin', '2024-08-26 12:33:45', '2024-08-26 12:35:33', 'ACTIVE', 1001);
INSERT INTO `sys_user` (`id`, `username`, `password`, `salt`, `phone`, `avatar`, `nickname`, `name`, `email`, `created_by`, `updated_by`, `created_at`, `updated_at`, `status`, `tenant_id`) VALUES (103, 'admin', '{bcrypt}$2a$10$GaU38yqig0IAWFQfoCkJQ.DWIfW8pflTMHxDZHZgXFnp1I31f7.iC', NULL, '123-456-7890', 'https://example.com/admin_avatar.jpg', 'Admin Nickname', NULL, 'admin@example.com', 'admin', 'admin', '2024-08-26 12:35:12', '2024-08-26 12:35:12', 'ACTIVE', 1001);
COMMIT;
DROP TABLE IF EXISTS `sys_tenant`;
CREATE TABLE `sys_tenant` (
                              `id` bigint NOT NULL AUTO_INCREMENT COMMENT '租户编码',
                              `name` varchar(100)  NOT NULL COMMENT '租户名称',
                              `created_at` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
                              `created_by` varchar(64)  NOT NULL COMMENT '创建人',
                              `status` varchar(64)  DEFAULT 'ACTIVE' COMMENT 'ACTIVE：启用 ;INACTIVE：禁用',
                              `updated_by` varchar(64)  NOT NULL COMMENT '更新人',
                              `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='多租户信息表';

-- ----------------------------
-- Records of sys_tenant
-- ----------------------------
BEGIN;
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`) VALUES (1, 'Tenant A', '2024-08-14 23:13:50', 'admin', 'ACTIVE', 'admin', '2024-08-14 23:13:50');
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`) VALUES (2, 'Tenant B', '2024-08-14 23:13:50', 'admin', 'ACTIVE', 'admin', '2024-08-14 23:13:50');
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`) VALUES (3, 'Tenant 1', '2024-08-21 09:48:54', 'admin', 'ACTIVE', 'admin', '2024-08-21 09:49:28');
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`) VALUES (4, 'Tenant 2', '2024-08-21 09:48:54', 'admin', 'ACTIVE', 'admin', '2024-08-21 09:49:28');
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`) VALUES (5, 'Tenant 3', '2024-08-21 09:48:54', 'admin', 'INACTIVE', 'admin', '2024-08-21 09:49:28');
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`) VALUES (6, 'Tenant 4', '2024-08-21 09:48:54', 'admin', 'ACTIVE', 'admin', '2024-08-21 09:49:28');
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`) VALUES (7, 'Tenant 5', '2024-08-21 09:48:54', 'admin', 'ACTIVE', 'admin', '2024-08-21 09:49:28');
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`) VALUES (8, 'Tenant 6', '2024-08-21 09:48:54', 'admin', 'ACTIVE', 'admin', '2024-08-21 09:49:28');
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`) VALUES (9, 'Tenant 7', '2024-08-21 09:48:54', 'admin', 'INACTIVE', 'admin', '2024-08-21 09:49:28');
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`) VALUES (10, 'Tenant 8', '2024-08-21 09:48:54', 'admin', 'ACTIVE', 'admin', '2024-08-21 09:49:28');
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`) VALUES (11, 'Tenant 9', '2024-08-21 09:48:54', 'admin', 'ACTIVE', 'admin', '2024-08-21 09:49:28');
INSERT INTO `sys_tenant` (`id`, `name`, `created_at`, `created_by`, `status`, `updated_by`, `updated_at`) VALUES (12, 'Tenant 10', '2024-08-21 09:48:54', 'admin', 'ACTIVE', 'admin', '2024-08-21 09:49:28');
COMMIT;

DROP TABLE IF EXISTS `sys_user_department`;
CREATE TABLE `sys_user_department` (
                                       `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                                       `user_id` bigint NOT NULL COMMENT '用户id',
                                       `department_id` bigint NOT NULL COMMENT '部门id',
                                       PRIMARY KEY (`id`) USING BTREE,
                                       UNIQUE KEY `idx_sud_user_dep_id` (`user_id`,`department_id`) USING BTREE,
                                       KEY `idx_sud_user_id` (`user_id`) USING BTREE,
                                       KEY `idx_sud_dep_id` (`department_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
                                 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                 `user_id` bigint NOT NULL COMMENT '用户id',
                                 `role_id` bigint NOT NULL COMMENT '角色id',
                                 `tenant_id` bigint DEFAULT '0' COMMENT '租户ID',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 KEY `idx_sur_user_id` (`user_id`) USING BTREE,
                                 KEY `idx_sur_role_id` (`role_id`) USING BTREE,
                                 KEY `idx_sur_user_role_id` (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

