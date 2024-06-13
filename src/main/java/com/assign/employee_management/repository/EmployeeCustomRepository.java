package com.assign.employee_management.repository;

import com.assign.employee_management.dto.AverageExperienceResponse;
import com.assign.employee_management.dto.EmployeeCountResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeCustomRepository {
    AverageExperienceResponse averageExperience();
    List<EmployeeCountResponse> employeeCount();
}
