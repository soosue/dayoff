package com.project.justdo.service;

import com.project.justdo.domain.DayOff;
import com.project.justdo.domain.DayOffApplication;
import com.project.justdo.domain.DayOffApproval;
import com.project.justdo.domain.Member;
import com.project.justdo.domain.repository.DayOffRepository;
import com.project.justdo.domain.repository.MemberRepository;
import com.project.justdo.service.dto.DayOffApplicationDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DayOffApplicationMapper {
    private final MemberRepository memberRepository;
    private final DayOffRepository dayOffRepository;

    public DayOffApplicationMapper(MemberRepository memberRepository, DayOffRepository dayOffRepository) {
        this.memberRepository = memberRepository;
        this.dayOffRepository = dayOffRepository;
    }

    public DayOffApplication mapFrom(DayOffApplicationDto dto) {
        Member owner = memberRepository.findById(dto.getMemberId()).orElseThrow(() -> new IllegalArgumentException());
        DayOff dayOff = dayOffRepository.findByOwner(owner).orElseThrow(() -> new IllegalArgumentException());
        List<Member> approvers = memberRepository.findAllById(dto.getApprovals());
        Member coworker = memberRepository.findById(dto.getCoworkerId()).orElseThrow(() -> new IllegalArgumentException());

        DayOffApplication dayOffApplication = new DayOffApplication(owner, dayOff,
                "2022-09-05", "2022-09-09",
                approvers,
                "010-1234-5678",
                coworker,
                "개인 사유로 연차를 신청합니다.");
        List<DayOffApproval> dayOffApprovals = approvers.stream()
                .map(approver -> new DayOffApproval(dayOffApplication, approver, Boolean.TRUE))
                .collect(Collectors.toList());

        dayOffApplication.setDayOffApprovals(dayOffApprovals);

        return dayOffApplication;
    }
}
