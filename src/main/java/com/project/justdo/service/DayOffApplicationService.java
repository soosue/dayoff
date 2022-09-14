package com.project.justdo.service;

import com.project.justdo.domain.DayOffApplication;
import com.project.justdo.domain.repository.DayOffApplicationRepository;
import com.project.justdo.service.dto.DayOffApplicationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DayOffApplicationService {
    private static final Logger logger = LoggerFactory.getLogger(DayOffApplicationService.class);

    private final DayOffApplicationMapper dayOffApplicationMapper;
    private final DayOffApplicationRepository dayOffApplicationRepository;

    public DayOffApplicationService(DayOffApplicationMapper dayOffApplicationMapper, DayOffApplicationRepository dayOffApplicationRepository) {
        this.dayOffApplicationMapper = dayOffApplicationMapper;
        this.dayOffApplicationRepository = dayOffApplicationRepository;
    }

    public Long registerDayOffApplication(DayOffApplicationDto dto) {
        DayOffApplication dayOffApplication = dayOffApplicationMapper.mapFrom(dto);
        dayOffApplication.validate();

        dayOffApplicationRepository.save(dayOffApplication);

        return dayOffApplication.getId();
    }

    public DayOffApplication findById(Long id) {
        DayOffApplication doa = dayOffApplicationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + "의 연차신청서를 찾을 수 없습니다."));

        //TODO 연차신청서를 볼 수 있는 사람은 누구누구가 되어야하는가?
        // 볼 권한이 없는 경우 예외발생
        doa.getDayOffApprovals();

        return doa;
//        return DayOffApplicationDto.from(doa);
    }
}
