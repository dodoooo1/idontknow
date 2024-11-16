
-- 删除租户表
DROP TABLE IF EXISTS `sys_tenant`;
DROP TABLE IF EXISTS `sys_user_department`;


alter table sys_role
    add department_id bigint not null comment '部门id';


alter table sys_resource
    add department_id bigint not null comment '部门id';
alter table sys_log
    add department_id bigint not null comment '部门id';

alter table sys_dict
    add department_id bigint not null comment '部门id';

alter table sys_dict_item
    add department_id bigint not null comment '部门id';

alter table base_product_category
    add department_id bigint not null comment '部门id';

alter table base_product
    add department_id bigint not null comment '部门id';

alter table base_container_type
    add department_id bigint not null comment '部门id';


alter table base_container
    add department_id bigint not null comment '部门id';
