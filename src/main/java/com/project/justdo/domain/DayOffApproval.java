package com.project.justdo.domain;

import javax.persistence.*;

@Entity
public class DayOffApproval {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private DayOffApplication dayOffApplication;
    @OneToOne
    private Member approver;
    private Boolean isApproved;

    public DayOffApproval() {
    }

    public DayOffApproval(DayOffApplication dayOffApplication, Member approver, Boolean isApproved) {
        this.dayOffApplication = dayOffApplication;
        this.approver = approver;
        this.isApproved = isApproved;
    }

    public DayOffApproval(DayOffApplication dayOffApplication, Member approver) {
        this.dayOffApplication = dayOffApplication;
        this.approver = approver;
    }

    public void approve() {
        isApproved = Boolean.TRUE;
    }

    public void reject() {
        isApproved = Boolean.FALSE;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public Long getId() {
        return id;
    }
}
