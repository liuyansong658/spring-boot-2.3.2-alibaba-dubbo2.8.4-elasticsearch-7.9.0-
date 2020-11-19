package com.fire.es.dao;

import com.fire.es.entity.FireItem;

/**
 * @author liuyansong
 * @date 2020/11/01
 */
public interface FireItemDao {

    public abstract void insertFireItem(FireItem fireItem);

    public abstract FireItem getFireItemById(Integer id);

}
