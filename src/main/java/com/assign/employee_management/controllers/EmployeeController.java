package com.assign.employee_management.controllers;

import com.assign.employee_management.dto.*;
import com.assign.employee_management.models.EmployeeModel;
import com.assign.employee_management.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;
    @PostMapping(path = "/api/employees")
    public CreateResponse createEmployee(@RequestBody CreateRequest employee, @RequestHeader("user-id") String userId)
    {
        return employeeService.createEmployee(employee,userId);
    }
    @GetMapping(path = "/api/employees/{id}")
    public EmployeeModel getEmployeeById(@PathVariable String id)
    {
        return employeeService.getEmployeeById(id);
    }
    @PutMapping(path = "PUT/api/employees/{id}")
    public UpdateResponse updateEmployee(@PathVariable String id, @RequestBody UpdateRequest updateRequest)
    {
        return employeeService.updateEmployee(id,updateRequest);
    }
    @PutMapping(path="DELETE/api/employees/{id}")
    public DeleteResponse deleteEmployee(@PathVariable String id)
    {
        return employeeService.deleteEmployee(id);
    }
    @GetMapping(path ="/api/employees/getall")
    public List<EmployeeModel> getEmployees()
    {
        return employeeService.getAllEmployees();
    }
    @GetMapping(path = "GET/api/employees/average-experience")
    public AverageExperienceResponse getAvgExperience()
    {
        return employeeService.getAvgExperience();
    }
    @GetMapping(path = "GET/api/employees/department-count")
    public List<EmployeeCountResponse> getEmployeeCount()
    {
        log.info("controller"+ employeeService.getEmployeeCount().toString());
        return employeeService.getEmployeeCount();
    }

}
