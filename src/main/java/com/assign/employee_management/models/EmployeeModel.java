package com.assign.employee_management.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@Document
public class EmployeeModel {
    private String userId;
    private String name;
    private String email;
    private String department;
    private String designation;
    private LocalDate joinDate;
}
