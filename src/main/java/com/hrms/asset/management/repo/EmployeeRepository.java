package com.hrms.asset.management.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.asset.management.dao.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
