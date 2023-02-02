package me.mykim.javatest.testorder;

import org.junit.jupiter.api.*;

//@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestOrderTest {

    /**
     * 테스트의 순서
     * - 특정한 순서에 의해 실행되지만 어떻게 그 순서를 정하는지는 의도적으로 분명히 하지 않는다.
     * - 각 테스트는 의존적이 않아야하며 서로 영향을 미치면 안된다. 그렇기 때문에 순서가 보장되지 않음
     * - 이 순서에 의존하면 안된다.
     *
     * - @TestInstance(Lifecycle.PER_CLASS)와 함께 @TestMethodOrder를 사용
     * ㄴ 통합 테스트,사나리오 테스트 일 때
     *
     * - @TestMethodOrder, @Order만 사용하면 테스트의 순서를 설정할 수 있다.
     */

    @Order(4)
    @Test
    @DisplayName("1번 테스트")
    void testCode1() throws Exception {
        System.out.println("test 1");
    }

    @Order(3)
    @Test
    @DisplayName("2번 테스트")
    void testCode2() throws Exception {
        System.out.println("test 2");
    }

    @Order(2)
    @Test
    @DisplayName("3번 테스트")
    void testCode3() throws Exception {
        System.out.println("test 3");
    }

    @Order(1)
    @Test
    @DisplayName("4번 테스트")
    void testCode4() throws Exception {
        System.out.println("test 4");
    }


}
