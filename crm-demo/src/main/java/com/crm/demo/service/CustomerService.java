package com.crm.demo.service;

import com.crm.demo.entity.Customer;
import com.crm.demo.entity.CustomerStatus;
import com.crm.demo.repository.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CustomerService {
    
    @Inject
    CustomerRepository customerRepository;
    
    public List<Customer> getAllCustomers() {
        return customerRepository.listAll();
    }
    
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id);
    }
    
    public List<Customer> getActiveCustomers() {
        return customerRepository.findActiveCustomers();
    }
    
    public List<Customer> searchCustomers(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllCustomers();
        }
        return customerRepository.searchByName(keyword);
    }
    
    @Transactional
    public Customer createCustomer(Customer customer) {
        customerRepository.persist(customer);
        return customer;
    }
    
    @Transactional
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer customer = customerRepository.findById(id);
        if (customer == null) {
            return null;
        }
        customer.name = updatedCustomer.name;
        customer.email = updatedCustomer.email;
        customer.phone = updatedCustomer.phone;
        customer.company = updatedCustomer.company;
        customer.status = updatedCustomer.status;
        customerRepository.persist(customer);
        return customer;
    }
    
    @Transactional
    public boolean deleteCustomer(Long id) {
        return customerRepository.deleteById(id);
    }
    
    @Transactional
    public Customer toggleCustomerStatus(Long id) {
        Customer customer = customerRepository.findById(id);
        if (customer == null) {
            return null;
        }
        customer.status = (customer.status == CustomerStatus.ACTIVE) 
                ? CustomerStatus.INACTIVE 
                : CustomerStatus.ACTIVE;
        customerRepository.persist(customer);
        return customer;
    }
    
    public long getTotalCount() {
        return customerRepository.count();
    }
    
    public long getActiveCount() {
        return customerRepository.countByStatus(CustomerStatus.ACTIVE);
    }
    
    public long getInactiveCount() {
        return customerRepository.countByStatus(CustomerStatus.INACTIVE);
    }
}
