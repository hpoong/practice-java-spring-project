package com.hopoong.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

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



// Exchange 유형 설명
// RabbitMQ Direct Exchange : ‘라우팅 키‘를 기반으로 메시지를 큐로 라우팅합니다.
//                             바인딩 키가 메시지의 라우팅 키와 '정확히 일치하는 큐'로 메시지가 라우팅됩니다.
//
// RabbitMQ Fanout Exchange  : 라우팅 키에 관계없이 바인딩된 ‘모든 큐‘로 메시지를 라우팅합니다.
//                             여러 소비자에게 메시지를 브로드캐스트하는데 유용합니다.
//
// RabbitMQ Topic Exchange   : 라우팅 키의 ‘라우팅 패턴 매칭‘에 따라 메시지를 큐로 라우팅합니다.
//                             라우팅 패턴 간의 와일드카드(*) 혹은 해시(#)가 일치해야만 수행합니다.
//
// RabbitMQ Headers Exchange : 라우팅 키 대신 '헤더 속성 값'에 따라 메시지를 큐로 라우팅합니다.
//                             헤더 값은 소비자가 지정한 헤더와 일치해야 메시지가 라우팅됩니다.



    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }


    // Exchange *********************************
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("direct-exchange");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout-exchange");
    }

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange("headers-exchange");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topic-exchange");
    }

    // Direct Exchange *********************************
    @Bean
    public Queue directQueue() {
        return new Queue("directQueue", false);
    }

    @Bean
    public Queue directQueue1() {
        return new Queue("directQueue1", false);
    }

    // Fanout Exchange *********************************
    @Bean
    public Queue fanoutQueue() {
        return new Queue("fanoutQueue", false);
    }

    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanoutQueue1", false);
    }

    // Headers Exchange *********************************
    @Bean
    public Queue headersQueue1() {
        return new Queue("headersQueue1", false);
    }

    @Bean
    public Queue headersQueue2() {
        return new Queue("headersQueue2", false);
    }

    @Bean
    public Queue headersQueue3() {
        return new Queue("headersQueue3", false);
    }

    // Topic Exchange *********************************
    @Bean
    public Queue topicQueue() {
        return new Queue("topicQueue", false);
    }


    @Bean
    public Binding bindingDirectQueue(Queue directQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with("routingKey1");
    }

    @Bean
    public Binding bindingDirectQueue1(Queue directQueue1, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue1).to(directExchange).with("routingKey2");
    }

    @Bean
    public Binding bindingFanoutQueue(Queue fanoutQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue).to(fanoutExchange);
    }

    @Bean
    public Binding bindingFanoutQueue1(Queue fanoutQueue1, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }



    @Bean
    public Binding bindingHeadersQueue1(Queue headersQueue1, HeadersExchange headersExchange) {
        // 모든 헤더 조건이 일치해야 함 (whereAll 사용)
        Map<String, Object> headers = new HashMap<>();
        headers.put("headerKey", "headerValue");
        return BindingBuilder.bind(headersQueue1).to(headersExchange).whereAll(headers).match();
    }

    @Bean
    public Binding bindingHeadersQueue2(Queue headersQueue2, HeadersExchange headersExchange) {
        // 조건: 하나의 헤더 조건이라도 일치하면 됨 (whereAny 사용)
        Map<String, Object> headers = new HashMap<>();
        headers.put("headerKey1", "headerValue1");
        headers.put("headerKey2", "headerValue2");
        return BindingBuilder.bind(headersQueue2).to(headersExchange).whereAny(headers).match();
    }

    @Bean
    public Binding bindingHeadersQueue3(Queue headersQueue3, HeadersExchange headersExchange) {
        // 모든 헤더 조건이 일치해야 함 (whereAll 사용)
        Map<String, Object> headers = new HashMap<>();
        headers.put("headerKey3", "headerValue3");
        headers.put("headerKey4", "headerValue4");
        headers.put("headerKey5", "headerValue5");
        return BindingBuilder.bind(headersQueue3).to(headersExchange).whereAll(headers).match();
    }

    @Bean
    public Binding bindingTopicQueue(Queue topicQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue).to(topicExchange).with("topic.routing.#");
    }

}
