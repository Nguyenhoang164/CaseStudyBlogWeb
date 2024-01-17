package com.example.blog.Service.Impl;

import com.example.blog.Model.Customer;
import com.example.blog.Repository.ICustomerRepository;
import com.example.blog.Service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;
    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void save(Customer customer) {
          customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public void remove(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void update(Customer customer) {
        remove(customer.getId());
        save(customer);
    }

    @Override
    public Customer findCustomerById(int id) {
        return customerRepository.findCustomerById(id);
    }

    @Override
    public Customer findCustomersByName(String name) {
        return customerRepository.findCustomersByName(name);
    }

    @Override
    public Customer findCustomersByEmail(String email) {
        return customerRepository.findCustomersByEmail(email);
    }
}
