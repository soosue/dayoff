package com.project.justdo.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DayOffApplication {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Member member;
    @ManyToOne
    private DayOff dayOff;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer dayOffCount;

    @Transient
    private Approvers approvers;
    @Embedded
    private DayOffApprovals dayOffApprovals;
    @Embedded
    private PhoneNumber phoneNumber;
    @OneToOne
    private Member subWorker;
    private String reason;

    public DayOffApplication(Member member, DayOff dayOff, String from, String to, List<Member> approvers, String emergencyCall, Member subWorker, String reason) {
        this(member, dayOff, from, to, approvers, List.of(), emergencyCall, subWorker, reason);
    }

    public DayOffApplication(Member member, DayOff dayOff, String from, String to, List<Member> approvers, List<DayOffApproval> dayOffApprovals, String emergencyCall, Member subWorker, String reason) {
        this.member = member;
        this.dayOff = dayOff;
        this.fromDate = LocalDate.parse(from);
        this.toDate = LocalDate.parse(to);
        this.dayOffCount = Integer.parseInt(String.valueOf(this.toDate.getDayOfMonth() - this.fromDate.getDayOfMonth())) + 1;
        this.approvers = new Approvers(approvers);
        this.dayOffApprovals = new DayOffApprovals(dayOffApprovals);
//        this.dayOffApprovals = dayOffApprovals;
        this.phoneNumber = new PhoneNumber(emergencyCall);
        this.subWorker = subWorker;
        this.reason = reason;
    }

    public DayOffApplication() {
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

    public Long getId() {
        return id;
    }

    public DayOffApprovals getDayOffApprovals() {
        return dayOffApprovals;
    }

    public void addDayOffApproval(DayOffApproval dayOffApproval) {
        dayOffApprovals.add(dayOffApproval);
    }
}
