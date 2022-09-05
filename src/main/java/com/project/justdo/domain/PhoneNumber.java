package com.project.justdo.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhoneNumber {
    private static final Logger logger = LoggerFactory.getLogger(PhoneNumber.class);

    private String value;

    public PhoneNumber(String phoneNumber) {
        validate(phoneNumber);
        this.value = phoneNumber;
    }

    private void validate(String phoneNumber) {

    }
}
