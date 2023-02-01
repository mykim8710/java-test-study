package me.mykim.javatest.start;

import me.mykim.javatest.Study;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class TestCodeStartTest {
    @Test
    @DisplayName("testCode 만들어보기")
    void testCode_Start() throws Exception {
        // given
        Study study = new Study();

        // when & then
        assertNotNull(study);
        assertThat(study).isNotNull();
        System.out.println("@Test testCodeStart()");
    }

    /**
     * 기본 @annotaion
     * - @Test
     * - @BeforeAll
     * - @AfterAll
     * - @BeforeEach
     * - @AfterEach
     * - @Disabled
     */
    @Test
    void create1() {
        System.out.println("@Test create1()");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll : 모든 @Test 테스트 시작 전 딱 한번만 호출");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("@AfterAll : 모든 @Test 테스트 시작 후 딱 한번만 호출");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("@BeforeEach : 각 @Test 테스트 시작 전 호출");
    }

    @AfterEach
    void afterEach() {
        System.out.println("@AfterEach : 각 @Test 테스트 시작 후 호출");
    }

    @Test
    @Disabled
    void disabledTest() {
        System.out.println("이 테스트는 깨져서 실행하지마세요.");
    }
}