package com.project.justdo.service;

import com.project.justdo.domain.DayOffApplication;
import com.project.justdo.domain.repository.DayOffApplicationRepository;
import com.project.justdo.service.dto.DayOffApplicationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
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
}
