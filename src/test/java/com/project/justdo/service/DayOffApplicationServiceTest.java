package com.project.justdo.service;

import com.project.justdo.domain.DayOff;
import com.project.justdo.domain.DayOffApplication;
import com.project.justdo.domain.Member;
import com.project.justdo.domain.repository.DayOffApplicationRepository;
import com.project.justdo.domain.repository.DayOffApprovalRepository;
import com.project.justdo.domain.repository.DayOffRepository;
import com.project.justdo.domain.repository.MemberRepository;
import com.project.justdo.service.dto.DayOffApplicationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class DayOffApplicationServiceTest {
    @Autowired
    private DayOffApplicationService service;

    @Autowired
    private DayOffApplicationRepository repository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private DayOffRepository dayOffRepository;

    @Autowired
    private DayOffApprovalRepository dayOffApprovalRepository;

    @BeforeEach
    void init() {
        Member 베니스 = new Member("베니스");
        memberRepository.save(베니스);
        memberRepository.save(new Member("아메리"));
        memberRepository.save(new Member("케이크"));
        memberRepository.save(new Member("아이스"));

        dayOffRepository.save(new DayOff(2022, 15, 베니스));
    }


    @Test
    void 연차신청서_작성() {
        DayOffApplicationDto dto = new DayOffApplicationDto(
                memberRepository.findByName("베니스").getId(), "2022-09-05", "2022-09-09",
                List.of(memberRepository.findByName("아메리").getId(), memberRepository.findByName("케이크").getId()),
                "010-1234-5678",
                memberRepository.findByName("아이스").getId(),
                "개인 사유로 연차를 신청합니다."
        );

        Long registeredId = service.registerDayOffApplication(dto);

        DayOffApplication dayOffApplication = repository.findById(registeredId).get();

        assertThat(dayOffApplication).isNotNull();
        assertThat(dayOffApplication.getDayOffApprovals().getList()).hasSize(2);
        System.out.println();
    }
}
