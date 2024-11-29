-- 修改部门表名称 为 sys_organization
alter table sys_department rename to sys_organization;
-- 删除用户角色关联表sys_user_role中的status,department_id,create_time,update_time字段,create_by,update_by字段,update_by字段
alter table sys_user_role drop column status;
alter table sys_user_role drop column department_id;
alter table sys_user_role drop column created_at;
alter table sys_user_role drop column updated_at;
alter table sys_user_role drop column created_by;
alter table sys_user_role drop column updated_by;


-- 修改用户表的部门department_id 为 organization_id
alter table sys_user change column department_id organization_id bigint;


