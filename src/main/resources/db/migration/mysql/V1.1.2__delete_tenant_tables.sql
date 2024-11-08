
-- 删除租户表
DROP TABLE IF EXISTS `sys_tenant`;
-- 删除其他表中租户字段tenant_id
ALTER TABLE sys_user_role
    DROP COLUMN tenant_id;

ALTER TABLE sys_user
    DROP COLUMN tenant_id;

ALTER TABLE sys_role
    DROP COLUMN tenant_id;

ALTER TABLE sys_resource
    DROP COLUMN tenant_id;

ALTER TABLE sys_department
    DROP COLUMN tenant_id;

