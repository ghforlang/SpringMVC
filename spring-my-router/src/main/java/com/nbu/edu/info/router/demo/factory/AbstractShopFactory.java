package com.nbu.edu.info.router.demo.factory;

import com.nbu.edu.info.router.demo.Shop;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractShopFactory {

    private static final Map<String, Shop> shopMap = new ConcurrentHashMap<>();

    public Shop getShop(String shopType){
        return shopMap.get(shopType);
    }

    public boolean registerShop(String shopType,Shop shop){
        if(shopType == null || shopType.length() <=0 || Objects.isNull(shop)){
            System.out.println("无效参数！");
            return false;
        }
        shopMap.put(shopType,shop);
        return true;
    }
}
