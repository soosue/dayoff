package com.project.justdo.service;

import com.project.justdo.domain.DayOff;
import com.project.justdo.domain.DayOffApproval;
import com.project.justdo.domain.Member;
import com.project.justdo.domain.repository.DayOffApprovalRepository;
import com.project.justdo.domain.repository.DayOffRepository;
import com.project.justdo.domain.repository.MemberRepository;
import com.project.justdo.service.dto.DayOffApplicationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DayOffApprovalServiceTest {
    @Autowired
    private DayOffApplicationService doaService;

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

    @DisplayName("연차신청서는 승인하거나 거절 할 수 있다.")
    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void 연차신청서_승인과_거절(boolean state) {
        DayOffApplicationDto dto = new DayOffApplicationDto(
                1L, "2022-09-05", "2022-09-09",
                List.of(2L, 3L), "010-1234-5678", 4L, "개인 사유로 연차를 신청합니다."
        );

        Long dayOffApplicationId = doaService.registerDayOffApplication(dto);
        Member member = memberRepository.findById(2L).get();


        Long dayOffApprovalId = dayOffApprovalService.save(dayOffApplicationId, member, state);

        DayOffApproval dayOffApproval = dayOffApprovalRepository.findById(dayOffApprovalId).get();

        assertThat(dayOffApproval.isApproved()).isEqualTo(state);
    }

}
