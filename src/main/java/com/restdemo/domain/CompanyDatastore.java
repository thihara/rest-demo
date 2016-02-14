package com.restdemo.domain;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by thihara.
 */
public class CompanyDatastore {
    private static ConcurrentHashMap<Long, Company> companyStore = new ConcurrentHashMap<>(50);
    private static AtomicLong keyCounter = new AtomicLong(0);

    public static Long addCompany(Company company){
        company.setId(keyCounter.incrementAndGet());

        companyStore.put(company.getId(), company);

        return company.getId();
    }

    public static Optional<Company> getCompany(Long id){
        return Optional.ofNullable(Company.fromCompany(companyStore.get(id)));
    }

    public static void updateCompany(Company company){
        Company oldVal = companyStore.replace(company.getId(), company);

        if(oldVal == null)
            throw new RuntimeException("Invalid company id.");
    }

    public static Collection<Company> listAll(){
        return companyStore.values();
    }
}
