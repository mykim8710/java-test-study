# Java Application Test Study

## Part1. JUnit5

### (1) 소개
- 자바 개발자가 가장 많이 사용하는 테스팅 프레임워크
- 단위 테스트를 작성하는 자바 개발자 93%가 JUnit을 사용
- 자바 8 이상을 필요로 함
- 대체제: TestNG, Spock ...
- JUnit5 
  - 2017년 공개
  - SpringBoot Version 2.2로 올리면서 기본 JUnit 버전을 5로 올림
  - JUnit5 => {Platformm, Jupiter, Vintage}
    - Platform : 테스트를 실행해주는 런처 제공. TestEngine API 제공.
    - Jupiter : TestEngine API 구현체로 JUnit 5를 제공.**
    - Vintage : JUnit 4와 3을 지원하는 TestEngine 구현체.

### (2) 시작
[package me.mykim.javatest.junit5.start; | TestCodeStartTest]
- SpringBoot Version 2.2이상 프로젝트 생성 시 자동으로 JUnit5 의존성 추가
- 기본 애노테이션
  - @Test
  - @BeforeAll  : 모든 @Test 테스트 시작 전 딱 한번만 호출
  - @AfterAll   : 모든 @Test 테스트 시작 후 딱 한번만 호출
  - @BeforeEach : 각 @Test 테스트 시작 전 호출
  - @AfterEach  : 각 @Test 테스트 시작 후 호출
  - @Disabled   : 실행하지 않은 단위테스트 
  ......
 
### (3) 테스트 이름 표시
[package me.mykim.javatest.junit5.start; | TestCodeStartTest]
- @DisplayNameGeneration
  - Method와 Class 레퍼런스를 사용해서 테스트 이름을 표기하는 방법 설정.
  - 기본 구현체로 ReplaceUnderscores 제공
- @DisplayName
  - 어떤 테스트인지 테스트 이름을 보다 쉽게 표현할 수 있는 방법을 제공하는 애노테이션.
  - @DisplayNameGeneration 보다 우선 순위가 높다.

### (4) Assertions
[package me.mykim.javatest.assertions; | AssertionsTest]
- org.junit.jupiter.api.Assertions.* 주요 메서드
  - assertEqulas(expected, actual) - 실제 값이 기대한 값과 같은지 확인
  - assertNotNull(actual) - 값이 null이 아닌지 확인
  - assertTrue(boolean) - 다음 조건이 참(true)인지 확인
  - assertAll(executables...) - 모든 확인 구문 확인
  - assertThrows(expectedType, executable) - 예외 발생 확인
  - assertTimeout(duration, executable) - 특정 시간 안에 실행이 완료되는지 확인
- 마지막 매개변수로 Supplier<String> 타입의 인스턴스를 람다 형태로 제공할 수 있다.
  - 복잡한 메시지 생성해야 하는 경우
  - 람다식으로 만들면 문자식 연산을 최대한 필요한 시점에 진행
  - 만약 일반적으로 “message”로 한다면 테스트 케이스가 성공, 실패하든 무조건 연산하므로 문자열 연산에 대한 비용이 걱정된다면 람다식을 사용

### (5) Assumptions
[package me.mykim.javatest.assumptions; | AssumptionsTest]
- 특정한 조건을 만족하는 경우에 테스트를 실행하는 방법. : 특정 OS, 자바버전, 시스템변수 …
- org.junit.jupiter.api.Assumptions.* 주요 메서드
  - assumeTrue(조건)
  - assumingThat(조건, 테스트)
- @Enabled___ 와 @Disabled___
  - OnOS
  - OnJre
  - IfSystemProperty
  - IfEnvironmentVariable
  - If

### (6) 태깅과 필터링
[package me.mykim.javatest.junit5.taggingandfiltering; | TestTaggingAndFilteringTest]
- 테스트 그룹을 만들고 원하는 테스트 그룹만 테스트를 실행할 수 있는 기능.
- @Tag
  - 테스트 메소드에 태그를 추가할 수 있다.
  - 하나의 테스트 메소드에 여러 태그를 사용 할 수 있다.

### (7) 커스텀 태그
[package me.mykim.javatest.customannotation; | CustomAnnotationTest]
- JUnit 5 애노테이션을 조합하여 커스텀 태그를 만들 수 있음

