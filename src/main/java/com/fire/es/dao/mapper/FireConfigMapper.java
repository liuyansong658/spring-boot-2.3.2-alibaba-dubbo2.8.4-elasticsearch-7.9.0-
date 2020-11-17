package com.fire.es.dao.mapper;

import com.fire.es.entity.FireConfig;
import com.fire.es.entity.FireItem;
import com.fire.es.util.Constants;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FireConfigMapper implements RowMapper<FireConfig> {

    @Override
    public FireConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
        FireConfig fire = new FireConfig();
        fire.setEsKey(rs.getString("es_key"));
        fire.setEsValue(rs.getString("es_value"));
        return fire;
    }
}
