alter table api.sys_role drop column status;
alter table api.sys_role add column deleted boolean default false;
alter table api.sys_role add column deleted_by varchar(128) default '';
alter table api.sys_role add column deleted_at datetime default null;


alter table api.sys_user add column deleted boolean default false;
alter table api.sys_user add column deleted_by varchar(128) default '';
alter table api.sys_user add column deleted_at datetime default null;

alter table api.sys_resource drop column status;
alter table api.sys_resource add column deleted boolean default false;
alter table api.sys_resource add column deleted_by varchar(128) default '';
alter table api.sys_resource add column deleted_at datetime default null;

alter table api.sys_organization drop column status;
alter table api.sys_organization add column deleted boolean default false;
alter table api.sys_organization add column deleted_by varchar(128) default '';
alter table api.sys_organization add column deleted_at datetime default null;

alter table api.sys_dict_item drop column status;
alter table api.sys_dict_item add column deleted boolean default false;
alter table api.sys_dict_item add column deleted_by varchar(128) default '';
alter table api.sys_dict_item add column deleted_at datetime default null;

alter table api.sys_dict drop column status;
alter table api.sys_dict add column deleted boolean default false;
alter table api.sys_dict add column deleted_by varchar(128) default '';
alter table api.sys_dict add column deleted_at datetime default null;
