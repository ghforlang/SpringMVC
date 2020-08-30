package com.edu.nbu.demo.designpatten.state.demo1;

public class RuleTest {

    public static void main(String[] args) {
        process("tencent");
    }

    private static void process(String code){
        ChannelRuleEnum ruleEnum = ChannelRuleEnum.getByCode(code);
        ruleEnum.getChannelRule().process();
    }
}
