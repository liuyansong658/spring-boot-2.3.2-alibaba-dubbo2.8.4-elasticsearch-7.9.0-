package com.fire.es.service.impl;

import com.fire.es.dao.FireConfigDao;
import com.fire.es.dao.FireItemDao;
import com.fire.es.entity.FireConfig;
import com.fire.es.entity.FireItem;
import com.fire.es.service.FireItemService;
import com.fire.es.util.Constants;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author liuyansong
 * @date 2020/11/01
 */
@Service
public class FireItemServiceImpl implements FireItemService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "fireJson")
    private Gson fireJson;

    @Resource
    private FireItemDao fireItemDao;

    @Resource
    private FireConfigDao fireConfigDao;

    @Override
    public void insertFireItem(String id,Map<String, Object> esMaps) {
        String message = esMaps.get(Constants.MESSAGE).toString();
        if(StringUtils.isEmpty(message)){
            return;
        }
        message = message.substring(message.indexOf("."));
        //message为一个json 直接转换成对象
        FireItem fire = fireJson.fromJson(message, FireItem.class);
        fire.setId(id);
        fireItemDao.insertFireItem(fire);

    }


    @Override
    public void updateFireConfig(String key,String value) {
        FireConfig fireConfig = new FireConfig();
        fireConfig.setEsValue(value);
        fireConfig.setEsKey(key);
        this.fireConfigDao.updateFireConfig(fireConfig);
    }

    @Override
    public FireConfig getFireConfig(String key) {
        return this.fireConfigDao.getFireConfig(key);
    }

    public String getValue(Object value){
        return value != null ? value.toString() : null;
    }
//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;





}
