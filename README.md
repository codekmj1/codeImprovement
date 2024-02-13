# 코드 개선 과제

## domain 패키지 : 24/01/30~24/02/05 과제

### 24/01/30 
#### 1. Controller Advice 로 예외 공통화 처리하기
`@RestControllerAdvice` 어노테이션을 사용하여 모든 RestController에서 발생하는 예외를 공통적으로 처리하였습니다.
- 코드: https://github.com/codekmj1/codeImprovement/blob/master/src/main/kotlin/com/teamsparta/codeimprovement/domain/exception/GlobalExceptionHandler.kt
  
#### 2. Service 인터페이스와 구현체 분리하여 추상화 하기
Service 클래스를 인터페이스와 구현체로 분리하였으며, 각 인터페이스 메서드에 주석을 추가하였습니다.
- 코드: https://github.com/codekmj1/codeImprovement/tree/master/src/main/kotlin/com/teamsparta/codeimprovement/domain/post/service
  
### 24/01/31
#### 1. CustomException 정의
애플리케이션에서 발생하는 예외상황은 다양합니다. 이를 처리하기 위해 `RuntimeException`을 상속받아 CustomException을 만들었습니다. TodoList 사이트를 만들면서 발생할 예외 상황을 기준으로 CustomException을 작성하였습니다.
- 코드: https://github.com/codekmj1/codeImprovement/blob/master/src/main/kotlin/com/teamsparta/codeimprovement/domain/exception/CustomExcpetion.kt
  
#### 2. Spring AOP 적용
Spring AOP(Aspect-Oriented Programming)를 사용하여 코드의 가독성과 유지 보수성을 높였습니다. 메서드 호출 전후에 로깅을 수행하는 부가 기능을 추가하였습니다.
- 코드: https://github.com/codekmj1/codeImprovement/blob/master/src/main/kotlin/com/teamsparta/codeimprovement/domain/aop/controller/ParameterAop.kt

### 24/02/01 ~ 24/02/02
#### QueryDSL 설정
1. build.gradle.kts에 설정 추가
2. Channel, Thread Entity 클래스 작성
3. Gradle 메뉴에서 compileKotlin을 더블클릭
4. build/generated/source/kapt/main 디렉토리 확인
5. 앞으로 Entity를 추가하거나 변경할 때 compileKotlin을 통해 QClass들을 업데이트

#### QueryDSL 을 사용하여 검색 기능 만들기 + Pageable 을 사용하여 페이징 및 정렬 기능 만들기
QueryDSL은 직관적인 쿼리를 작성하면서 동적인 쿼리를 작성할 수 있게 해주고, 컴파일 타임에 오류를 잡아줄 수 있는 프레임워크입니다. 복잡한 검색 기능을 구현하기 위해 QueryDSL을 사용하였습니다.

1. QueryDslSupport 클래스 작성
2. ThreadRepository 인터페이스 작성
3. 커스텀 인터페이스를 구현하는 CustomThreadRepository 인터페이스 작성 
4. ThreadRepositoryImpl 클래스 작성 (검색기능 + 페이징 및 정렬 기능)
5. ThreadRepository 인터페이스가 커스텀 인터페이스를 상속받도록 설정

- 코드: https://github.com/codekmj1/codeImprovement/tree/master/src/main/kotlin/com/teamsparta/codeimprovement/domain/thread

### 24/02/05
#### 다양한 조건을 동적 쿼리로 처리하기
QueryDSL을 사용하여 다양한 검색 조건에 따른 동적 쿼리를 처리합니다. 다음의 조건들을 만족하는 동적 쿼리를 작성하였습니다.
- 제목 (포함)
- 태그 (포함)
- 카테고리 (정확히 일치)
- 게시글 상태 (정확히 일치)
- N일전 게시글

1. PostRepository 인터페이스 작성
2. 커스텀 인터페이스를 구현하는 CustomPostRepository 인터페이스 작성 
3. PostRepositoryImpl 클래스 작성 (각각의 검색 조건을 만족하는 쿼리 메소드 작성)
4. PostRepository 인터페이스가 커스텀 인터페이스를 상속받도록 설정

- 코드: https://github.com/codekmj1/codeImprovement/tree/master/src/main/kotlin/com/teamsparta/codeimprovement/domain/post


## domain2 패키지 : 24/02/06~24/02/13 과제

