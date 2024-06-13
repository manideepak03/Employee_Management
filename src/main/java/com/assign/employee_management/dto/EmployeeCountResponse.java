package com.assign.employee_management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class EmployeeCountResponse {
    private String department;
    private String designation;
    private Integer employeeCount;

}
