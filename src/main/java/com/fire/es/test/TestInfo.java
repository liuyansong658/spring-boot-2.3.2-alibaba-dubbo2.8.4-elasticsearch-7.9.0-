package com.fire.es.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestInfo {

    private Long id ;

    private String birthday;

    private String cnName;

    private Integer isChinese;

    private String updateTime;

}