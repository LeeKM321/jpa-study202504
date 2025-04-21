package com.study.jpa.chap04.repository;

import com.study.jpa.chap04.entity.Department;
import com.study.jpa.chap04.entity.Employee;
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

    @Test
    @DisplayName("양방향 연관관계에서 연관 데이터의 수정")
    void testChangeDept() {
        // 1번 사원의 부서를 1 -> 2번 부서로 변경해야 한다.

        // given
        Employee foundEmp
                = employeeRepository.findById(3L).orElseThrow();
        Department department
                = departmentRepository.findById(1L).orElseThrow();

        // emp리스트에 아무리 사원 add 해봤자 실제 DB에는 적용 안됩니다!
//        department.getEmployees().add(foundEmp);

        // when
        // update는 트랜잭션이 종료되어야 동작합니다.
        // 이 작업 단위(메서드) 안에서 부서에도 사원의 변경된 정보를 사용할 수 있도록
        // 연관관계 편의 메서드를 통해 리스트에 사원을 직접 추가해서 마치 변경사항이
        // 바로 반영된 것처럼 사용이 가능.
        foundEmp.changeDepartment(department);

        // then
        employeeRepository.save(foundEmp);
        System.out.println("\n\n\n");
        department.getEmployees().forEach(System.out::println);
        System.out.println("\n\n\n");
    }

}







