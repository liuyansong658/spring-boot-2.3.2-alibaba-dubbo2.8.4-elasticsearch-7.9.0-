package com.fire.es.facade;

import com.fire.es.entity.FireConfig;
import com.fire.es.entity.FireItem;

import javax.jws.WebService;
import java.util.Map;

/**
 * @author liuyansong
 * @date 2020/11/01
 */
@WebService
public interface FireItemFacade {

    FireConfig getFireConfig(String key);

}
