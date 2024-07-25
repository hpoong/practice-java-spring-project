package com.hopoong.reactor;

import reactor.core.publisher.Flux;

import java.time.Duration;


public class FluxClass {


    // Flux는 0 또는 다수의 요소를 포함하는 리액티브 타입
    public static void main(String[] args) throws InterruptedException {

        // 기본 Flux 생성 및 구독
        Flux<String> fluxStr = Flux.just("String", "String Boot", "Reactor", "String WebFlux");
        fluxStr.subscribe(System.out::println);
        System.out.println("================================= 1");


        // 값 범위 생성
        Flux<Integer> rangeFlux = Flux.range(1, 5);
        rangeFlux.subscribe(val -> System.out.println(val));
        System.out.println("================================= 2");


        // filter
        Flux<Integer> filterFlux = Flux.range(1, 10).filter(val -> val % 2 == 0);
        filterFlux.subscribe(System.out::println);
        System.out.println("================================= 3");


        // map
        Flux<Integer> mapFlux = Flux.range(1, 5).map(val -> val *  2);
        mapFlux.subscribe(System.out::println);
        System.out.println("================================= 4");


        Flux<String> fluxObj1 = Flux.just("Red", "Green", "Blue");
        Flux<String> fluxObj2 = Flux.just("Circle", "Square", "Triangle");

        // merge (순서 보장 안됨)
        Flux<String> mergedFlux = Flux.merge(fluxObj1, fluxObj2);
        mergedFlux.subscribe(System.out::println);
        System.out.println("================================= 5");


        // concat (순서 보장)
        Flux<String> concatFlux = Flux.concat(fluxObj1, fluxObj2);
        concatFlux.subscribe(System.out::println);
        System.out.println("================================= 6");

        // zip
        Flux<String> zippedFlux = Flux.zip(fluxObj1, fluxObj2, (color, shpape) -> color + " :: " + shpape);
        zippedFlux.subscribe(System.out::println);
        System.out.println("================================= 7");


        // Error
        Flux<Integer> fluxa = Flux.range(1, 10)
                .map(val -> {
                    if(val == 5)
                        throw new RuntimeException("Error at 5");
                    return val;
                }).onErrorResume(e -> Flux.just(-1, -2));

        fluxa.subscribe(val -> System.out.println(val));
        System.out.println("================================= 8");

        // 비동기 작업 처리
        Flux<Long> fluxInterval = Flux.interval(Duration.ofMillis(500)).take(7);
        fluxInterval.subscribe(System.out::println);
        Thread.sleep(1000);
    }



}



