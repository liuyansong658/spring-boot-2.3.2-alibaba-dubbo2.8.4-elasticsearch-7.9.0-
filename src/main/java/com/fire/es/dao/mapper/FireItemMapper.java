package com.fire.es.dao.mapper;

import com.fire.es.entity.FireItem;
import com.fire.es.util.Constants;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class FireItemMapper implements RowMapper<FireItem> {
    @Override
    public FireItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        FireItem fire = new FireItem();
        fire.setId(rs.getString(Constants.ID));
        return fire;
    }
}
