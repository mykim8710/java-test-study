package me.mykim.javatest.junit5.customannotation;

import me.mykim.javatest.junit5.FastTest;
import me.mykim.javatest.junit5.SlowTest;
import org.junit.jupiter.api.DisplayName;

class CustomAnnotationTest {

    /**
     * Cumstom annotation을 정의하고 적용
     */

    @DisplayName("@Tag(\"fast\") 이 테스트는 실행하는 데 빠르다.")
//    @Test
//    @Tag("fast")
    @FastTest
    void testCode1() throws Exception {
        System.out.println("fast test");
    }

    @DisplayName("@Tag(\"slow\")이 테스트는 실행하는 데 오래걸린다.")
//    @Test
//    @Tag("slow")
    @SlowTest
    void testCode2() throws Exception {
        System.out.println("slow test");
    }

}
