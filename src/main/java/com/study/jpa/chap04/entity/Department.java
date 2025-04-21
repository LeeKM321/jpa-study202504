package com.study.jpa.chap04.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter @Getter
// JPA 연관관계 매핑에서 연관관계 필드는 toString에서 제외해야 합니다. (순환 참조 발생)
@ToString(exclude = "employees")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tbl_dept")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Long id;

    @Column(name = "dept_name", nullable = false)
    private String name;

    // 양방향 맵핑에서는 실제 테이블에 list가 세팅되지 않습니다.
    // 엔터티 안에서만 사용하는 가상의 컬럼입니다.
    // 상대방 엔터티의 갱신에 관여할 수 없기 때문에 단순히 읽기 전용(조회)으로만 사용하는 것을 권장.
    @OneToMany(mappedBy = "department") // 연관관계 엔터티의 필드명을 작성
    private List<Employee> employees;

}








