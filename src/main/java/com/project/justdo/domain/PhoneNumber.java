package com.project.justdo.domain;

import javax.persistence.Embeddable;

@Embeddable
public class PhoneNumber {
    private String phoneNumber;

    public PhoneNumber() {

    }

    public PhoneNumber(String phoneNumber) {
        validate(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    private void validate(String phoneNumber) {

    }
}
