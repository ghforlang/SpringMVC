package com.nbu.edu.info.router.demo.holder;

import com.nbu.edu.info.router.demo.router.DefaultRouter;
import com.nbu.edu.info.router.demo.router.Router;

public class DefaultRouterHolder implements Holder<Router>{

    @Override
    public Router get() {
        return RouterInstance.defaultRouter;
    }

    private static class RouterInstance{
        private static DefaultRouter defaultRouter = new DefaultRouter();
    }
}
