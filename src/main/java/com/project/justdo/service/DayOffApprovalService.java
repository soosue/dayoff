package com.project.justdo.service;

import com.project.justdo.domain.DayOffApplication;
import com.project.justdo.domain.DayOffApproval;
import com.project.justdo.domain.Member;
import com.project.justdo.domain.repository.DayOffApplicationRepository;
import com.project.justdo.domain.repository.DayOffApprovalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DayOffApprovalService {
    private static final Logger logger = LoggerFactory.getLogger(DayOffApprovalService.class);

    private final DayOffApplicationRepository dayOffApplicationRepository;
    private final DayOffApprovalRepository dayOffApprovalRepository;

    public DayOffApprovalService(DayOffApplicationRepository dayOffApplicationRepository, DayOffApprovalRepository dayOffApprovalRepository) {
        this.dayOffApplicationRepository = dayOffApplicationRepository;
        this.dayOffApprovalRepository = dayOffApprovalRepository;
    }

    public Long save(Long dayOffApplicationId, Member member, Boolean state) {
        DayOffApplication dayOffApplication = dayOffApplicationRepository.findById(dayOffApplicationId).get();
        //TODO 현재 요청한 사람이 연차신청서의 승인자 중 한 명이어야 한다.
        // 맞다면, 진행. 아니면 예외처리.

        DayOffApproval dayOffApproval = new DayOffApproval(dayOffApplication, member, state);
        dayOffApplication.addDayOffApproval(dayOffApproval);


        return dayOffApprovalRepository.save(dayOffApproval).getId();
    }
}
