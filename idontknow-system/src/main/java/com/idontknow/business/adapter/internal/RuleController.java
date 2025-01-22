package com.idontknow.business.adapter.internal;

import com.idontknow.business.rules.DynamicRuleLoader;
import com.idontknow.business.rules.RuleDefinition;
import com.idontknow.business.rules.RuleLocalStorage;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/rules")
public class RuleController {
    private final DynamicRuleLoader ruleLoader = new DynamicRuleLoader();

    @PostMapping
    public String addRule(@RequestBody RuleDefinition rule) {
        RuleLocalStorage.saveRule(rule);
        ruleLoader.loadRules();
        return "Rule added and loaded successfully.";
    }

    @PutMapping("/{name}")
    public String updateRule(@PathVariable String name, @RequestBody RuleDefinition rule) {
        RuleLocalStorage.saveRule(rule);
        ruleLoader.loadRules();
        return "Rule updated and loaded successfully.";
    }

    @DeleteMapping("/{name}")
    public String deleteRule(@PathVariable String name) {
        RuleLocalStorage.deleteRule(name);
        ruleLoader.loadRules();
        return "Rule deleted and rules reloaded successfully.";
    }

    @GetMapping
    public Collection<RuleDefinition> getAllRules() {
        return RuleLocalStorage.getAllRules();
    }
}
