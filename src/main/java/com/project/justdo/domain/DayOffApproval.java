package com.project.justdo.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DayOffApproval {
    private static final Logger logger = LoggerFactory.getLogger(DayOffApproval.class);

    private Long id;
    private DayOffApplication dayOffApplication;
    private Member approver;
    private Boolean isApproval;

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
