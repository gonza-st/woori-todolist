# 투두리스트

일정 관리를 위한 Todo 애플리케이션입니다.

## 기능

### Todo 항목 관리

- **Todo 항목 생성 (Create)**
    * 제목, 내용, 마감일, 완료 여부, 생성/수정/완료 날짜 정보 포함
        * 필수 항목
            * 제목
        * 선택 항목
            * 내용, 마감일
        * 사용자 입력이 아닌 자동 부여
            * 완료 여부: 생성시 false 로 부여
            * 생성 날짜: 생성 시점의 날짜로 부여
            * 수정 날짜: 제목, 내용, 마감일 중 하나라도 수정된 시점의 날짜로 부여
- **Todo 항목 조회 (Read)**
    * 단일 Todo 항목 조회 (ID로 조회)
    * 전체 Todo 목록 조회
- **Todo 항목 수정 (Update)**
    * 제목, 내용, 마감일 수정
    * 완료 상태 변경 (완료/미완료)
        * 상태 변경시 관련된 시간은 거기에 맞춰 업데이트
    * 수정 시간 자동 갱신
- **Todo 항목 삭제 (Delete)**
    * 단일 Todo 항목 삭제
    * 선택한 여러 항목 일괄 삭제
    * 완료된 항목만 삭제

### 시스템 설계

- **예외 처리**
    * 적절한 예외 클래스 정의 및 처리
    * 유효성 검사와 에러 응답 포맷 표준화
- **계층 구조**
    * Controller-Service-Repository 패턴 적용
    * 각 레이어의 역할과 책임 분리

## 사용 예시

```kotlin
// Todo 생성
val todo = Todo(
    title = "코틀린 공부",
    content = "코틀린 기본 문법 강의 시청하기",
)

// TodoList에 추가
todoList.addTodo(todo)

// Todo title 변경
todo.updateTitle(newTitle)

/* 완료된 항목 모두 삭제 */
todoList.deleteCompletedTodo()
```

## 프로젝트 구조

- `main.model` - Todo 및 TodoList 데이터 모델
- `main.service` - 비즈니스 로직 처리
- `main.controller` - 사용자 요청 처리
- `main.repository` - 데이터 접근 계층
- `main.exception` - 예외 처리

## 기술 스택

- Kotlin
- Spring Boot (추가 예정)
- JPA (추가 예정)