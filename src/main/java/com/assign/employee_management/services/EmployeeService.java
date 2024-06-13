package com.assign.employee_management.services;

import com.assign.employee_management.dto.*;
import com.assign.employee_management.models.EmployeeModel;
import com.assign.employee_management.repository.EmployeeCustomRepository;
import com.assign.employee_management.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

//    private final List<EmployeeModel> employeeList = new LinkedList<>();
    private final EmployeeRepository employeeRepository;
    private final EmployeeCustomRepository employeeCustomRepository;
    public CreateResponse createEmployee(CreateRequest employee, String userId) {
        EmployeeModel employeeModel = EmployeeModel.builder()
                .userId(userId)
                .name(employee.getName())
                .email(employee.getEmail())
                .department(employee.getDepartment())
                .designation(employee.getDesignation())
                .joinDate(employee.getJoinDate()).
                build();
//        employeeList.add(employeeModel);
//        if(employeeRepository.existsById(userId))
        if(!Objects.isNull(employeeRepository.findByUserId(userId)))
        {
            log.info("*************");
            throw new RuntimeException("Already Exist by this UserId");
        }
        employeeRepository.save(employeeModel);
        return CreateResponse.builder()
                .Id(employeeModel.getUserId())
                .message("Employee created successfully")
                .build();
    }

    public EmployeeModel getEmployeeById(String userId) {
//        for (var employee : employeeList) {
//            if (employee.getUserId().equals(userId)) {
//                return employee;
//            }
//        }
//        return null;
        return employeeRepository.findByUserId(userId);
//        throw new RuntimeException();
    }

    public UpdateResponse updateEmployee(String userId, UpdateRequest updateRequest) {
        EmployeeModel employeeModel = employeeRepository.findByUserId(userId);
        if(Objects.isNull(employeeModel))
        {
            throw new RuntimeException("Employee doesn't exist");
        }
        employeeModel.setName(updateRequest.getName());
        employeeModel.setEmail(updateRequest.getEmail());
        employeeModel.setDepartment(updateRequest.getDepartment());
        employeeModel.setDesignation(updateRequest.getDesignation());
//        for (var employee : employeeList) {
//            if (employee.getUserId().equals(userId)) {
//                employee.setName(updateRequest.getName());
//                employee.setEmail(updateRequest.getEmail());
//                employee.setDepartment(updateRequest.getDepartment());
//                employee.setDesignation(updateRequest.getDesignation());
//                break;
//            }
//        }
        employeeRepository.deleteByUserId(userId);
        employeeRepository.save(employeeModel);
        return UpdateResponse.builder()
                .message("Employee updated successfully")
                .build();

    }

    public DeleteResponse deleteEmployee(String userId) {

//        for (var employee : employeeList) {
//            if (employee.getUserId().equals(userId)) {
//                employeeList.remove(employee);
//                break;
//            }
//        }
//        EmployeeModel employeeModel = employeeRepository.findByUserId(userId);;
        employeeRepository.deleteByUserId(userId);
        return  DeleteResponse.builder()
                .message("Employee deleted successfully")
                .build();
    }

    public List<EmployeeModel> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public AverageExperienceResponse getAvgExperience() {
//
//        return employeeCustomRepository.averageExperience();
        List<EmployeeModel> employeeModels = employeeRepository.findAll();
        LocalDate currentDate = LocalDate.now();

        long totalExperienceInDays = employeeModels.stream()
                .mapToLong(employee -> ChronoUnit.DAYS.between(employee.getJoinDate(), currentDate))
                .sum();
        log.info(String.valueOf(totalExperienceInDays));
        double averageExperienceInYears = totalExperienceInDays / ((double) employeeModels.size()* 365.0);
        log.info(String.valueOf(averageExperienceInYears));
        return AverageExperienceResponse.builder()
                .avgExperience(averageExperienceInYears)
                .build();
    }

    public List<EmployeeCountResponse> getEmployeeCount() {
        log.info(employeeCustomRepository.employeeCount().toString());
        return employeeCustomRepository.employeeCount();
    }
}
