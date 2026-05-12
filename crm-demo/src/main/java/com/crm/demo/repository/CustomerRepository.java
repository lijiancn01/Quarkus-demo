package com.crm.demo.repository;

import com.crm.demo.entity.Customer;
import com.crm.demo.entity.CustomerStatus;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
    
    public List<Customer> findByStatus(CustomerStatus status) {
        return list("status", status);
    }
    
    public List<Customer> searchByName(String name) {
        return list("name like ?1", "%" + name + "%");
    }
    
    public List<Customer> findActiveCustomers() {
        return findByStatus(CustomerStatus.ACTIVE);
    }
    
    public long countByStatus(CustomerStatus status) {
        return count("status", status);
    }
}
