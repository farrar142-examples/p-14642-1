package com.back.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
public class ElasticsearchConfig extends ElasticsearchConfiguration {
    @Value("${elasticsearch.host:localhost}")
    String host;
    @Value("${elasticsearch.port:9200}")
    String port;
    @Override
    public ClientConfiguration clientConfiguration(){
        return ClientConfiguration.builder()
                .connectedTo(
                        String.format("%s:%s", host,port)
                ).build();
    }
}
