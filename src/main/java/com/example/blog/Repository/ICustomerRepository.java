package com.example.blog.Repository;

import com.example.blog.Model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepository extends CrudRepository<Customer , Integer> {
    Customer findCustomerById(int id);
    Customer findCustomersByName(String name);
    Customer findCustomersByEmail(String email);
}
