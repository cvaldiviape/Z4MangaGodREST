package com.mangagod.service;

import com.mangagod.dto.data.JobDataDTO;
import com.mangagod.dto.pagination.JobAllPageableDataDTO;
import com.mangagod.dto.request.JobRequestDTO;
import com.mangagod.service.base.BaseService;

public interface JobService extends BaseService<JobAllPageableDataDTO, JobDataDTO, JobRequestDTO, Integer>{

}