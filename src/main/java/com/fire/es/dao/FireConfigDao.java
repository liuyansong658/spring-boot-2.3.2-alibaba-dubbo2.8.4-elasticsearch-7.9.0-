package com.fire.es.dao;

import com.fire.es.entity.FireConfig;
import com.fire.es.entity.FireItem;

/**
 * @author liuyansong
 * @date 2020/11/01
 */
public interface FireConfigDao {

    public abstract void updateFireConfig(FireConfig fireItem);

    public abstract FireConfig getFireConfig(String key);

}
