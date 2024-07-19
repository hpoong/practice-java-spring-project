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

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("direct-exchange");
    }

//    @Bean
//    public FanoutExchange fanoutExchange() {
//        return new FanoutExchange("fanout-exchange");
//    }
//
//    @Bean
//    public HeadersExchange headersExchange() {
//        return new HeadersExchange("headers-exchange");
//    }
//
//    @Bean
//    public TopicExchange topicExchange() {
//        return new TopicExchange("topic-exchange");
//    }

    @Bean
    public Queue queue1() {
        return new Queue("queue1", false); //  서버가 재시작되더라도 큐와 큐에 저장된 메시지가 사라짐.
    }

//    @Bean
//    public Queue queue2() {
//        return new Queue("queue2");
//    }
//
//    @Bean
//    public Queue queue3() {
//        return new Queue("queue3");
//    }
//
//    @Bean
//    public Queue queue4() {
//        return new Queue("queue4");
//    }
//
//    @Bean
//    public Queue queue5() {
//        return new Queue("queue5");
//    }

    @Bean
    public Binding binding1a(Queue queue1, DirectExchange directExchange) {
        return BindingBuilder.bind(queue1).to(directExchange).with("routingKey1");
    }

//    @Bean
//    public Binding binding2a(Queue queue2, FanoutExchange fanoutExchange) {
//        return BindingBuilder.bind(queue2).to(fanoutExchange);
//    }
//
//    @Bean
//    public Binding binding3a(Queue queue3, HeadersExchange headersExchange) {
//        Map<String, Object> headers = new HashMap<>();
//        headers.put("headerKey", "headerValue");
//        return BindingBuilder.bind(queue3).to(headersExchange).whereAll(headers).match();
//    }
//
//    @Bean
//    public Binding binding4a(Queue queue4, TopicExchange topicExchange) {
//        return BindingBuilder.bind(queue4).to(topicExchange).with("topic.routing.#");
//    }

}
