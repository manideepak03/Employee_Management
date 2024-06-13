package com.assign.employee_management.repository;

import com.assign.employee_management.dto.AverageExperienceResponse;
import com.assign.employee_management.dto.EmployeeCountResponse;
import com.assign.employee_management.models.EmployeeModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository{
    private final MongoTemplate mongoTemplate;
    @Override
    public AverageExperienceResponse averageExperience() {
//        LocalDate currentDate = LocalDate.now();
//        AggregationOperation addCurrentDateField = addFields()
//                .addField("currentDate")
//                .withValue(currentDate)
//                .build();
//        AggregationOperation projectDaysDifference = project()
//                .andExpression(
//                        "(new Date(currentDate).getTime() - new Date(joinDate).getTime()) / (1000 * 60 * 60 * 24)"
//                ).as("daysDifference")
//                .andInclude("joinDate").andInclude("currentDate");
//        AggregationOperation groupOperation = group()
//                .sum("daysDifference").as("totalDaysDifference")
//                .count().as("totalModels");
//
//        AggregationOperation projectOperation = project()
//                .andExpression("totalDaysDifference / (365 * totalModels)").as("averageDaysDifference");
//
//        Aggregation aggregation = newAggregation(addCurrentDateField, projectDaysDifference, groupOperation, projectOperation);
//
//        AggregationResults<AverageExperienceResponse> results = mongoTemplate.aggregate(aggregation, EmployeeModel.class, AverageExperienceResponse.class);
//        log.info(String.valueOf(results));
//        return results.getMappedResults().get(0);
        return null;

    }

    @Override
    public List<EmployeeCountResponse> employeeCount() {
        GroupOperation groupOperation = group("department","designation").count().as("employeeCount")
                .first("department").as("department")
                .first("designation").as("designation");

        Aggregation aggregation = Aggregation.newAggregation(groupOperation);
        AggregationResults<EmployeeCountResponse> results = mongoTemplate.aggregate(aggregation, EmployeeModel.class, EmployeeCountResponse.class);
        log.info(results.getMappedResults().toString());
        return results.getMappedResults();
    }
}
