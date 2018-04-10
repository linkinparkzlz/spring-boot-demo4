package com.example.springbootdemo4;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11Nio2Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class SpringBootDemo4Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemo4Application.class, args);
    }


    @Controller
    public static class MyController {

        @RequestMapping("/message")
        @ResponseBody
        public String message() {
            return "Hello !!!";
        }
    }


    @Bean
    public static EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {

        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {

                if (container instanceof TomcatEmbeddedServletContainerFactory) {

                    TomcatEmbeddedServletContainerFactory factory = TomcatEmbeddedServletContainerFactory.class.cast(container);

                    factory.addContextCustomizers(new TomcatContextCustomizer() {
                        @Override
                        public void customize(Context context) {
                            context.setPath("/spring-boot");
                        }
                    });


                    factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
                        @Override
                        public void customize(Connector connector) {
                            connector.setPort(8080);
                            connector.setProtocol(Http11Nio2Protocol.class.getName());
                        }
                    });
                }
            }
        };

    }


}





//    http://localhost:8080/spring-boot/message



















































