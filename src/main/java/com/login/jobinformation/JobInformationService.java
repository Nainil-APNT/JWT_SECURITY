package com.login.jobinformation;

import java.util.List;

import com.login.aql.AQLRequest;
import com.login.aql.AQLResponse;

public interface JobInformationService {

	JobInformation createJobInformation(JobInformation jobInformation);

	List<JobInformation> getAllJobInformation();

	JobInformation getJobInformationById(Integer jobInformationId);

	JobInformation updateJobInformation(JobInformation jobInformation);

	void deleteJobInformation(Integer jobInformationId);

	AQLResponse<Object> getFilteredAndPaginatedJobInformationServices(AQLRequest request);
}
