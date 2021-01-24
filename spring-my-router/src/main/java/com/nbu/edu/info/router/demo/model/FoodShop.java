package com.nbu.edu.info.router.demo.model;

import com.nbu.edu.info.router.demo.Shop;

public class FoodShop implements Shop {

    @Override
    public void sell() {
        System.out.println("i just sell food");
    }
}
