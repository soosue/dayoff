package com.project.justdo.service;

import com.project.justdo.service.dto.DayOffApplicationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DayOffApplicationServiceTest {
    @Autowired
    private DayOffApplicationService service;

    @Test
    void 연차신청서_작성() {
        DayOffApplicationDto dto = new DayOffApplicationDto();

        service.registerDayOffApplication();
    }
}
