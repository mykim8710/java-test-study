package me.mykim.javatest.mockito.service;

import me.mykim.javatest.domain.Member;
import me.mykim.javatest.domain.Study;
import me.mykim.javatest.domain.StudyStatus;
import me.mykim.javatest.exception.MemberNotFoundException;
import me.mykim.javatest.repository.StudyRepository;
import me.mykim.javatest.service.MemberService;
import me.mykim.javatest.service.StudyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.AntPathMatcher;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    /**
     * 구현체가 없고 인터페이스만 알고있고
     * 내가 테스트하려는 클래스가 그 들을 의존할때 Mocking 해야한다.
     */

    @Mock
    MemberService memberService;

    @Mock StudyRepository studyRepository;

    @Test
    @DisplayName("StudyService 객체를 생성하는 테스트")
    void createStudyService() throws Exception {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);
    }

//    @Test
//    @DisplayName("StudyService 객체를 생성하는 테스트2 : 해당 메서드에서만 Mock 객체를 생성해서 사용하고 싶다.")
//    void createStudyService2(@Mock MemberService memberService, @Mock StudyRepository studyRepository) throws Exception {
//        StudyService studyService = new StudyService(memberService, studyRepository);
//        assertNotNull(studyService);
//    }

    @Test
    @DisplayName("Mockito 객체 Stubbing 테스트")
    void createStudyService2() throws Exception {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        // memberService.findById(1L)하면 아래의 Member객체를 반환하도록 Stubbing
        Member member = new Member();
        member.setId(1L);
        member.setEmail("mykim8710@gmail.com");
        Mockito.when(memberService.findById(1L)).thenReturn(Optional.of(member));

        Optional<Member> byId = memberService.findById(1L); // 넘겨주는 파라미터가 1L일때만 이 객체를 반환, ArgumentMatcher를 사용하여 다양한 파라미터를 설정가능
        assertEquals("mykim8710@gmail.com", byId.get().getEmail());


        // memberService.findById(2L)하면 예외를 던지도록 Stubbing
        Mockito.when(memberService.findById(2L)).thenThrow(new MemberNotFoundException());
        assertThrows(MemberNotFoundException.class, () -> {
            memberService.findById(2L);
        });


        // void 메서드를 실행하면 new IllegalArgumentException()를 던지도록 Stubbing
        Mockito.doThrow(new IllegalArgumentException()).when(memberService).validate(3L);
        assertThrows(IllegalArgumentException.class, () -> {
            memberService.validate(3L); // 2L이면 exception발생이 안됨
        });


        // 메소드가 동일한 매개변수로 여러번 호출될 때 각기 다르게 행동하도록 Stubbing
        Member member2 = new Member();
        member2.setId(5L);
        member2.setEmail("mykim@netcube.com");
        Mockito.when(memberService.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(member2))   // 첫번째
                .thenThrow(new RuntimeException())   // 두번째
                .thenReturn(Optional.empty())             // 세번쨰
        ;

        // 첫번째 호출
        Optional<Member> optionalMember1 = memberService.findById(5L);
        assertEquals("mykim@netcube.com", optionalMember1.get().getEmail());

        // 두번째 호출
        assertThrows(RuntimeException.class, () -> {
            memberService.findById(5L);
        });

        // 세번쨰 호출
        Optional<Member> optionalMember2 = memberService.findById(5L);
        assertEquals(Optional.empty(), optionalMember2);
    }


    @Test
    @DisplayName("Mockito 객체 Stubbing 테스트 실전")
    void createStudyService3() throws Exception {
        // given
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("mykim8710@gmail.com");

        Study study = new Study(10, "테스트");

        // TODO memberService 객체에 findById 메소드를 1L 값으로 호출하면 Optional.of(member) 객체를 리턴하도록 Stubbing
        // Mockito.when(memberService.findById(1L)).thenReturn(Optional.of(member));
        // -> given으로 변환(BDD Api)
        BDDMockito.given(memberService.findById(1L)).willReturn(Optional.of(member));

        // TODO studyRepository 객체에 save 메소드를 study 객체로 호출하면 study 객체 그대로 리턴하도록 Stubbing
        // Mockito.when(studyRepository.save(study)).thenReturn(study);
        // -> given으로 변환(BDD Api)
        BDDMockito.given(studyRepository.save(study)).willReturn(study);


        // when
        studyService.createStudy(1L, study);

        // then
        assertEquals(member, study.getOwner());

        // memberService.notify(study); 가 1번은 호출됐어야 한다. => 안하면 exception
        //Mockito.verify(memberService, Mockito.times(1)).notify(study);
        // -> then으로 변환(BDD Api)
        BDDMockito.then(memberService).should(Mockito.times(1)).notify(study);
    }


    @Test
    @DisplayName("Mockito 객체 Stubbing - Mock 객체 확인 테스트")
    void createStudyService4() throws Exception {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("mykim8710@gmail.com");

        Study study = new Study(10, "테스트");

        // TODO memberService 객체에 findById 메소드를 1L 값으로 호출하면 Optional.of(member) 객체를 리턴하도록 Stubbing
        Mockito.when(memberService.findById(1L)).thenReturn(Optional.of(member));

        // TODO studyRepository 객체에 save 메소드를 study 객체로 호출하면 study 객체 그대로 리턴하도록 Stubbing
        Mockito.when(studyRepository.save(study)).thenReturn(study);

        studyService.createStudy(1L, study);
        assertEquals(member, study.getOwner());

        // memberService.notify(study); 가 1번은 호출됐어야 한다. => 안하면 exception
        Mockito.verify(memberService, Mockito.times(1)).notify(study);
        Mockito.verify(memberService, Mockito.times(1)).notify(member);

        // 한번도 호출안되야한다.
        Mockito.verify(memberService, Mockito.never()).validate(ArgumentMatchers.any());

        // 순서대로 호출되어야한다. study, -> member
        InOrder inOrder = Mockito.inOrder(memberService);
        inOrder.verify(memberService).notify(study);
        inOrder.verify(memberService).notify(member);
    }



    @Test
    @DisplayName("다른 사용자가 볼 수 있도록 스터디를 공개한다.")
    void openStudyTest() {
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        Study study = new Study(10, "The Java Test");
        assertNull(study.getOpenedDateTime());

        // TODO studyRepository Mock 객체의 save 메소드를호출 시 study를 리턴하도록 만들기.
        BDDMockito.given(studyRepository.save(study)).willReturn(study);

        // When
        studyService.openStudy(study);

        // Then
        // TODO study의 status가 OPENED로 변경됐는지 확인
        assertEquals(StudyStatus.OPEN, study.getStudyStatus());

        // TODO study의 openedDataTime이 null이 아닌지 확인
        assertNotNull(study.getOpenedDateTime());

        // TODO memberService의 notify(study)가 호출 됐는지 확인.
        BDDMockito.then(memberService).should().notify(study);
    }



}