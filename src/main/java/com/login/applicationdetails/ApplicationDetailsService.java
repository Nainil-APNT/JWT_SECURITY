package com.login.applicationdetails;

import java.util.List;

import com.login.aql.AQLRequest;
import com.login.aql.AQLResponse;

public interface ApplicationDetailsService {

	ApplicationDetails createApplicationDetails(ApplicationDetails applicationDetails);

	List<ApplicationDetails> getAllApplicationDetails();

	ApplicationDetails getApplicationDetailsById(Integer applicationId);

	ApplicationDetails updateApplicationDetails(ApplicationDetails applicationDetails);

	void deleteApplicationDetails(Integer applicationId);

	AQLResponse<Object> getFilteredAndPaginatedApplicationDetails(AQLRequest request);

}
