package com.assign.employee_management.repository;

import com.assign.employee_management.models.EmployeeModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeModel, String> {
    EmployeeModel findByUserId(String UserId);
    EmployeeModel deleteByUserId(String UserId);
}
