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
    private Boolean isApproval;

    public DayOffApproval() {

    }

    public DayOffApproval(DayOffApplication dayOffApplication, Member approver) {
        this.dayOffApplication = dayOffApplication;
        this.approver = approver;
    }

    public void approve() {
        isApproval = Boolean.TRUE;
    }

    public void reject() {
        isApproval = Boolean.FALSE;
    }

    public boolean isApproval() {
        return isApproval;
    }
}
