package com.bernardtm.octoEvents.domain.models.dtos;

public class IssueEventDto {
	
	private Long id;
	
	private String action;
	
	private IssueDto issue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public IssueDto getIssue() {
		return issue;
	}

	public void setIssue(IssueDto issueDto) {
		this.issue = issueDto;
	}
 
}
