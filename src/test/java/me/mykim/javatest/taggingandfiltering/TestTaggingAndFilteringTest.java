package me.mykim.javatest.taggingandfiltering;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class TestTaggingAndFilteringTest {
    /**
     * @Tag("fast")가 붙은 테스트는 로컬에서 돌리고 싶고,
     * @Tag("slow")가 붙은 테스트는 로컬이외에 환경에서 돌리고 싶다.
     */


    @Test
    @DisplayName("@Tag(\"fast\") 이 테스트는 실행하는 데 빠르다.")
    @Tag("fast")
    void testCode1() throws Exception {
        System.out.println("fast test");
    }

    @Test
    @DisplayName("@Tag(\"slow\") 이 테스트는 실행하는 데 오래걸린다.")
    @Tag("slow")
    void testCode2() throws Exception {
        System.out.println("slow test");
    }


}
