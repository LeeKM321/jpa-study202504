package com.study.jpa.chap02.repository;

import com.study.jpa.chap02.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,String> {

    // JPA 쿼리 메서드 -> 메서드 이름으로 jpa가 쿼리를 만듭니다.
    // pk 관련 메서드는 기본으로 제공되지만, 다른 컬럼을 이용한 sql은 직접 생성해야 합니다.
    // 메서드 이름으로 쿼리를 조합하게 작성합니다.
    List<Student> findByName(String name);

    List<Student> findByCityAndMajor(String city, String major);

    // WHERE major LIKE '%major%'
    List<Student> findByMajorContaining(String major);

    // WHERE major LIKE 'major%'
    List<Student> findByMajorStartingWith(String major);

    // WHERE major LIKE '%major'
    List<Student> findByMajorEndingWith(String major);

    // WHERE age >= ?
//    findNameAndMajorByAgeGreaterAndEqual(int age);
    // lessAndEqual -> age <= ?

}








