package com.nbu.edu.info.router;

import com.nbu.edu.info.router.demo.Shop;
import com.nbu.edu.info.router.demo.factory.DefaultShopFactory;
import com.nbu.edu.info.router.demo.model.DrinkShop;
import com.nbu.edu.info.router.demo.model.FoodShop;
import com.nbu.edu.info.router.demo.model.SugarShop;


import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Shop> shopList = new ArrayList<>();
        shopList.add(new SugarShop());
        shopList.add(new DrinkShop());
        shopList.add(new FoodShop());

        shopList.forEach(shop ->{
            DefaultShopFactory.newInstance().registerShop(shop.getClass().getName(),shop);
        });

        DefaultShopFactory.newInstance().getShop(DrinkShop.class.getName()).sell();
        DefaultShopFactory.newInstance().getShop(FoodShop.class.getName()).sell();
        DefaultShopFactory.newInstance().getShop(SugarShop.class.getName()).sell();
    }
}
