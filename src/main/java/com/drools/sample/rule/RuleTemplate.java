package com.drools.sample.rule;

import com.drools.sample.event.Event;
import com.drools.sample.model.FileHash;
import com.drools.sample.model.Publisher;

import java.util.List;

public class RuleTemplate implements Event {
    private String name;
    private List<RuleConditions> conditions;

    private String conditionTemplate = "%s(%s)";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RuleConditions> getConditions() {
        return conditions;
    }

    public void setConditions(List<RuleConditions> conditions) {
        this.conditions = conditions;
    }

    @Override
    public String toString(){
        StringBuilder statementBuilder = new StringBuilder();

        for (RuleConditions conditions : getConditions()) {
            String concateOperator = null;
            switch (conditions.getCombine()){
                case AND:
                    concateOperator = " && ";
                    break;
                case OR:
                    concateOperator = " || ";
                    break;
            }

            List<RuleCondition> ruleConditions = conditions.getConditionList();
            for(int i=0; i< ruleConditions.size(); i++) {
                RuleCondition condition=  ruleConditions.get(i);
                StringBuilder conditionBuilder = new StringBuilder();

                String field = condition.getField();
                String fieldCheck = null;
                String columnToCompare = null;
                if(field.equalsIgnoreCase("publisher")){
                    fieldCheck = Publisher.class.getName();
                    columnToCompare = "name";
                }else if(field.equalsIgnoreCase("hash")){
                    fieldCheck = FileHash.class.getName();
                    columnToCompare = "hash";
                }


                String operator = null;
                switch (condition.getOperator()) {
                    case EQUAL_TO:
                        operator = "==";
                        break;
                    case NOT_EQUAL_TO:
                        operator = "!=";
                        break;
                    case GREATER_THAN:
                        operator = ">";
                        break;
                    case LESS_THAN:
                        operator = "<";
                        break;
                    case GREATER_THAN_OR_EQUAL_TO:
                        operator = ">=";
                        break;
                    case LESS_THAN_OR_EQUAL_TO:
                        operator = "<=";
                        break;
                }

                conditionBuilder.append(columnToCompare).append(" ").append(operator).append(" ");

                if (condition.getValue() instanceof String) {
                    conditionBuilder.append("'").append(condition.getValue()).append("'");
                } else {
                    conditionBuilder.append(condition.getValue());
                }

                statementBuilder.append(String.format(conditionTemplate, fieldCheck, conditionBuilder.toString()));
                if(i < ruleConditions.size() - 1) {
                    statementBuilder.append(concateOperator);
                }
            }
            statementBuilder.append("\n");
        }

        return statementBuilder.toString();
    }
}
