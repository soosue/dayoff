package com.project.justdo.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DayOffConfirm {
    private static final Logger logger = LoggerFactory.getLogger(DayOffConfirm.class);

    private DayOffApplication dayOffApplication;
    private Member confirmer;
    private Boolean isConfirmed;

    public DayOffConfirm(DayOffApplication dayOffApplication, Member confirmer) {
        validate(dayOffApplication, confirmer);
        this.dayOffApplication = dayOffApplication;
        this.confirmer = confirmer;
    }

    private void validate(DayOffApplication dayOffApplication, Member confirmer) {
        dayOffApplication.validateBeforeConfirm();
    }

    public void confirm() {
        isConfirmed = Boolean.TRUE;
    }

    public void reject() {
        isConfirmed = Boolean.FALSE;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

}
