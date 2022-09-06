package com.mangagod.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mangagod.dto.data.MangakaDataDTO;
import com.mangagod.dto.pagination.MangakaAllPageableDataDTO;
import com.mangagod.dto.request.MangakaRequestDTO;
import com.mangagod.entity.MangakaEntity;
import com.mangagod.exception.MangaGodAppException;
import com.mangagod.exception.ResourceNotFoundException;
import com.mangagod.mapper.MangakaMapper;
import com.mangagod.repository.MangakaRepository;

@Service
@Transactional
public class MangakaServiceImpl implements MangakaService {

	// ----------------------------------------------------- dependency injection  ----------------------------------------------------- //
	@Autowired
	private MangakaRepository mangakaRepository;
	@Autowired
	private MangakaMapper mangakaMapper;
	
	// ----------------------------------------------------------- services ----------------------------------------------------------- //
	@Override
	public MangakaAllPageableDataDTO getAll(Integer numberPage, Integer sizePage, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortBy).ascending() 
				: Sort.by(sortBy).descending();
	
		// agregando paginación
		Pageable pageable = PageRequest.of(numberPage, sizePage, sort);
		Page<MangakaEntity> mangakasPageable = this.mangakaRepository.findAll(pageable);	
		List<MangakaEntity> mangakasEntity = mangakasPageable.getContent();
		List<MangakaDataDTO> mangakassDTO = mangakasEntity.stream().map((x) -> this.mangakaMapper.mapEntityToDataDTO(x)).collect(Collectors.toList());	
		
		MangakaAllPageableDataDTO pageableDataDTO = new MangakaAllPageableDataDTO();
		pageableDataDTO.setMangakas(mangakassDTO);
		pageableDataDTO.setNumberPage(mangakasPageable.getNumber());
		pageableDataDTO.setSizePage(mangakasPageable.getSize());
		pageableDataDTO.setTotalElements(mangakasPageable.getTotalElements());
		pageableDataDTO.setTotalPages(mangakasPageable.getTotalPages());
		pageableDataDTO.setIsLastPage(mangakasPageable.isLast());
		
		return pageableDataDTO;
	}

	@Override
	public MangakaDataDTO getById(Integer id) {
		// TODO Auto-generated method stub
		MangakaEntity entity = this.mangakaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Mangaka", "id", id));
		MangakaDataDTO dataDTO = this.mangakaMapper.mapEntityToDataDTO(entity);
		return dataDTO;
	}

	@Override
	public MangakaDataDTO create(MangakaRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		Boolean existNname = this.mangakaRepository.existsByName(requestDTO.getName());
		if(existNname) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + requestDTO.getName() + " ya existe.");
		}
		MangakaEntity entity = this.mangakaMapper.mapRequestToEntity(requestDTO);
		
		MangakaDataDTO dataCreated = this.mangakaMapper.mapEntityToDataDTO(this.mangakaRepository.save(entity));			
		return dataCreated;
	}

	@Override
	public MangakaDataDTO update(Integer id, MangakaRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		MangakaEntity dataCurrent = this.mangakaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Mangaka", "id", id));
		Boolean existsNname = this.mangakaRepository.existsByName(requestDTO.getName());
		Boolean diferentNameCurrent = (!requestDTO.getName().equalsIgnoreCase(dataCurrent.getName()));
		if(existsNname && diferentNameCurrent) {
			throw new MangaGodAppException(HttpStatus.BAD_REQUEST, "El nombre " + requestDTO.getName() + " ya existe.");
		}
		dataCurrent.setName(requestDTO.getName());
		
		MangakaDataDTO dataUpdated = this.mangakaMapper.mapEntityToDataDTO(this.mangakaRepository.save(dataCurrent));	
		return dataUpdated;
	}

	@Override
	public MangakaDataDTO delete(Integer id) {
		// TODO Auto-generated method stub
		MangakaEntity entity = this.mangakaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Mangaka", "id", id));
		this.mangakaRepository.delete(entity);
		MangakaDataDTO dataDeleted = this.mangakaMapper.mapEntityToDataDTO(entity);
		return dataDeleted;
	}

}