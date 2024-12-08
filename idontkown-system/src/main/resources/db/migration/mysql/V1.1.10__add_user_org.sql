-- auto-generated definition
create table sys_user_organization
(
    id      bigint auto_increment comment '主键id'
        primary key,
    user_id bigint not null comment '用户id',
    org_id bigint not null comment '组织id'
)
    comment '用户组织机构表' row_format = DYNAMIC;

