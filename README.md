## 코드 개선 과제

### 24/01/30 
#### 1. Controller Advice 로 예외 공통화 처리하기
`@RestControllerAdvice` 어노테이션을 사용하여 모든 RestController에서 발생하는 예외를 공통적으로 처리하였습니다.
- https://github.com/codekmj1/codeImprovement/blob/master/src/main/kotlin/com/teamsparta/codeimprovement/domain/exception/GlobalExceptionHandler.kt  
#### 2. Service 인터페이스와 구현체 분리하여 추상화 하기
Service 클래스를 인터페이스와 구현체로 분리하였으며, 각 인터페이스 메서드에 주석을 추가하였습니다.

### 24/01/31
#### 1. CustomException 정의


#### 2. Spring AOP 적용


### 24/02/01
#### QueryDSL 을 사용하여 검색 기능 만들기


### 24/02/02
#### Pageable 을 사용하여 페이징 및 정렬 기능 만들기


### 24/02/05
#### 다양한 조건을 동적 쿼리로 처리하기
