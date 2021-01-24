package com.nbu.edu.info.router.demo.factory;


public class DefaultShopFactory extends AbstractShopFactory{

    public static AbstractShopFactory newInstance(){
        return FactoryHolder.shopFactory;
    }

    private static class FactoryHolder{
        private static AbstractShopFactory shopFactory = new DefaultShopFactory();
    }

}
