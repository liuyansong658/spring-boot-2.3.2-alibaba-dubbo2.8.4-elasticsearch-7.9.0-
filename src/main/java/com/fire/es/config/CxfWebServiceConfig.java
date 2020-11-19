package com.fire.es.config;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuyansong
 * @date 2020/11/01
 */
@Configuration
public class CxfWebServiceConfig {

    //默认servlet路径/*,如果覆写则按照自己定义的来
    @Bean
    public ServletRegistrationBean cxfServlet() {
        return new ServletRegistrationBean(new CXFServlet(),
                "/fire-es-master/*");
    }

}
