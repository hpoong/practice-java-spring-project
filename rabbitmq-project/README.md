## Init
- RabbitMQ 설치
- `pwd.ini` 파일 생성 (아래 참조)

```
Rabbit_Host     =
Rabbit_Port     =
Rabbit_UserName =
Rabbit_Password =
```

# API Endpoints

- [GET http://localhost:8080/api/v1/producer/send/direct-queue](http://localhost:8080/api/v1/producer/send/direct-queue)

- [GET http://localhost:8080/api/v1/producer/send/fanout-queue](http://localhost:8080/api/v1/producer/send/fanout-queue)

- [GET http://localhost:8080/api/v1/producer/send/headers-queue1](http://localhost:8080/api/v1/producer/send/headers-queue1)

- [GET http://localhost:8080/api/v1/producer/send/headers-queue2](http://localhost:8080/api/v1/producer/send/headers-queue2)

- [GET http://localhost:8080/api/v1/producer/send/headers-queue3](http://localhost:8080/api/v1/producer/send/headers-queue3)

- [GET http://localhost:8080/api/v1/producer/send/topic-queue](http://localhost:8080/api/v1/producer/send/topic-queue)