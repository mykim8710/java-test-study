package me.mykim.javatest.service;

import lombok.extern.slf4j.Slf4j;
import me.mykim.javatest.domain.Member;
import me.mykim.javatest.domain.Study;
import me.mykim.javatest.exception.MemberNotFoundException;
import me.mykim.javatest.repository.StudyRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudyService {
    private final MemberService memberService;
    private final StudyRepository studyRepository;

    public StudyService(MemberService memberService, StudyRepository studyRepository) {
        assert memberService != null;
        assert studyRepository != null;
        this.memberService = memberService;
        this.studyRepository = studyRepository;
    }

    public Study createStudy(Long memberId, Study study) {
        Member member = memberService.findById(memberId).orElseThrow(() -> new MemberNotFoundException());
        study.setOwner(member);

        Study newStudy = studyRepository.save(study);

        memberService.notify(newStudy);
        memberService.notify(member);

        return newStudy;
    }



    public Study openStudy(Study study) {
        study.open();
        Study openedStudy = studyRepository.save(study);
        memberService.notify(openedStudy);
        return openedStudy;
    }





}
