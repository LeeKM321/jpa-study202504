package com.study.jpa.chap02.repository;

import com.study.jpa.chap02.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

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

    /////////////////////////////////////////////////////////////////////

    // native-sql (쌩 sql) 사용 방법
    @Query(
            value = "SELECT * FROM tbl_student WHERE stu_name = :n OR city = :city",
            nativeQuery = true)
    List<Student> getStudentByNameOrCity(@Param("n") String name, @Param("city") String city);

    // native-sql (테이블, 컬럼 위주)
    // SELECT 컬럼명 FROM 테이블명
    // WHERE 컬럼 = ?

    // JPQL (Entity 클래스 위주)
    // SELECT 별칭 FROM 엔터티클래스명 AS 별칭
    // WHERE 별칭.필드명 = ?

    @Query(value = "SELECT stu FROM Student stu WHERE stu.name = ?1 OR stu.city = ?2")
    List<Student> getStudentByNameOrCity2(String name, String city);

    // 특정 이름이 포함된 학생 리스트 조회하기
    @Query("SELECT stu FROM Student stu WHERE stu.name LIKE ?1")
    List<Student> searchByNameWithJPQL(String name);

    // 도시명으로 학생 1명을 단일 조회 -> 이름만 조회
    @Query("SELECT s FROM Student s WHERE s.city = ?1")
    Optional<Student> getByCityWithJPQL(String city);

    // 삭제 로직
    @Modifying // SELECT 아니면 무조건 붙이세요!
    @Query("DELETE FROM Student s WHERE s.name = ?1 AND s.city = ?2")
    void deleteByNameAndCityWithJPQL(String name, String city);

}








