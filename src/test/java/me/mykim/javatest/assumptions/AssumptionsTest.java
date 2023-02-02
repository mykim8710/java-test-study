package me.mykim.javatest.assumptions;

import me.mykim.javatest.Study;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class AssumptionsTest {

    @Test
    @DisplayName("Assumptions.assumeTrue")
    void testCode1() throws Exception {
        String test_env = System.getenv("TEST_ENV"); // 시스템 환경변수 가져오기
        System.out.println("test_env = " + test_env);

        assumeTrue("LOCAL".equalsIgnoreCase(test_env)); // 이 조건이 맞디면 하부의 테스트 코드를 실행
        Study study = new Study(10);
        assertEquals(10, study.getLimit());
    }

    @Test
    @DisplayName("Assumptions.assumingThat")
    void testCode2() throws Exception {
        String test_env = System.getenv("TEST_ENV"); // 시스템 환경변수 가져오기
        System.out.println("test_env = " + test_env);

        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
            System.out.println("하부코드 동작");
            Study study = new Study(10);
            assertEquals(10, study.getLimit());
        });

        assumingThat(test_env == null, () -> {
            System.out.println("하부코드 동작");
            Study study = new Study(10);
            assertEquals(10, study.getLimit());
        });
    }

    @Test
    @DisplayName("@Enabled")
    @EnabledOnOs({OS.MAC, OS.LINUX})    // 운영체제가 mac, linux인 경우 실행, <-> @DisabledOn....
    void testCode3 () throws Exception {
        System.out.println("테스트 코드 실행");
        Study study = new Study(10);
        assertEquals(10, study.getLimit());
    }
}
