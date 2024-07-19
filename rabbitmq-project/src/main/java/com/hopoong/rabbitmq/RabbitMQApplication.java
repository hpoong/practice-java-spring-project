package com.hopoong.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("file:./pwd.ini")
public class RabbitMQApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitMQApplication.class, args);
	}

//	RabbitMQ를 통한 메시지 큐잉
//	RabbitMQ를 사용하는 메시지 큐잉은 비동기적 메시지 전달을 가능하게 하며, 주로 다음과 같은 특징을 가집니다:
//
//	비동기 통신: 메시지를 큐에 넣은 후 즉시 반환되며, 메시지를 수신하는 소비자는 큐에서 메시지를 비동기적으로 처리합니다. 이는 송신자와 수신자가 동시에 가동될 필요가 없음을 의미합니다.
//	내구성: 메시지는 큐에 저장되며, 소비자가 메시지를 처리할 준비가 되었을 때까지 보관됩니다.
//	로드 밸런싱: 여러 소비자가 동일한 큐에서 메시지를 가져가 처리할 수 있어 부하를 분산시킬 수 있습니다.
//	내결함성: 하나의 소비자가 실패하더라도 큐에 저장된 메시지는 다른 소비자가 처리할 수 있습니다.
//	확장성: 큐와 소비자를 추가하여 시스템의 처리 능력을 쉽게 확장할 수 있습니다.


//	메시지 큐잉이 유리한 경우:
//
//	대용량 데이터 처리: 대량의 메시지를 처리하고, 처리 속도를 조절할 필요가 있을 때.
//	비동기 작업: 이메일 발송, 로그 처리, 배치 작업 등.
//	마이크로서비스 간의 비동기 통신: 서로 다른 마이크로서비스가 비동기적으로 데이터를 주고받을 때.



}
