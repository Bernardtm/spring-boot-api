package com.bernardtm.octoEvents.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bernardtm.octoEvents.domain.models.IssueEvent;

public interface IssueEventsRepository extends JpaRepository<IssueEvent, Long> {
	
	public List<Optional<IssueEvent>> findByNumber(Long number);
}
