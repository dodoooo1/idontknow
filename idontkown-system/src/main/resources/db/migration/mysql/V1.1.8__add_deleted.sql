
alter table api.sys_role change column deleted deleted boolean default false;
alter table api.sys_role change column deleted_by deleted_by varchar(128) default '';
alter table api.sys_role change column deleted_at deleted_at datetime default null;


alter table api.sys_user change column deleted deleted boolean default false;
alter table api.sys_user change column deleted_by deleted_by varchar(128) default '';
alter table api.sys_user change column deleted_at deleted_at datetime default null;

alter table api.sys_resource change column deleted deleted boolean default false;
alter table api.sys_resource change column deleted_by deleted_by varchar(128) default '';
alter table api.sys_resource change column deleted_at deleted_at datetime default null;

alter table api.sys_organization change column deleted deleted boolean default false;
alter table api.sys_organization change column deleted_by deleted_by varchar(128) default '';
alter table api.sys_organization change column deleted_at deleted_at datetime default null;

alter table api.sys_dictionary change column deleted deleted boolean default false;
alter table api.sys_dictionary change column deleted_by deleted_by varchar(128) default '';
alter table api.sys_dictionary change column deleted_at deleted_at datetime default null;

alter table api.sys_dictionary_entry change column deleted deleted boolean default false;
alter table api.sys_dictionary_entry change column deleted_by deleted_by varchar(128) default '';
alter table api.sys_dictionary_entry change column deleted_at deleted_at datetime default null;
