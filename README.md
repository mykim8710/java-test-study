# Java Application Test Study

## 1. JUnit5

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
[package me.mykim.javatest.start; | TestCodeStartTest]
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
[package me.mykim.javatest.start; | TestCodeStartTest]
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
[package me.mykim.javatest.taggingandfiltering; | TestTaggingAndFilteringTest]
- 테스트 그룹을 만들고 원하는 테스트 그룹만 테스트를 실행할 수 있는 기능.
- @Tag
  - 테스트 메소드에 태그를 추가할 수 있다.
  - 하나의 테스트 메소드에 여러 태그를 사용 할 수 있다.

### (7) 커스텀 태그
[package me.mykim.javatest.customannotation; | CustomAnnotationTest]
- JUnit 5 애노테이션을 조합하여 커스텀 태그를 만들 수 있음

### (8) 테스트 반복
[package me.mykim.javatest.testrepeat; | RepeatTest]
- @RepeatedTest
  - 반복횟수와 반복테스트 이름을 설정 할 수 있다.
- @ParameterizedTest
  - 테스트에 여러 다른 매개변수를 대입해가며 반복 실행한다.

### (9) 테스트 인스턴스
[package me.mykim.javatest.testinstance; | TestInstanceTest]
- JUnit은 테스트 메소드 마다 테스트 인스턴스를 새로 만든다.
  - 이것이 기본 전략.
  - 테스트 메소드를 독립적으로 실행하여 예상치 못한 부작용을 방지하기 위함  : 테스트 간 의존성을 없애기 위함
  - 이 전략을 JUnit 5에서 변경할 수 있음
- @TestInstance(Lifecycle.PER_CLASS)
  - 테스트 클래스 당 인스턴스를 하나만 만들어 사용한다.
  - 경우에 따라, 테스트 간에 공유하는 모든 상태를 @BeforeEach 또는 @AfterEach에서 초기화 할 필요가 있다.
  - @BeforeAll과 @AfterAll을 인스턴스 메소드 또는 인터페이스에 정의한 default 메소드로 정의할 수도 있다.

### (10) 테스트 순서
[package me.mykim.javatest.testorder; | TestOrderTest]
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


### (12) 확장 모델


### (13) 마이그레이션