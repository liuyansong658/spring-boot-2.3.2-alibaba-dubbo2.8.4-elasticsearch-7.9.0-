package com.fire.es.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.SerializedName;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Date;

/**
 * @author liuyansong
 * @date 2020/11/01
 */
@Data
public class FireItem implements Serializable {

    private String id;

}
