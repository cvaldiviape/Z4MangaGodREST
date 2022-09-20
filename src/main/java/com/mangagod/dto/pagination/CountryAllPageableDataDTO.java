package com.mangagod.dto.pagination;

import java.util.List;
import com.mangagod.dto.pagination.base.PageableDataDTOImpl;
import com.mangagod.dto.response.CountryResponseDTO;
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
public class CountryAllPageableDataDTO extends PageableDataDTOImpl {

	private List<CountryResponseDTO> countries;
	
}