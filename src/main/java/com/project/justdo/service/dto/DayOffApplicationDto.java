package com.project.justdo.service.dto;

import com.project.justdo.domain.DayOffApplication;

import java.util.List;

public class DayOffApplicationDto {
    private Long memberId;
    private List<Long> approvers;
    private Long coworkerId;

    public DayOffApplicationDto() {

    }

    public DayOffApplicationDto(Long memberId, String from, String to, List<Long> approvers, String phoneNumber, Long subWorker, String reason) {
        this.memberId = memberId;
        this.approvers = approvers;
        this.coworkerId = subWorker;
    }

    public static DayOffApplicationDto from(DayOffApplication doa) {
        return new DayOffApplicationDto();
    }

    public Long getMemberId() {
        return memberId;
    }

    public Iterable<Long> getApprovals() {
        return approvers;
    }

    public Long getCoworkerId() {
        return coworkerId;
    }
}
