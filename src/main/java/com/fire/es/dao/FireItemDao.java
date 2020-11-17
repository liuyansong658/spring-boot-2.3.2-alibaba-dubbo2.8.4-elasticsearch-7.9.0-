package com.fire.es.dao;

import com.fire.es.entity.FireItem;

public interface FireItemDao {

    public abstract void insertFireItem(FireItem fireItem);

    public abstract FireItem getFireItemById(Integer id);

}
