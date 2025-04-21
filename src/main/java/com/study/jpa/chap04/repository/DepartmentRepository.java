package com.study.jpa.chap04.repository;

import com.study.jpa.chap04.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // 연관관계에서 다른 엔터티의 데이터를 가져올 때는 Lazy Loading을 선호한다고 했습니다.
    // 다른 엔터티의 개수가 많아지면, N+1 문제가 발생합니다.
    // (연관 데이터의 갯수만큼 SELECT문이 증가)
    // FETCH JOIN을 사용하면 하나의 SQL로 필요한 모든 데이터를 가져옵니다.
    @Query("SELECT d FROM Department d JOIN FETCH d.employees")
    List<Department> findAllIncludesEmployees();

}









