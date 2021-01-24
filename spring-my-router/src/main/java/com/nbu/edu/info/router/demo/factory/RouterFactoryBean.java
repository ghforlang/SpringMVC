package com.nbu.edu.info.router.demo.factory;

import com.nbu.edu.info.router.demo.constant.RegistryConstant;
import com.nbu.edu.info.router.demo.holder.DefaultRouterHolder;
import com.nbu.edu.info.router.demo.holder.Holder;
import com.nbu.edu.info.router.demo.router.DefaultRouter;
import com.nbu.edu.info.router.demo.router.Router;
import org.springframework.beans.factory.FactoryBean;

public class RouterFactoryBean implements FactoryBean<Router> {

    private boolean defaultValue;

    public boolean getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(boolean defaultValue) {
        this.defaultValue = defaultValue;
    }



    @Override
    public Router getObject() throws Exception {
        if(defaultValue){
             Holder holder = RegistryConstant.holderMap.get(RegistryConstant.DEFAULT_ROUTER_HOLDER);
             return (DefaultRouter)holder.get();
        }
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
