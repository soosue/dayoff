package com.project.justdo.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.project.justdo.domain.MemberTexture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DayOffConfirmTest {
    @DisplayName("모두 승인되었으면 최종결재요청이 가능하다.")
    @Test
    void 최종결재요청_모두승인됨_승인() {
        DayOff dayOff = new DayOff(2022, 15, 김윤수);

        DayOffApplication dayOffApplication = new DayOffApplication(김윤수, dayOff,
                "2022-09-05", "2022-09-09",
                List.of(정수원, 이응석),
                "010-3841-9809",
                이민우,
                "개인 사유로 연차를 신청합니다.");

        DayOffApproval dayOffApproval = new DayOffApproval(dayOffApplication, 정수원);
        dayOffApproval.approve();

        DayOffApproval dayOffApproval2 = new DayOffApproval(dayOffApplication, 이응석);
        dayOffApproval2.approve();

        dayOffApplication.setDayOffApprovals(List.of(dayOffApproval, dayOffApproval2));

        DayOffConfirm dayOffConfirm = new DayOffConfirm(dayOffApplication, 홍창민);
        dayOffConfirm.confirm();

        assertThat(dayOffConfirm.isConfirmed()).isTrue();
    }

    @DisplayName("모두 승인되었으면 최종결재요청이 가능하다.")
    @Test
    void 최종결재요청_모두승인됨_거절() {
        DayOff dayOff = new DayOff(2022, 15, 김윤수);

        DayOffApplication dayOffApplication = new DayOffApplication(김윤수, dayOff,
                "2022-09-05", "2022-09-09",
                List.of(정수원, 이응석),
                "010-3841-9809",
                이민우,
                "개인 사유로 연차를 신청합니다.");

        DayOffApproval dayOffApproval = new DayOffApproval(dayOffApplication, 정수원);
        dayOffApproval.approve();

        DayOffApproval dayOffApproval2 = new DayOffApproval(dayOffApplication, 이응석);
        dayOffApproval2.approve();

        dayOffApplication.setDayOffApprovals(List.of(dayOffApproval, dayOffApproval2));

        DayOffConfirm dayOffConfirm = new DayOffConfirm(dayOffApplication, 홍창민);
        dayOffConfirm.reject();

        assertThat(dayOffConfirm.isConfirmed()).isFalse();
    }

    @DisplayName("하나라도 승인되지 않았으면 최종결재요청이 불가능하다.")
    @Test
    void 최종결재요청_모두_승인되지는않음() {
        DayOff dayOff = new DayOff(2022, 15, 김윤수);

        DayOffApplication dayOffApplication = new DayOffApplication(김윤수, dayOff,
                "2022-09-05", "2022-09-09",
                List.of(정수원, 이응석),
                "010-3841-9809",
                이민우,
                "개인 사유로 연차를 신청합니다.");

        DayOffApproval dayOffApproval = new DayOffApproval(dayOffApplication, 정수원);
        dayOffApproval.approve();

        DayOffApproval dayOffApproval2 = new DayOffApproval(dayOffApplication, 이응석);
        dayOffApproval2.reject();

        dayOffApplication.setDayOffApprovals(List.of(dayOffApproval, dayOffApproval2));

        assertThatThrownBy(() ->
                new DayOffConfirm(dayOffApplication, 홍창민)
        ).isInstanceOf(IllegalStateException.class);
    }
}
