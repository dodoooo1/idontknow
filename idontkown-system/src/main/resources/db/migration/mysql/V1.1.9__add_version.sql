
alter table api.sys_role add column version INT DEFAULT 0 NOT NULL COMMENT '版本号';

alter table api.sys_user add column version INT DEFAULT 0 NOT NULL COMMENT '版本号';

alter table api.sys_resource add column version INT DEFAULT 0 NOT NULL COMMENT '版本号';

alter table api.sys_organization add version INT DEFAULT 0 NOT NULL COMMENT '版本号';

alter table api.sys_dictionary add column version INT DEFAULT 0 NOT NULL COMMENT '版本号';

alter table api.sys_dictionary_entry add column version INT DEFAULT 0 NOT NULL COMMENT '版本号';
