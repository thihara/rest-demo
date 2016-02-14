package com.restdemo.controller;

import com.restdemo.domain.Company;
import com.restdemo.exception.CompanyNotFoundException;
import com.restdemo.exception.ValidationException;
import com.restdemo.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thihara on 2/13/16.
 */
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/company/{companyID}", method = RequestMethod.GET)
    public Company getCompany(@PathVariable Long companyID){
        return companyService.listCompanyDetails(companyID);
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public Collection<Company> getAllCompanies(){
        return companyService.listCompanies();
    }

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    public void createCompany(Company company){
        companyService.createCompany(company);
    }

    @RequestMapping(value = "/company/{companyID}", method = RequestMethod.PUT)
    public void updateCompany(@PathVariable Long companyID, Company company){
        company.setId(companyID);
        companyService.updateCompany(company);
    }

    @RequestMapping(value = "/company/{companyID}/owner", method = RequestMethod.PUT)
    public void addOwner(@PathVariable Long companyID, @RequestParam String owner){
        companyService.addOwner(companyID, owner);
    }

    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String,Object> genericExceptionHandler(Exception e) {
        return getErrorMap(e);
    }

    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    @ExceptionHandler(CompanyNotFoundException.class)
    public Map<String,Object> notFoundHandler(CompanyNotFoundException e) {
        return getErrorMap(e);
    }

    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public Map<String,Object> validationErrorHandler(ValidationException e) {
        return getErrorMap(e);
    }

    private Map<String, Object> getErrorMap(Exception e) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("error", true);
        result.put("error_message", e.getMessage());
        return result;
    }
}