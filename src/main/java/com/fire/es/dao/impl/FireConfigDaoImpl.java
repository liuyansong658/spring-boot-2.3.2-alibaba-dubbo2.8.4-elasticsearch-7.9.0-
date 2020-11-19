package com.fire.es.dao.impl;

import com.fire.es.dao.FireConfigDao;
import com.fire.es.dao.mapper.FireConfigMapper;
import com.fire.es.entity.FireConfig;
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
public class FireConfigDaoImpl implements FireConfigDao {

    private final static Logger log = LoggerFactory.getLogger(FireConfigDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    static final String insert_fire_sql = "update fire_config set es_value = ? where es_key = ?";
    static final String get_fire_config = "select * from fire_config where es_key = ?";

    @Override
    public void updateFireConfig(FireConfig fireConfig) {
        try {

            int update = jdbcTemplate.update(insert_fire_sql, fireConfig.getEsValue(),fireConfig.getEsKey());

            if(update == 1){
                log.info("updateFireConfig is success..");
            }
        }catch (Exception ex){
            log.error(ex.getMessage(),ex);
        }
    }

    @Override
    public FireConfig getFireConfig(String key) {
        try {

            FireConfig fireConfig = jdbcTemplate.queryForObject(get_fire_config, new FireConfigMapper(), key);
            return fireConfig;
        }catch (Exception ex){
            log.error(ex.getMessage(),ex);
        }
        return null;
    }

}
