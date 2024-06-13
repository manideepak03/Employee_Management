package com.assign.employee_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CreateRequest {
    private String name;
    private String email;
    private String department;
    private String designation;
    private LocalDate joinDate;
}
