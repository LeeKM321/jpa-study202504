package com.study.jpa.chap04.repository;

import com.study.jpa.chap04.entity.Department;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("부서 정보를 조회하면 해당 부서원들도 함께 조회되어야 한다.")
    void testFindDept() {
        // given
        Long id = 2L;

        // when
        Department department
                = departmentRepository.findById(id).orElseThrow();

        // then
        System.out.println("\n\n\n");
        System.out.println("department = " + department);
        System.out.println(department.getEmployees());
        System.out.println("\n\n\n");
    }

}







