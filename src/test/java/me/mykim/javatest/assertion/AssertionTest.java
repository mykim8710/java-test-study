package me.mykim.javatest.assertion;

import me.mykim.javatest.Study;
import me.mykim.javatest.StudyStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AssertionTest {
    @Test
    @DisplayName("스터디 만들기")
    void studyCreateTest() throws Exception {
        Study study = new Study();

        Assertions.assertNotNull(study);
        Assertions.assertEquals(StudyStatus.DRAFT, study.getStudyStatus(), () ->"스터디 객체를 처음 만들면 상태값이 DRAFT가 되어야 한다.");
        // assertEquals(기대값, 실제값, 실패 시 안내 메세지);
        // 실패 시 안내 메세지 : Supplier<T>, () -> ""
    }


}
