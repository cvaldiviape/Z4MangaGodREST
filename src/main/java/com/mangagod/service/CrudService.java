package com.mangagod.service;

public interface CrudService <T, K, U, V> {

	public T getAll(Integer numberPage, Integer sizePage, String sortBy, String sortDir);
	
	public K getById(Integer id);
	
	public K create(U createRequestDTO);
	
	public K update(Integer id, V updateRequestDTO);
	
	public K delete(Integer id);

}