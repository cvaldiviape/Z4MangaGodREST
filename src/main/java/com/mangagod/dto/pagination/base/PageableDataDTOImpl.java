package com.mangagod.dto.pagination.base;

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
public class PageableDataDTOImpl implements PageableDataDTO {
	
	private Integer numberPage;
	private Integer sizePage;
	private Integer totalPages;
	private Boolean isLastPage;
	private Long totalElements;
	
}