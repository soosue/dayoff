package com.project.justdo.domain;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class DayOffApprovals {
    @OneToMany(mappedBy = "dayOffApplication")
    private List<DayOffApproval> dayOffApprovals = new ArrayList<>();

    public DayOffApprovals() {

    }

    public DayOffApprovals(List<DayOffApproval> dayOffApprovals) {
        this.dayOffApprovals = dayOffApprovals;
    }

    public boolean isAllApproval() {
        return dayOffApprovals.stream()
                .allMatch(DayOffApproval::isApproval);
    }
}
