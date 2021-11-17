package com.jumia.task.jumia_task.Repository;

import com.jumia.task.jumia_task.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}