package com.project.justdo.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DayOffApprovals {
    private static final Logger logger = LoggerFactory.getLogger(DayOffApprovals.class);

    private List<DayOffApproval> dayOffApprovals;

    public DayOffApprovals(List<DayOffApproval> dayOffApprovals) {
        this.dayOffApprovals = dayOffApprovals;
    }

    public boolean isAllApproval() {
        return dayOffApprovals.stream()
                .allMatch(DayOffApproval::isApproval);
    }
}
