package com.assign.employee_management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CreateResponse {

    private String Id;
    private String message;
}
