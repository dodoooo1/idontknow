package com.idontknow.business.rules;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RuleDefinition {
    private String name;            // 规则名称
    private String description;     // 规则描述
    private String condition;       // 规则条件 (SpEL 表达式)
    private String action;          // 规则动作 (JavaScript 表达式)
    private boolean enabled;        // 是否启用
}
