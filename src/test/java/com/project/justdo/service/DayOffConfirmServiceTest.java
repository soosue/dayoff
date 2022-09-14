package com.project.justdo.service;

import com.project.justdo.domain.DayOff;
import com.project.justdo.domain.Member;
import com.project.justdo.domain.repository.DayOffApprovalRepository;
import com.project.justdo.domain.repository.DayOffRepository;
import com.project.justdo.domain.repository.MemberRepository;
import com.project.justdo.service.dto.DayOffApplicationDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class DayOffConfirmServiceTest {
    @Autowired
    private DayOffApplicationService dayOffApplicationService;

    @Autowired
    private DayOffApprovalService dayOffApprovalService;

    @Autowired
    private DayOffApprovalRepository dayOffApprovalRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private DayOffRepository dayOffRepository;

    @BeforeEach
    void init() {
        Member 베니스 = new Member("베니스");
        memberRepository.save(베니스);
        memberRepository.save(new Member("아메리"));
        memberRepository.save(new Member("케이크"));
        memberRepository.save(new Member("아이스"));

        dayOffRepository.save(new DayOff(2022, 15, 베니스));
    }

    @DisplayName("모두 승인된 연차신청서는 최종결재요청을 할 수 있다.")
    @Test
    void 최종결재요청_모두_승인된_연차신청서() {
        DayOffApplicationDto dto = new DayOffApplicationDto(
                memberRepository.findByName("베니스").getId(), "2022-09-05", "2022-09-09",
                List.of(memberRepository.findByName("아메리").getId(), memberRepository.findByName("케이크").getId()),
                "010-1234-5678",
                memberRepository.findByName("아이스").getId(),
                "개인 사유로 연차를 신청합니다."
        );

        Long dayOffApplicationId = dayOffApplicationService.registerDayOffApplication(dto);
        Member member = memberRepository.findById(2L).get();
        dayOffApprovalService.save(dayOffApplicationId, member, Boolean.TRUE);

        Member member2 = memberRepository.findById(3L).get();
        dayOffApprovalService.save(dayOffApplicationId, member2, Boolean.TRUE);




//        assertThat(dayOffApproval.isApproved()).isEqualTo(state);
    }

    @DisplayName("하나라도 거절된 연차신청서는 최종결재요청을 할 수 없다.")
    @Test
    void 최종결재요청_거절된_연차신청서() {
        DayOffApplicationDto dto = new DayOffApplicationDto(
                memberRepository.findByName("베니스").getId(), "2022-09-05", "2022-09-09",
                List.of(memberRepository.findByName("아메리").getId(), memberRepository.findByName("케이크").getId()),
                "010-1234-5678",
                memberRepository.findByName("아이스").getId(),
                "개인 사유로 연차를 신청합니다."
        );

        Long registeredId = dayOffApplicationService.registerDayOffApplication(dto);

        assertThat(dayOffRepository.findById(registeredId)).isPresent();
    }
}
