package com.fire.es.config;

import com.fire.es.util.DateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Optional;

/**
 * @author liuyansong
 * @date 2020/11/03
 */
@Data
@Configuration
public class ElasticSearchConfig {

    @Value("${elasticsearch.nodes}")
    public String nodes;

    /**
     * 链接建立超时时间
     */
    @Value("${elasticsearch.connectTimeOut:1000}")
    private Integer connectTimeOut;
    /**
     * 等待数据超时时间
     */
    @Value("${elasticsearch.socketTimeOut:30000}")
    private Integer socketTimeOut;
    /**
     * 连接池获取连接的超时时间
     */
    @Value("${elasticsearch.connectionRequestTimeOut:500}")
    private Integer connectionRequestTimeOut;
    /**
     * 最大连接数
     */
    @Value("${elasticsearch.maxConnectNum:30}")
    private Integer maxConnectNum;
    /**
     * 最大路由连接数
     */
    @Value("${elasticsearch.maxConnectPerRoute:10}")
    private Integer maxConnectPerRoute;

    /**
     * 节点请求方案
     */
    private final String HTTP_HOST_SCHEME = "http";

//    @Value("${elasticsearch.cluster-name}")
//    private String clusterName;


    @Bean(value = "fireJson")
    public Gson gson(){
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateAdapter()).create();
        return gson;
    }

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        if (this.nodes != null) {
            //将全部的节点的地址实例化
            String [] nodesHostArr=this.nodes.split(",");
            HttpHost[]  httpHostArr = new HttpHost[nodesHostArr.length];
            for (int i = 0; i < nodesHostArr.length; i++) {
                String[] httpHost = nodesHostArr[i].split(":");
                httpHostArr[i] = new HttpHost(httpHost[0], Integer.parseInt(httpHost[1]));
            }

            //若拆分结果不为空则开始构造 RestClient
            RestClientBuilder restClientBuilder = RestClient.builder(httpHostArr)
                    .setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
                        @Override
                        public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                            return requestConfigBuilder.setConnectTimeout(connectTimeOut) // 连接超时（默认为1秒）
                                    .setSocketTimeout(socketTimeOut)// 套接字超时（默认为30秒）
                                    .setConnectionRequestTimeout(connectionRequestTimeOut);
                        }
                    })
                    // .setMaxRetryTimeoutMillis(60000)//调整最大重试超时时间（默认为30秒）
                    .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                        @Override
                        public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                            return httpClientBuilder
                                    .setMaxConnTotal(maxConnectNum)
                                    .setMaxConnPerRoute(maxConnectPerRoute)
                                    .setDefaultIOReactorConfig(IOReactorConfig.custom().setIoThreadCount(1).build());// 线程数
                        }
                    });

            RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClientBuilder);
            return restHighLevelClient;
        }
        //实例化客户端
//        this.client = new RestHighLevelClient(RestClient.builder(httpHostArr));
        return null;

    }

}
