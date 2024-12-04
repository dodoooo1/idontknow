alter table api.sys_log drop column response;
alter table api.sys_log drop column business_type;
alter table api.sys_log drop column organization_id;
alter table api.sys_log add column error_msg varchar(255) null
