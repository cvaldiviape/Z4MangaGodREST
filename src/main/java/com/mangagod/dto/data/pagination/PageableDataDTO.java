package com.mangagod.dto.data.pagination;

public interface PageableDataDTO {

	public Integer getNumberPage();

	public void setNumberPage(Integer numberPage);

	public Integer getSizePage();

	public void setSizePage(Integer sizePage);

	public Integer getTotalPages();

	public void setTotalPages(Integer totalPages);

	public Boolean getIsLastPage();

	public void setIsLastPage(Boolean isLastPage);

	public Long getTotalElements();

	public void setTotalElements(Long totalElements);

}