package com.project.justdo.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DayOff {
    private static final Logger logger = LoggerFactory.getLogger(DayOff.class);

    private int year;
    private int dayOffTotal;
    private int remain;
    private Member owner;

    public DayOff(int year, int dayOffTotal, Member owner) {
        this.year = year;
        this.dayOffTotal = dayOffTotal;
        this.remain = dayOffTotal;
        this.owner = owner;
    }

    public void validateRemainCount(Integer dayOffCount) {
        if (remain < dayOffCount) {
            throw new IllegalStateException("휴가가 " + remain + "일 남았습니다.");
        }
    }
}
