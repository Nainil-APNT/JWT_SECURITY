package com.login.jobcategory;

import java.util.List;

import com.login.aql.AQLRequest;
import com.login.aql.AQLResponse;

public interface JobCategoryService {

	JobCategory createJobCategory(JobCategory jobCategory);

	List<JobCategory> getAllJobCategory();

	JobCategory getJobCategoryById(Integer jobCategoryId);

	JobCategory updateJobCategory(JobCategory jobCategory);

	void deleteJobCategory(Integer jobCategoryId);

	AQLResponse<Object> getFilteredAndPaginatedJobCategory(AQLRequest request);

}
