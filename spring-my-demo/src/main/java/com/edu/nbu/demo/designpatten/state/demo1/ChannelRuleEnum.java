package com.edu.nbu.demo.designpatten.state.demo1;

import com.edu.nbu.demo.designpatten.state.demo1.rule.GeneralChannelRule;
import com.edu.nbu.demo.designpatten.state.demo1.rule.TencentChannelRule;
import com.edu.nbu.demo.designpatten.state.demo1.rule.ToutiaoChannelRule;

public enum ChannelRuleEnum {
    TENCENT("tencent",new TencentChannelRule()),
    TOUTIAO("toutiao",new ToutiaoChannelRule());


    private String code;
    private GeneralChannelRule channelRule;

    ChannelRuleEnum(String code, GeneralChannelRule channelRule) {
        this.code = code;
        this.channelRule = channelRule;
    }

    public  static ChannelRuleEnum getByCode(String code){
        if(code == null || code.length() == 0){
            return  null;
        }

        for(ChannelRuleEnum ruleEnum : ChannelRuleEnum.values()){
            if(ruleEnum.code.equals(code)){
                return ruleEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public GeneralChannelRule getChannelRule() {
        return channelRule;
    }
}
