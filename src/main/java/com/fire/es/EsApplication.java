package com.fire.es;

//import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
//import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author liuyansong
 * @date 2020/11/03
 */
@SpringBootApplication(scanBasePackages = "com.fire.*")
@ImportResource(locations={"classpath:/applicationContext.xml"})
public class EsApplication {

    private static Logger logger = LoggerFactory.getLogger(EsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class, args);
        logger.info("es 启动成功!");
    }
}
