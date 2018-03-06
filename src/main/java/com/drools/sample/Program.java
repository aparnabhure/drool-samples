package com.drools.sample;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import com.drools.sample.model.FileHash;
import com.drools.sample.model.Publisher;
import com.drools.sample.rule.RuleTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class Program {

    static public void main(String[] args) throws Exception {

        //Step1: Convert JSON to Object
        RuleTemplate ruleTemplate = getRuleJson();

        //Step2: Convert drl template file with received conditions
        String templateDrl = applyPolicyRuleTemplate(ruleTemplate);

        AlertDecision alertDecision1 = applyRule(templateDrl);

        if(alertDecision1.getDoAlert()){
            System.out.println("TRUE");
        }
    }

    static private RuleTemplate getRuleJson(){
        RuleTemplate ruleTemplate = null;
        try {

            ObjectMapper mapper = new ObjectMapper();
             ruleTemplate = mapper.readValue(Thread.currentThread().getContextClassLoader().getResourceAsStream("add_rule_template.json"), new TypeReference<RuleTemplate>() {
            });
        }catch (Exception e){
            e.printStackTrace();
        }

        return ruleTemplate;
    }

    static private String applyPolicyRuleTemplate(RuleTemplate ruleTemplate) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        ObjectDataCompiler objectDataCompiler = new ObjectDataCompiler();

        data.put("ruleName", ruleTemplate.getName());
        data.put("condition", ruleTemplate.toString());

        return objectDataCompiler.compile(Arrays.asList(data), Thread.currentThread().getContextClassLoader().getResourceAsStream("policy-rule-template.drl"));
    }

    static private AlertDecision applyRule(String drl) throws Exception {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write("src/main/resources/rule.drl", drl);
        kieServices.newKieBuilder(kieFileSystem).buildAll();

        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        KieSession statelessKieSession = kieContainer.getKieBase().newKieSession();

        AlertDecision alertDecision = new AlertDecision();
        statelessKieSession.getGlobals().set("alertDecision", alertDecision);
        statelessKieSession.insert(new Publisher("xyz","p1"));
        statelessKieSession.insert(new FileHash("yyy"));
        statelessKieSession.fireAllRules();

        return alertDecision;
    }
}