### (8) 테스트 반복
[package me.mykim.javatest.junit5.testrepeat; | RepeatTest]
- @RepeatedTest
  - 반복횟수와 반복테스트 이름을 설정 할 수 있다.
- @ParameterizedTest
  - 테스트에 여러 다른 매개변수를 대입해가며 반복 실행한다.

### (9) 테스트 인스턴스
[package me.mykim.javatest.junit5.testinstance; | TestInstanceTest]
- JUnit은 테스트 메소드 마다 테스트 인스턴스를 새로 만든다.
  - 이것이 기본 전략.
  - 테스트 메소드를 독립적으로 실행하여 예상치 못한 부작용을 방지하기 위함  : 테스트 간 의존성을 없애기 위함
  - 이 전략을 JUnit 5에서 변경할 수 있음
- @TestInstance(Lifecycle.PER_CLASS)
  - 테스트 클래스 당 인스턴스를 하나만 만들어 사용한다.
  - 경우에 따라, 테스트 간에 공유하는 모든 상태를 @BeforeEach 또는 @AfterEach에서 초기화 할 필요가 있다.
  - @BeforeAll과 @AfterAll을 인스턴스 메소드 또는 인터페이스에 정의한 default 메소드로 정의할 수도 있다.

### (10) 테스트 순서
[package me.mykim.javatest.junit5.testorder; | TestOrderTest]
- 실행할 테스트 메소드는 특정한 순서에 의해 실행되지만 어떻게 그 순서를 정하는지는 의도적으로 분명히 하지 않는다.
  - 테스트 인스턴스를 테스트 마다 새로 만드는 것과 같은 이유
  - 각 테스트는 의존적이 않아야하며 서로 영향을 미치면 안된다. 그렇기 때문에 순서가 보장되지 않음
  - 이 순서에 의존하면 안된다.
- 경우에 따라, 특정 순서대로 테스트를 실행하고 싶을 때도 있다.
- 그 경우에는 테스트 메소드를 원하는 순서에 따라 실행하도록 `@TestInstance(Lifecycle.PER_CLASS)`와 함께 `@TestMethodOrder`를 사용할 수 있다. : 통합, 시나리오 테스트 시
- @TestMethodOrder
  - MethodOrderer 구현체를 설정한다.
  - 기본구현체
    - Alphanumeric : a,b,c….
    - **OrderAnnoation : 1,2,3….**
    - Random

### (11) junit-platform.properties
- JUnit 설정 파일
- 위치(클래스패스 루트) : src/test/resources/junit-platform.properties
```
## 테스트 인스턴스 라이프사이클 설정
junit.jupiter.testinstance.lifecycle.default=per_class

## 확장팩 자동 감지 기능(default=false)
junit.jupiter.extensions.autodetection.enabled=true

## @Disabled 무시하고 실행하기
junit.jupiter.conditions.deactivate=org.junit.*DisabledCondition

## 테스트 이름 표기 전략 설정
junit.jupiter.displayname.generator.default=org.junit.jupiter.api.DisplayNameGenerator$ReplaceUnderscores
```

### (12) 확장 모델
- JUnit 4의 확장 모델
  - `@RunWith(Runner), TestRule, MethodRule`
- JUnit 5의 확장 모델
  - 단 하나, `Extension`
- 확장팩 등록 방법
  - 선언적인 등록 : `@ExtendWith`
  - 프로그래밍 등록 : `@RegisterExtension`
  - 자동 등록 : 자바 ServiceLoader 이용(default, false)
    - in `src/test/resources/junit-platform.properties`
    - junit.jupiter.extensions.autodetection.enabled=true

- 확장팩 만드는 방법
  - 테스트 실행 조건
  - 테스트 인스턴스 팩토리
  - 테스트 인스턴스 후-처리기
  - 테스트 매개변수 리졸버
  - 테스트 라이프사이클 콜백
  - 예외처리
  - ...

### (13) 마이그레이션
- junit-vintage-engine을 의존성으로 추가하면, JUnit 5의 junit-platform으로 JUnit 3과 4로 작성된 테스트를 실행할 수 있다.
```
testImplementation group: 'org.junit.vintage', name: 'junit-vintage-engine', version: '5.9.2'
```


## Part2. Mockito

