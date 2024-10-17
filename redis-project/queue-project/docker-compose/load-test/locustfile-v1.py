from locust import task, FastHttpUser, between
import json
import threading  # Timer를 사용하기 위한 모듈 임포트

# 전역 queueId 카운터
queue_counter = 1

class WriteFileProcessing(FastHttpUser):
    connection_timeout = 10.0
    network_timeout = 10.0

    # 1분 후 테스트 종료 설정
    def on_start(self):
        threading.Timer(60.0, self.stop_locust).start()  # 60초 타이머 시작

    @task
    def processTask(self):
        global queue_counter

        # 순차적으로 queueId 생성
        queue_id = f"queueId_{queue_counter}"
        queue_counter += 1

        # 요청 바디 생성
        payload = {
            "queueId": queue_id
        }

        # POST 요청으로 바디를 전송
        response = self.client.post(
            "/files/async/write/enqueue",
            data=json.dumps(payload),  # JSON으로 직렬화
            headers={"Content-Type": "application/json"}
        )

        # 응답 상태 확인
        print(f"Sent queueId: {queue_id}, Status: {response.status_code}")

    # Locust 테스트를 중지하는 메소드
    def stop_locust(self):
        print("Stopping Locust after 60 seconds")
        self.environment.runner.quit()  # Locust 종료