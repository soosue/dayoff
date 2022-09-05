package com.project.justdo.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class DayOffApplication {
    private static final Logger logger = LoggerFactory.getLogger(DayOffApplication.class);

    private Long id;
    private Member member;
    private DayOff dayOff;
    private LocalDate from;
    private LocalDate to;
    private Integer dayOffCount;
    private Approvers approvers;
    private DayOffApprovals dayOffApprovals;
    private PhoneNumber phoneNumber;
    private Member subWorker;
    private String reason;

    public DayOffApplication(Member member, DayOff dayOff, String from, String to, List<Member> approvers, String emergencyCall, Member subWorker, String reason) {
        this(member, dayOff, from, to, approvers, List.of(), emergencyCall, subWorker, reason);
    }

    public DayOffApplication(Member member, DayOff dayOff, String from, String to, List<Member> approvers, List<DayOffApproval> dayOffApprovals, String emergencyCall, Member subWorker, String reason) {
        this.member = member;
        this.dayOff = dayOff;
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
        this.dayOffCount = Integer.parseInt(String.valueOf(this.to.getDayOfMonth() - this.from.getDayOfMonth())) + 1;
        this.approvers = new Approvers(approvers);
        this.dayOffApprovals = new DayOffApprovals(dayOffApprovals);
        this.phoneNumber = new PhoneNumber(emergencyCall);
        this.subWorker = subWorker;
        this.reason = reason;
    }

    public void validate() {
        dayOff.validateRemainCount(dayOffCount);
    }

    public void setDayOffApprovals(List<DayOffApproval> dayOffApproval) {
        this.dayOffApprovals = new DayOffApprovals(dayOffApproval);
    }

    public void validateBeforeConfirm() {
        if (dayOffApprovals.isAllApproval()) {
            return;
        }
        throw new IllegalStateException("최종결재요청을 위해선 모두 승인되어야합니다.");
    }
}
