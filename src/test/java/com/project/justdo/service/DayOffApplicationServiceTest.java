package com.project.justdo.service;

import com.project.justdo.domain.DayOff;
import com.project.justdo.domain.Member;
import com.project.justdo.domain.repository.DayOffApplicationRepository;
import com.project.justdo.domain.repository.DayOffRepository;
import com.project.justdo.domain.repository.MemberRepository;
import com.project.justdo.service.dto.DayOffApplicationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DayOffApplicationServiceTest {
    @Autowired
    private DayOffApplicationService service;

    @Autowired
    private DayOffApplicationRepository repository;

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


    @Test
    void 연차신청서_작성() {
        DayOffApplicationDto dto = new DayOffApplicationDto(
                1L, "2022-09-05", "2022-09-09",
                List.of(2L, 3L), "010-1234-5678", 4L, "개인 사유로 연차를 신청합니다."
        );

        Long registeredId = service.registerDayOffApplication(dto);

        assertThat(repository.findById(registeredId)).isPresent();
    }
}
