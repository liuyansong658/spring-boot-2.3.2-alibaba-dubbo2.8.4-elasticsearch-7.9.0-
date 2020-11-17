package com.fire.es.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liuyansong
 * @date 2020/11/01
 */
@Data
public class FireConfig implements Serializable {

    private String esKey;
    private String esValue;

}
