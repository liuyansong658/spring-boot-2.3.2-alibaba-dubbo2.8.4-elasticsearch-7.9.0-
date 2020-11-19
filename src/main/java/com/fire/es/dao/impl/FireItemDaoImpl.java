package com.fire.es.dao.impl;

import com.fire.es.dao.FireItemDao;
import com.fire.es.entity.FireItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author liuyansong
 * @date 2020/11/01
 */
@Repository
public class FireItemDaoImpl implements FireItemDao {

    private final static Logger log = LoggerFactory.getLogger(FireItemDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    static final String insert_fire_sql = "INSERT INTO fire_item(id) VALUES " +
            "(?)";

    @Override
    public void insertFireItem(FireItem fire) {
        try {
            int update = jdbcTemplate.update(insert_fire_sql, fire.getId());
            if(update == 1){
                log.info("insertFireItem is success..");
            }
        }catch (Exception ex){
            log.error(ex.getMessage(),ex);
        }
    }

    @Override
    public FireItem getFireItemById(Integer id) {
        return null;
    }
}
