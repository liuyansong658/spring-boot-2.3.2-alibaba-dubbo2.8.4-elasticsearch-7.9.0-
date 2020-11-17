package com.fire.es.controller;

import com.fire.es.entity.FireConfig;
import com.fire.es.service.FireItemService;
import com.fire.es.util.Constants;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.alibaba.dubbo.config.annotation.Reference;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author liuyansong
 * @date 2020/11/01
 */

@RestController
@RequestMapping(value = "item")
public class ItemController {

    // 定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger log = LoggerFactory.getLogger(ItemController.class);

//    @Autowired
    @Resource
    private FireItemService fireItemService;


    @Resource
    private RestHighLevelClient client;

    @Resource(name = "fireJson")
    private Gson fireJson;


    @GetMapping("/query")
    @ApiOperation(value="多个入参测试接口", notes="测试用例")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "indices", value = "索引id", required = true, defaultValue = "fire-syslog"),
            @ApiImplicitParam(paramType="query", name = "index", value = "开始数", required = true, defaultValue = "1"),
            @ApiImplicitParam(paramType="query", name = "size", value = "页数", required = true, defaultValue = "2")
    })
    public List<Map<String, Object>> query(@RequestParam("index") Integer index,
                                           @RequestParam("size") Integer size,
                                           @RequestParam("indices") String indices
    ) throws IOException {
        SearchRequest request = new SearchRequest(indices);
         //构造bool查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(Constants.TIMESTAMP);
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.000'");
//            String currentTime = dateFormat.format(new java.util.Date());

        /**
         * 根据最后一次记录的时间升序查询
         */
        FireConfig fireConfig = this.fireItemService.getFireConfig(indices);
        rangeQueryBuilder.gt(fireConfig.getEsValue());
        boolQueryBuilder.filter(rangeQueryBuilder);

        /**
         * 查询需要匹配的index
         */
        FireConfig indexKeys = this.fireItemService.getFireConfig("index_keys");


        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //排序
        searchSourceBuilder.sort(SortBuilders.fieldSort(Constants.TIMESTAMP).order(SortOrder.ASC));
        //分页
        searchSourceBuilder.from(index).size(size).query(boolQueryBuilder);
        request.searchType(SearchType.DEFAULT).source(searchSourceBuilder);
        List<Map<String, Object>> list = new ArrayList<>();
        SearchHits hits = client.search(request, RequestOptions.DEFAULT).getHits();
        String esValue = null;
        for (SearchHit s : hits.getHits()) {
            Map<String, Object> esMaps = s.getSourceAsMap();
            esValue = esMaps.get(Constants.TIMESTAMP).toString();
            fireItemService.insertFireItem(s.getId(),esMaps);
            list.add(s.getSourceAsMap());
        }
        if(!StringUtils.isEmpty(esValue)){
            this.fireItemService.updateFireConfig(indices,esValue);
        }
        return list;
    }
}
