package com.nbu.edu.info.router.demo.constant;

import com.nbu.edu.info.router.demo.holder.DefaultRouterHolder;
import com.nbu.edu.info.router.demo.holder.Holder;

import java.util.HashMap;
import java.util.Map;

public class RegistryConstant {
    public static final String DEFAULT_ROUTER_HOLDER = "defaultRouterHolder";
    public static final Map<String, Holder> holderMap = new HashMap<>();
    static{
        holderMap.put(DEFAULT_ROUTER_HOLDER,new DefaultRouterHolder());
    }
}
