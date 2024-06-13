package com.assign.employee_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateRequest {
    private String name;
    private String email;
    private String department;
    private String designation;
}
