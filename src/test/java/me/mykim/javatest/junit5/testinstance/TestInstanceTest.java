package me.mykim.javatest.junit5.testinstance;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // 클래스마다 테스트 인스턴스를 생성, 메서드가 하나의 테스트 인스턴스를 공유
class TestInstanceTest {

    /**
     * JUnit은 테스트 메소드 마다 테스트 인스턴스를 새로 만든다.
     * - 이것이 기본 전략.
     * - 테스트 메소드를 독립적으로 실행하여 예상치 못한 부작용을 방지하기 위함 : 테스트 간 의존성을 없애기 위함
     * - 이 전략을 JUnit 5에서 변경할 수 있음
     *
     * - @TestInstance(TestInstance.Lifecycle.PER_CLASS)
     * ㄴ 클래스마다 테스트 인스턴스를 생성, 메서드가 하나의 테스트 인스턴스를 공유
     * ㄴ
     *
     * - @BeforeAll, @AfterAll : 기본적으로 static method로 정의해야함
     * ㄴ @TestInstance(TestInstance.Lifecycle.PER_CLASS)일때, static 일 필요가 없다. : test instance를 공유하기 때문
     */

    @Test
    @DisplayName("test 1")
    void testCode1() throws Exception {
        System.out.println(this);
        /**
         * @TestInstance(TestInstance.Lifecycle.PER_CLASS)
         * X : me.mykim.javatest.junit5.testinstance.TestInstanceTest@4c309d4d
         * O : me.mykim.javatest.junit5.testinstance.TestInstanceTest@2d2ffcb7
         */
    }

    @Test
    @DisplayName("test 2")
    void testCode2() throws Exception {
        System.out.println(this);    //
        /**
         * @TestInstance(TestInstance.Lifecycle.PER_CLASS)
         * X : me.mykim.javatest.junit5.testinstance.TestInstanceTest@21b2e768
         * O : me.mykim.javatest.junit5.testinstance.TestInstanceTest@2d2ffcb7
         */
    }

}
