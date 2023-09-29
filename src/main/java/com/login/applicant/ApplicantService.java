package com.login.applicant;

import java.util.List;

import com.login.aql.AQLRequest;
import com.login.aql.AQLResponse;

public interface ApplicantService {

	Applicant createApplicant(Applicant applicant);

	List<Applicant> getAllApplicant();

	Applicant getApplicantById(Integer applicantId);

	Applicant updateApplicant(Applicant applicant);

	void deleteApplicant(Integer applicantId);

	AQLResponse<Object> getFilteredAndPaginatedApplicants(AQLRequest request);

}
