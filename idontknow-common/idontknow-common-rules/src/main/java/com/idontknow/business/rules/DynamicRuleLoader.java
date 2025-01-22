package com.idontknow.business.rules;

import org.jeasy.rules.api.*;
import org.jeasy.rules.core.*;
import org.jeasy.rules.mvel.MVELRule;

public class DynamicRuleLoader {
    private RulesEngine rulesEngine;
    private Rules rules;

    public DynamicRuleLoader() {
        this.rulesEngine = new DefaultRulesEngine();
        this.rules = new Rules();
    }

    // 加载规则到引擎
    public void loadRules() {
        rules.clear();
        for (RuleDefinition ruleDefinition : RuleLocalStorage.getAllRules()) {
            if (ruleDefinition.isEnabled()) {
                rules.register(new MVELRule());
            }
        }
    }

    // 执行规则
    public void executeRules(Facts facts) {
        rulesEngine.fire(rules, facts);
    }
}
