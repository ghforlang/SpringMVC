package com.nbu.edu.info.router.demo.router;

public class DefaultRouter implements Router{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    @Override
    public Object getRouterBean() {
        return new Object();
    }
}
