package com.project.justdo.service.dto;

import com.project.justdo.domain.DayOffApplication;

import java.util.List;

public class DayOffApplicationDto {
    public DayOffApplicationDto() {

    }

    public DayOffApplicationDto(Long member, String from, String to, List<Long> approvers, String phoneNumber, Long subWorker, String reason) {

    }

    public static DayOffApplicationDto from(DayOffApplication doa) {
        return new DayOffApplicationDto();
    }
}
