package com.project.justdo.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.project.justdo.domain.MemberTexture.*;
import static org.assertj.core.api.Assertions.assertThat;

public class DayOffApprovalTest {
    @DisplayName("승인담당자는 연차신청서를 승인할 수 있다.")
    @Test
    void 연차승인() {
        DayOff dayOff = new DayOff(2022, 15, 베니스);

        DayOffApplication dayOffApplication = new DayOffApplication(베니스, dayOff,
                "2022-09-05", "2022-09-09",
                List.of(아메리, 케이크),
                "010-1234-5678",
                아이스,
                "개인 사유로 연차를 신청합니다.");

        DayOffApproval dayOffApproval = new DayOffApproval(dayOffApplication, 아메리);
        dayOffApproval.approve();

        assertThat(dayOffApproval.isApproval()).isTrue();
    }

    @DisplayName("승인담당자는 연차신청서를 거절할 수 있다.")
    @Test
    void 연차승인거절() {
        DayOff dayOff = new DayOff(2022, 15, 베니스);

        DayOffApplication dayOffApplication = new DayOffApplication(베니스, dayOff,
                "2022-09-05", "2022-09-09",
                List.of(아메리, 케이크),
                "010-1234-5678",
                아이스,
                "개인 사유로 연차를 신청합니다.");

        DayOffApproval dayOffApproval = new DayOffApproval(dayOffApplication, 아메리);
        dayOffApproval.reject();

        assertThat(dayOffApproval.isApproval()).isFalse();
    }

}
