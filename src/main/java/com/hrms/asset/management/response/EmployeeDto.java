package com.hrms.asset.management.response;

import lombok.Data;

@Data
public class EmployeeDto {
    private String name;
    private String username;
    private String email;
    private String phone;
    private String jobTitle;
    private String project;
    private String jobType;
    private String jobStatus;
    private String jobDescription;
}