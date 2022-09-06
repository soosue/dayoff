package com.project.justdo.service;

import com.project.justdo.domain.DayOff;
import com.project.justdo.domain.DayOffApplication;
import com.project.justdo.domain.Member;
import com.project.justdo.domain.repository.DayOffRepository;
import com.project.justdo.domain.repository.MemberRepository;
import com.project.justdo.service.dto.DayOffApplicationDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DayOffApplicationMapper {
    private final MemberRepository memberRepository;
    private final DayOffRepository dayOffRepository;

    public DayOffApplicationMapper(MemberRepository memberRepository, DayOffRepository dayOffRepository) {
        this.memberRepository = memberRepository;
        this.dayOffRepository = dayOffRepository;
    }

    public DayOffApplication mapFrom(DayOffApplicationDto dto) {
        Member owner = memberRepository.findById(1L).orElseThrow(() -> new IllegalArgumentException());
        DayOff dayOff = dayOffRepository.findById(5L).orElseThrow(() -> new IllegalArgumentException());
        List<Member> approvers = memberRepository.findAllById(List.of(2L, 3L));
        Member coworker = memberRepository.findById(4L).orElseThrow(() -> new IllegalArgumentException());

        return new DayOffApplication(owner, dayOff,
                "2022-09-05", "2022-09-09",
                approvers,
                "010-1234-5678",
                coworker,
                "개인 사유로 연차를 신청합니다.");
    }
}
