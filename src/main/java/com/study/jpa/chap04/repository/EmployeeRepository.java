package com.study.jpa.chap04.repository;

import com.study.jpa.chap04.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
