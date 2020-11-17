package com.fire.es.facade.impl;


import com.fire.es.entity.FireConfig;
import com.fire.es.facade.FireItemFacade;
import com.fire.es.service.FireItemService;

import javax.annotation.Resource;
import javax.jws.WebService;

@WebService(targetNamespace = "com.fire.es.facade",endpointInterface = "com.fire.es.facade.FireItemFacade")
public class FireItemFacadeImpl implements FireItemFacade {

    @Resource
    private FireItemService fireItemService;

    @Override
    public FireConfig getFireConfig(String key) {
        FireConfig fireConfig = fireItemService.getFireConfig(key);
        return fireConfig;
    }
}
