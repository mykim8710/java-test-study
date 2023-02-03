package me.mykim.javatest.junit5.junit4migration;

import org.junit.Before;
import org.junit.Test;

/**
 * 현재 이 프로젝트는 JUnit5가 default
 * JUnit4 기반으로 작성된 테스트 코드
 */

public class Junit4MigrationTest {

    @Before
    public void before() {
        System.out.println("Before");
    }

    @Test
    public void junit4Test() {
        System.out.println("테스트 실행");
    }

}