### (1) Mockito 소개
- Mock : 진짜 객체와 비슷하게 동작하지만 프로그래머가 직접 그 객체의 행동을 관리하는 객체
- Mockito
  - Mock 객체를 쉽게 만들고 관리하고 검증할 수 있는 방법을 제공하는 프레임워크
  - 테스트를 작성하는 자바 개발자 50%+ 사용하는 Mock 프레임워크
  - 현재 최신 버전 : 3.1.0(2023.2)
  - 대체제: EasyMock, JMock

### (2) Mockito 시작
- 스프링 부트 2.2+ 프로젝트 생성시 spring-boot-starter-test에서 자동으로 Mockito 추가해 줌.
- 스프링 부트 쓰지 않는다면, 의존성 직접 추가.
```
[maven]
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>3.1.0</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-junit-jupiter</artifactId>
    <version>3.1.0</version>
    <scope>test</scope>
</dependency>


[gradle]
// https://mvnrepository.com/artifact/org.mockito/mockito-core
testImplementation group: 'org.mockito', name: 'mockito-core', version: '3.10.0'

// https://mvnrepository.com/artifact/org.mockito/mockito-junit-jupiter
testImplementation group: 'org.mockito', name: 'mockito-junit-jupiter', version: '3.10.0'
```
- 다음 세 가지만 알면 Mock을 활용한 테스트를 쉽게 작성할 수 있음
  - Mock을 만드는 방법
  - Mock이 어떻게 동작해야 하는지 관리하는 방법
  - Mock의 행동을 검증하는 방법

### (3) Mock 객체 만들기
- @Mock 애노테이션으로 만드는 방법
  - JUnit 5 extension으로 MockitoExtension을 사용해야 한다.
  - 필드
  - 메소드 매개변수
  ```
  @ExtendWith(MockitoExtension.class)
  class StudyServiceTest {
      @Mock MemberService memberService;
      @Mock StudyRepository studyRepository;
  
      @Test
      @DisplayName("StudyService 객체를 생성하는 테스트")
      void createStudyService() throws Exception {
          StudyService studyService = new StudyService(memberService, studyRepository);
          assertNotNull(studyService);
      }
  
      @Test
      @DisplayName("StudyService 객체를 생성하는 테스트2 : 해당 메서드에서만 Mock 객체를 생성해서 사용하고 싶다.")
      void createStudyService2(@Mock MemberService memberService, @Mock StudyRepository studyRepository) throws Exception {
          StudyService studyService = new StudyService(memberService, studyRepository);
          assertNotNull(studyService);
      }
  
  }
  ```

### (4) Mock 객체 Stubbing
- Stubbing  : Mock객체의 행동을 정의
- 모든 Mock 객체의 행동
  - Null을 리턴한다. (Optional 타입은 Optional.empty 리턴)
  - Primitive 타입은 기본 Primitive 값
  - 콜렉션은 비어있는 콜렉션.
  - void 메소드는 예외를 던지지 않고 아무런 일도 발생하지 않는다.
- Mock 객체를 조작해서
  - 특정한 매개변수를 받은 경우 특정한 값을 리턴하거나 예외를 던지도록 만들 수 있다.
  - void 메소드 특정 매개변수를 받거나 호출된 경우 예외를 발생 시킬 수 있다.
  - 메소드가 동일한 매개변수로 여러번 호출될 때 각기 다르게 행동하도록 조작할 수도 있다.

### (5) Mock 객체 확인
- Mock 객체가 어떻게 사용이 됐는지 확인할 수 있다.
  - 특정 메소드가 특정 매개변수로 몇번 호출 되었는지, 최소 한번은 호출 됐는지, 전혀 호출되지 않았는지 확인
  - 어떤 순서대로 호출했는지 확인
  - 특정 시간 이내에 호출됐는지 확인
  - 특정 시점 이후에 아무 일도 벌어지지 않았는지 확인

### (6) BDD 스타일 Mockito API
- BDD(Behavior Driven Development)
  - 애플리케이션이 어떻게 "행동"해야 하는지에 대한 공통된 이해를 구성하는 방법으로, TDD(Test Driven Development)에서 창안

- 행동에 대한 스팩
  - Title(제목)
  - Narrative(설명)
    - As a / I want / so that
      - As a  : ~한 역할로서
      - I want : ~한 것을 나는 원한다.
      - so that : ~것을 위해
  - Acceptance criteria
    - Given / When / Then
      - Given : ~이 주어졌을때
      - When : ~를 하면
      - Then : ~이 기대된다.

- Mockito는 **BddMockito**라는 클래스를 통해 BDD 스타일의 API를 제공