package com.restdemo.services;

import com.restdemo.domain.Company;
import com.restdemo.domain.CompanyDatastore;
import com.restdemo.exception.CompanyNotFoundException;
import com.restdemo.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by thihara on 2/13/16.
 */
@Component
public class CompanyService {

    public Long createCompany(Company company){
        validateCompanyData(company);

        return CompanyDatastore.addCompany(company);
    }

    public void updateCompany(Company company){
        validateNotNull(company.getId(), "ID");

        Company updatedCompany = updateFields(company);

        CompanyDatastore.updateCompany(updatedCompany);
    }

    public Collection<Company> listCompanies(){
        return CompanyDatastore.listAll();
    }

    public Company listCompanyDetails(Long companyID){
        return getCompany(companyID);
    }

    public void addOwner(Long companyID, String owner){
        validateNonEmpty(owner, "Owner");

        Company company = getCompany(companyID);

        company.getOwners().add(owner);

        CompanyDatastore.updateCompany(company);
    }

    private Company getCompany(Long companyID) {
        validateNotNull(companyID, "Company ID");

        return CompanyDatastore.getCompany(companyID).
                orElseThrow(() -> new CompanyNotFoundException("Unable to find a company with the given ID"));
    }

    private Company updateFields(Company company) {
        Company original = getCompany(company.getId());

        if(company.getCountry() != null)
            original.setCountry(company.getCountry());

        if (company.getCity() != null)
            original.setCity(company.getCity());

        if (company.getAddress() != null)
            original.setAddress(company.getAddress());

        if (company.getName() != null)
            original.setName(company.getName());

        if (company.getOwners() != null && !company.getOwners().isEmpty())
            original.setOwners(company.getOwners());

        //Optional fields can be overridden into null values.
        original.setPhoneNumber(company.getPhoneNumber());
        original.setEmail(company.getEmail());
        return original;
    }

    private void validateCompanyData(Company company){
        validateNotNull(company.getAddress(), "Address");
        validateNotNull(company.getCity(), "City");
        validateNotNull(company.getName(), "Name");
        validateNotNull(company.getCountry(), "Country");
        validateNotNull(company.getOwners(), "Owners");
        validateCollection(company.getOwners(), "Owners");
    }

    private void validateNotNull(Object val, String fieldName){
        if(val == null)
            throw new ValidationException(fieldName + " cannot be null");
    }

    private void validateNonEmpty(String val, String fieldName){
        validateNotNull(val, fieldName);

        if(val.isEmpty())
            throw new ValidationException(fieldName + " cannot be null");
    }

    private void validateCollection(Collection col, String fieldName){
        if(col.isEmpty())
            throw new ValidationException(fieldName + " cannot be empty");
    }
}
