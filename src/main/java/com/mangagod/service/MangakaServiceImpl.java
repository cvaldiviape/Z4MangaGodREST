package com.mangagod.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mangagod.dto.data.MangakaDataDTO;
import com.mangagod.dto.pagination.MangakaAllPageableDataDTO;
import com.mangagod.dto.request.MangakaRequestDTO;
import com.mangagod.entity.MangakaEntity;
import com.mangagod.exception.ResourceNotFoundException;
import com.mangagod.mapper.MangakaMapper;
import com.mangagod.repository.MangakaRepository;

@Service
@Transactional
public class MangakaServiceImpl implements MangakaService {

	// ----------------------------------------------------- dependency injection  ----------------------------------------------------- //
	@Autowired
	private MangakaRepository mangakaRepository;
//	@Autowired
//	private AuthorRepository authorRepository;
//	@Autowired
//	private ArtistRepository artistRepository;
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
		return null;
	}

	@Override
	public MangakaDataDTO update(Integer id, MangakaRequestDTO requestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MangakaDataDTO delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}