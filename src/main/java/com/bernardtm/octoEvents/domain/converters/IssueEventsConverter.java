package com.bernardtm.octoEvents.domain.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.bernardtm.octoEvents.domain.models.IssueEvent;
import com.bernardtm.octoEvents.domain.models.dtos.IssueEventDto;

@Component
public class IssueEventsConverter {

	public IssueEvent toModel(IssueEventDto dto) {
		Optional<IssueEvent> model = Optional.empty();
		
		if (dto != null) {
			model = Optional.of(new IssueEvent());
			
			model.get().setAction(dto.getAction());
			model.get().setNumber(Long.valueOf(dto.getNumber()));
			
		}
		return model.get();
	}

	public IssueEventDto toDto(IssueEvent model) {
		IssueEventDto dto = null;
		
		if (model != null) {
			dto = new IssueEventDto();
			
			dto.setAction(model.getAction());
			dto.setNumber(String.valueOf(model.getNumber()));
			
		}
		return dto;
	}

	public List<IssueEventDto> optionalListModeltoDto(List<Optional<IssueEvent>> listOptionalModel) {
		List<IssueEventDto> listDto = null;

		if (!listOptionalModel.isEmpty()) {
			listDto = new ArrayList<IssueEventDto>();
			for (Optional<IssueEvent> optionalModel : listOptionalModel) {
				if (optionalModel.isPresent()) {
					listDto.add(toDto(optionalModel.get()));
				}
			}
		}
		return listDto;
	}

}
