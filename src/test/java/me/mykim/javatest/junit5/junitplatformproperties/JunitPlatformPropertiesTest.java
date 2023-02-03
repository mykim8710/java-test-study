package me.mykim.javatest.junit5.junitplatformproperties;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JunitPlatformPropertiesTest {
    private int value =1;

    @Order(1)
    @Test
    @DisplayName("junit-platform.properties, 테스트 인스턴스 라이프사이클 설정=per_class 확인 1")
    void testCode1() throws Exception {
        System.out.println("value++ = " + value++); // 1
    }

    @Order(2)
    @Test
    @DisplayName("junit-platform.properties, 테스트 인스턴스 라이프사이클 설정=per_class 확인 2")
    void testCode2() throws Exception {
        System.out.println("value++ = " + value++); // 2
    }

    @Test
    @DisplayName("junit-platform.properties, @Disabled 무시하고 실행하기 확인")
    @Disabled
    void testCode3() throws Exception {
        System.out.println("@Disabled 무시하고 테스트 실행");
    }

    // junit-platform.properties, 테스트 이름 표기 전략 설정 확인 : _ =>  " "
    // @DisplayName()이 우선권이 더 높음
    @Test
    void test_Code_4() throws Exception {
        System.out.println("테스트 이름 표기 전략 설정 확인");
    }
}
