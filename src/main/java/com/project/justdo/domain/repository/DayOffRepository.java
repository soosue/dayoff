package com.project.justdo.domain.repository;

import com.project.justdo.domain.DayOff;
import com.project.justdo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DayOffRepository extends JpaRepository<DayOff, Long> {
    Optional<DayOff> findByOwner(Member owner);
}
