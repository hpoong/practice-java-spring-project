package com.hopoong.reactor;

import reactor.core.publisher.Mono;

public class MonoClass {

    // Mono는 0 또는 1개의 요소를 포함하는 리액티브 타입
    public static void main(String[] args) {

        Mono<String> strMono = Mono.just("Mono");
        strMono.subscribe(System.out::println);
        System.out.println("================================= 1");


        Mono<String> emptStr = Mono.error(new Exception("Error"));
        emptStr.subscribe(
                System.out::println,
                error -> System.out.println("Error"),
                () -> System.out.println("Completed")
        );
        System.out.println("================================= 1");



    }
}
