package com.mangagod.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mangagod.dto.data.JobDataDTO;
import com.mangagod.dto.request.JobRequestDTO;
import com.mangagod.entity.JobEntity;

@Component
public class JobMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	// ---------------------------------------------------------- modelMapper --------------------------------------------------------- //
	public JobDataDTO mapEntityToDataDTO(JobEntity entity) {
		JobDataDTO dataDTO = this.modelMapper.map(entity, JobDataDTO.class);
		return dataDTO;
	}
	
	public JobEntity mapRequestToEntity(JobRequestDTO requestDTO) {
		JobEntity entity = this.modelMapper.map(requestDTO, JobEntity.class);
		return entity;
	}
	
}