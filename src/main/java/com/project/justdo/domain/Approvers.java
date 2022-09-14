package com.project.justdo.domain;

import java.util.ArrayList;
import java.util.List;

public class Approvers {
    private List<Member> approvers = new ArrayList<>();

    public Approvers() {

    }

    public Approvers(List<Member> approvers) {
        this.approvers = approvers;
    }

    public List<Member> getList() {
        return approvers;
    }
}
