package com.pollchihuy.repo;

import com.pollchihuy.model.DataCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataCustomerRepo extends JpaRepository<DataCustomer, Long> {
}
