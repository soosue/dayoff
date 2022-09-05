package com.project.justdo.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Member {
    private static final Logger logger = LoggerFactory.getLogger(Member.class);

    private Long id;
    private String name;

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
