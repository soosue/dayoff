package com.project.justdo.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Approvers {
    private static final Logger logger = LoggerFactory.getLogger(Approvers.class);

    private List<Member> approvers;

    public Approvers(List<Member> approvers) {
        this.approvers = approvers;
    }
}
