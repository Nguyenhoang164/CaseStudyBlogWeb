package com.example.blog.Service;

import com.example.blog.Model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface ICustomerService extends IGenerateService<Customer> {
    Customer findCustomerById(int id);

    Customer findCustomersByName(String name);
    Customer findCustomersByEmail(String email);
    Page<Customer> findAllBy(Pageable pageable);
}
