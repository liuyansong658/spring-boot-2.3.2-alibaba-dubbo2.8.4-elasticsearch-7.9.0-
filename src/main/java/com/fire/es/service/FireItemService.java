package com.fire.es.service;

import com.fire.es.entity.FireConfig;

import java.util.Map;

/**
 * @author liuyansong
 * @date 2020/11/01
 */
public interface FireItemService {

    void insertFireItem(String id,Map<String, Object> esMaps);

    void updateFireConfig(String key,String value);

    FireConfig getFireConfig(String key);

}
