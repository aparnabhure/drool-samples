package com.drools.sample.rule;

import com.drools.sample.model.Operator;

import java.util.List;

public class RuleConditions {
    private Operator combine;
    private List<RuleCondition> conditionList;

    public Operator getCombine() {
        return combine;
    }

    public void setCombine(Operator combine) {
        this.combine = combine;
    }

    public List<RuleCondition> getConditionList() {
        return conditionList;
    }

    public void setConditionList(List<RuleCondition> conditionList) {
        this.conditionList = conditionList;
    }
}
