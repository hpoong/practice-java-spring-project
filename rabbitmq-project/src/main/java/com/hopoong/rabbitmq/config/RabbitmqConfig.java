package com.hopoong.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.port}")
    private int port;


    /**
     * 1. Exchange 구성합니다.
     * "hello.exchange" 라는 이름으로 Direct Exchange 형태로 구성하였습니다.
     */
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("hello.exchange");
    }

    /**
     * 2. 큐를 구성합니다.
     * "hello.queue"라는 이름으로 큐를 구성하였습니다.
     */
    @Bean
    Queue queue() {
        return new Queue("hello.queue", false);
    }


    /**
     * 3. 큐와 DirectExchange를 바인딩합니다.
     * "hello.key"라는 이름으로 바인딩을 구성하였습니다.
     */
    @Bean
    Binding binding(DirectExchange directExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(directExchange).with("hello.key");
    }


    /**
     * 4. RabbitMQ와의 연결을 위한 ConnectionFactory을 구성합니다.
     * Application.properties의 RabbitMQ의 사용자 정보를 가져와서 RabbitMQ와의 연결에 필요한 ConnectionFactory를 구성합니다.
     */
    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }




}
