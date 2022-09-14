package com.project.justdo.domain;

import javax.persistence.*;

@Entity
public class DayOff {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int year;
    private int dayOffTotal;
    private int remain;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member owner;

    public DayOff() {
    }

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

    public boolean isOwner(Member owner) {
        return this.owner.equals(owner);
    }
}
