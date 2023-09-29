package com.login.company;

import java.util.List;

import com.login.aql.AQLRequest;
import com.login.aql.AQLResponse;

public interface CompanyService {

	Company createCompany(Company company);

	List<Company> getAllCompanies();

	Company getCompanyById(Integer companyId);

	Company updateCompany(Company company);

	void deleteCompany(Integer companyId);

	AQLResponse<Object> getFilteredAndPaginatedCompines(AQLRequest request);
}
