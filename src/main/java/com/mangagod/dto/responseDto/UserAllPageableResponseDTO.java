package com.mangagod.dto.responseDto;

import java.util.List;

public class UserAllPageableResponseDTO {

	private Integer numberPage;
	private Integer sizePage;
	private Integer totalPages;
	private Boolean isLastPage;
	private Long totalElements;
	private List<UserResponseDTO> users;
	
	public UserAllPageableResponseDTO() {
		
	}

	public Integer getNumberPage() {
		return numberPage;
	}

	public void setNumberPage(Integer numberPage) {
		this.numberPage = numberPage;
	}

	public Integer getSizePage() {
		return sizePage;
	}

	public void setSizePage(Integer sizePage) {
		this.sizePage = sizePage;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Boolean getIsLastPage() {
		return isLastPage;
	}

	public void setIsLastPage(Boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public List<UserResponseDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserResponseDTO> users) {
		this.users = users;
	}
	
}