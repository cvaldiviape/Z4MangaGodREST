package com.mangagod.dto.pagination;

import java.util.List;
import com.mangagod.dto.pagination.base.PageableDataDTOImpl;
import com.mangagod.dto.response.StoryResponseDTO;
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
public class StoryAllPageableDataDTO extends PageableDataDTOImpl{

	private List<StoryResponseDTO> stories;

}