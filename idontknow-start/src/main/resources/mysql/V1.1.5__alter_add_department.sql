
alter table sys_role change column department_id organization_id bigint;
alter table sys_resource change column department_id organization_id bigint;
alter table sys_dict change column department_id organization_id bigint;
alter table sys_dict_item change column department_id organization_id bigint;
alter table sys_log change column department_id organization_id bigint;
alter table base_container change column department_id organization_id bigint;
alter table base_container_type change column department_id organization_id bigint;
alter table base_product change column department_id organization_id bigint;
alter table base_product_category change column department_id organization_id bigint;
-- 这些表base_warehouse,wm_inbound_order,wm_inbound_order_delivery,wm_inbound_order_item 增加organization_id字段
alter table base_warehouse add column organization_id bigint;
alter table wm_inbound_order add column organization_id bigint;
alter table wm_inbound_order_delivery add column organization_id bigint;
alter table wm_inbound_order_item add column organization_id bigint;
-- 修改wm_inbound_order_delivery 表名为 wm_inbound_order_shipping
rename table wm_inbound_order_delivery to wm_inbound_order_shipping;


