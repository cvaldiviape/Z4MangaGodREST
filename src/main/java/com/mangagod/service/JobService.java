package com.mangagod.service;

import com.mangagod.dto.pagination.JobAllPageableDataDTO;
import com.mangagod.dto.request.JobRequestDTO;
import com.mangagod.dto.response.JobResponseDTO;
import com.mangagod.service.base.BaseService;

public interface JobService extends BaseService<JobAllPageableDataDTO, JobResponseDTO, JobRequestDTO, Integer>{

}