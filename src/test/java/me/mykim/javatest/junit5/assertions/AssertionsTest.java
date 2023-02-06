package me.mykim.javatest.junit5.assertions;

import me.mykim.javatest.domain.Study;
import me.mykim.javatest.domain.StudyStatus;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class AssertionsTest {
    @Test
    @DisplayName("스터디 만들기")
    void testCode1() throws Exception {
        // given
        Study study = new Study();
        study.setStudyStatus(StudyStatus.DRAFT);
        study.setLimit(10);

        assertNotNull(study);
        assertEquals(StudyStatus.DRAFT, study.getStudyStatus(), () -> "스터디 객체를 처음 만들면 상태값이 " +StudyStatus.DRAFT +"가 되어야 한다.");
        assertTrue(study.getLimit() > 0, "스터디 최대 참석인원은 0보다 커야한다.");
    }

    @Test
    @DisplayName("스터디 만들기 : assertAll")
    @Disabled
    void testCode2() throws Exception {
        // given
        Study study = new Study();
        study.setStudyStatus(StudyStatus.START);
        study.setLimit(-10);

        /**
         * 첫번째 테스트가 실패한다면 그 하위 테스트는 작동하지않는다.
         * -> assertAll(Executable)로 묶으면 각 테스트 별 성공, 실패여부 확인 가능
         */
        assertAll(
                () -> assertNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStudyStatus(), () -> "스터디 객체를 처음 만들면 상태값이 " +StudyStatus.DRAFT +"가 되어야 한다."),
                () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석인원은 0보다 커야한다.")
        );
    }

    @Test
    @DisplayName("스터디 만들기 : assertThrows")
    void testCode3() throws Exception {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            Study study = new Study(-1);
        });
        assertEquals("limit은 0보다 커야한다.", illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("스터디 만들기 : assertTimeout")
    @Disabled
    void testCode4() throws Exception {
        /**
         * 이 상태면 {} 코드블럭안에 코드들이 모두 돌아야 이 테스트는 종료된다.
         */
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(1000);  // 1초
        });

        /**
         * 코드블럭 {}내 코드가 모두 돌아야 마치는 것이 아니라 assertTimeout()에서 설정한 기대시간이 지나면
         * 테스트가 종료되고 싶다면
         * assertTimeout() => assertTimeoutPreemptively()를 사용하면 된다.
         *
         * [주의] {} 코드블럭은 별개의 Thread 실행,
         * Spring의 Transactional은 Thread Local에서 실행되므로 코드블럭의 {}의 Thread에 공유가 안됨 => 테스트 후 롤백이 안될 수 있음
         * 시간이 걸리더라도 assertTimeout()을 사하는것이 안전
         */
    }

}
