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

### 24/02/06
#### Controller 테스트 코드 작성하기
이 테스트 코드는 HTTP POST 요청을 "/courses" 경로에 보내는 것으로, 새로운 Course를 생성하는 API를 테스트하는 목적을 가지고 있습니다.

**테스트 시나리오:**
1. 새로운 Course를 생성하려고 할 때라는 상황을 설정합니다(Given).

2. POST /courses를 호출하면 이라는 행동을 정의합니다(When).

3. 그 결과로 새로운 Course가 생성되어야 한다는 결과를 예측합니다(Then).

**테스트 방식:**
- 먼저 CreateCourseRequest 객체를 생성하여 새로운 Course의 상세 정보(title, description 등)를 설정합니다.

- CourseService의 createCourse() 메서드를 호출할 때 위에서 생성한 CreateCourseRequest 객체를 반환하도록 mock 설정합니다.

- mockMvc.perform() 메서드를 사용하여 실제 HTTP POST 요청을 "/courses" 경로에 보냅니다. 이때 CreateCourseRequest 객체를 JSON 형식으로 변환하여 요청 본문에 포함시킵니다.

- 반환된 HTTP 응답의 상태 코드가 201(Created)인지 확인합니다.

- 반환된 HTTP 응답의 본문을 CourseResponse 객체로 변환하고, 이 객체가 기대한 값과 일치하는지 확인합니다.

- 코드 : https://github.com/codekmj1/codeImprovement/blob/master/src/test/kotlin/com/teamsparta/codeimprovement/domain2/course/controller/CourseControllerTest.kt
### 24/02/07
#### Service 테스트 코드 작성하기
**특정 Course 조회:** Course 목록이 존재하지 않을 때 특정 Course를 요청하면 ModelNotFoundException이 발생해야 한다는 시나리오를 테스트하고 있습니다. courseService.getCourseById(courseId) 메소드가 특정 ID의 코스를 찾지 못했을 때, 적절한 예외를 발생시키는지 확인하는 테스트입니다.

**테스트 방식:**
- courseRepository.findByIdOrNull(any())가 null을 반환하도록 설정합니다. 이는 코스 목록이 비어 있음을 시뮬레이션합니다.

- courseService.getCourseById(courseId)를 호출하고, ModelNotFoundException이 발생하는지 확인합니다.

**새로운 Course 추가:** 새로운 Course를 추가하려고 할 때 addCourse() 메소드를 호출하면 새로운 Course가 저장되어야 한다는 시나리오를 테스트하고 있습니다. courseService.createCourse(newCourse) 메소드가 새로운 코스를 정상적으로 추가하고, 그 결과를 반환하는지 확인하는 테스트입니다.

**테스트 방식:**
- 새로운 CreateCourseRequest 객체를 생성하고, courseRepository.save(any())가 이 코스를 반환하도록 설정합니다.

- courseService.createCourse(newCourse)를 호출하고, 반환된 객체가 예상대로인지 확인합니다. 이를 위해 savedCourse의 ID와 title이 반환된 객체의 ID와 title과 일치하는지 확인합니다.

- 그리고 반환된 객체의 ID가 null이 아닌지 확인합니다. 이는 새 코스가 정상적으로 저장되었음을 나타냅니다.

- 코드 : https://github.com/codekmj1/codeImprovement/blob/master/src/test/kotlin/com/teamsparta/codeimprovement/domain2/course/service/CourseServiceTest.kt

### 24/02/08
#### Repository 테스트 코드 작성하기
**제목으로 Course 검색:** 새로운 Course를 검색하려고 할 때 searchCourseListByTitle() 메소드를 호출하면 일치하는 Course가 반환되어야 한다는 시나리오를 테스트하고 있습니다.

CourseRepositoryImpl의 searchCourseListByTitle() 메서드는 주어진 제목에 일치하는 코스 목록을 검색하고 반환하는 기능을 합니다. 테스트에서는 이 메서드가 예상한 결과를 반환하는지 확인합니다.

**테스트 방식:**
- searchCourseListByTitle(title)이 주어진 제목에 일치하는 코스 목록을 반환하도록 설정합니다.

- searchCourseListByTitle(title)를 호출하고, 반환된 코스 목록이 예상한 코스 목록과 일치하는지 확인합니다.

**상태와 페이지 정보로 Course 검색:** 특정 상태의 Course를 페이지 단위로 검색하려고 할 때 findByPageableAndStatus() 메소드를 호출하면 일치하는 Course의 페이지가 반환되어야 한다"는 시나리오를 테스트하고 있습니다.

CourseRepositoryImpl의 findByPageableAndStatus() 메서드는 주어진 상태에 일치하고, 주어진 페이지 정보에 따른 코스 목록을 검색하고 반환하는 기능을 합니다. 테스트에서는 이 메서드가 예상한 결과를 반환하는지 확인합니다.

**테스트 방식:**
- findByPageableAndStatus(pageable, status)가 주어진 상태에 일치하고, 주어진 페이지 정보에 따른 코스 목록을 반환하도록 설정합니다.

- findByPageableAndStatus(pageable, status)를 호출하고, 반환된 코스 목록이 예상한 코스 목록과 일치하는지 확인합니다.

- 코드 : https://github.com/codekmj1/codeImprovement/blob/master/src/test/kotlin/com/teamsparta/codeimprovement/domain2/course/repository/CourseRepositoryTest.kt
