package com.mangagod.dto.pagination;

import java.util.List;
import com.mangagod.dto.pagination.base.PageableDataDTOImpl;
import com.mangagod.dto.response.JobResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class JobAllPageableDataDTO extends PageableDataDTOImpl {

	private List<JobResponseDTO> jobs;
	
}