package com.bernardtm.octoEvents.domain.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.bernardtm.octoEvents.domain.models.IssueEvent;
import com.bernardtm.octoEvents.domain.models.dtos.IssueDto;
import com.bernardtm.octoEvents.domain.models.dtos.IssueEventDto;

@Component
public class IssueEventsConverter {

	public IssueEvent toModel(IssueEventDto dto) {
		Optional<IssueEvent> model = Optional.empty();
		
		if (dto != null) {
			model = Optional.of(new IssueEvent());
			
			model.get().setId(dto.getId());
			model.get().setAction(dto.getAction());
			
			if (dto.getIssue() != null) {
				if (dto.getIssue().getNumber() != null) {
					model.get().setNumber(Long.parseLong(dto.getIssue().getNumber()));
				}
				model.get().setCreated_at(dto.getIssue().getCreated_at());
				model.get().setTitle(dto.getIssue().getTitle());
			}
			
		}
		return model.get();
	}

	public IssueEventDto toDto(IssueEvent model) {
		IssueEventDto dto = null;
		
		if (model != null) {
			dto = new IssueEventDto();
			
			dto.setId(model.getId());
			dto.setAction(model.getAction());
			
			IssueDto issue = new IssueDto();
			if (model.getNumber() != null) {
				issue.setNumber(String.valueOf(model.getNumber()));
			}
			issue.setCreated_at(model.getCreated_at());
			issue.setTitle(model.getTitle());
			dto.setIssue(issue);
			
		}
		return dto;
	}

	public List<IssueEventDto> optionalListModeltoDto(List<Optional<IssueEvent>> listOptionalModel) {
		List<IssueEventDto> listDto = new ArrayList<IssueEventDto>();

		if (!listOptionalModel.isEmpty()) {
			for (Optional<IssueEvent> optionalModel : listOptionalModel) {
				if (optionalModel.isPresent()) {
					listDto.add(toDto(optionalModel.get()));
				}
			}
		}
		return listDto;
	}

}
