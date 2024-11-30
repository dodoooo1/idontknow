rename table api.sys_dict to api.sys_dictionary;
rename table api.sys_dict_item to api.sys_dictionary_entry;
alter table api.sys_dictionary_entry change column item_label entry_label varchar(255) not null;
alter table api.sys_dictionary_entry change column item_value entry_value varchar(255) not null;


