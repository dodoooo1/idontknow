package com.idontknow.business.rules;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RuleLocalStorage {
    private static final Map<String, RuleDefinition> ruleMap = new ConcurrentHashMap<>();

    public static void saveRule(RuleDefinition rule) {
        ruleMap.put(rule.getName(), rule);
    }

    public static void deleteRule(String ruleName) {
        ruleMap.remove(ruleName);
    }

    public static RuleDefinition getRule(String ruleName) {
        return ruleMap.get(ruleName);
    }

    public static Collection<RuleDefinition> getAllRules() {
        return ruleMap.values();
    }
}
