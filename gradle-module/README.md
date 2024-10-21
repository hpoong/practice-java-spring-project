

# Multi-Module Project

## 프로젝트 개요

이 프로젝트는 **`core-service`**, **`auth-service`**, `user-service`로 구성된 **멀티 모듈 프로젝트**입니다. 각 서비스는 독립적으로 동작하며, 특정 비즈니스 로직에 맞춰 각자의 책임을 수행합니다. **`core-service`**는 공통적으로 사용하는 엔티티 및 공통 로직을 포함하고, `auth-service`와 `user-service`는 각각 인증 및 사용자 관리 관련 비즈니스 로직을 구현합니다.

### 모듈 구조

- **`core-service`**: 공통 엔티티, 공통 유틸리티 및 공통 기능을 제공하는 모듈입니다. `auth-service`와 `user-service`는 `core-service`의 엔티티 및 공통 로직을 참조합니다.
- **`auth-service`**: 인증 및 권한 관리와 관련된 로직을 처리하는 모듈입니다. `core-service`의 공통 엔티티를 활용하여 사용자 인증 및 권한 관련 작업을 수행합니다.
- **`user-service`**: 사용자 관리와 관련된 기능을 처리하는 모듈입니다. 사용자 목록 조회, 사용자 정보 관리 등의 기능을 담당합니다.



# API Endpoints

- [GET http://localhost:19100/auth/login?userId=1](http://localhost:19100/auth/login?userId=1)

- [GET http://localhost:19200/users/list](http://localhost:19200/users/list)