package com.study.jpa.chap04.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tbl_emp")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long id;

    @Column(nullable = false, name = "emp_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "dept_id") // FK 컬럼명 (연관 테이블의 컬럼명과 일치하게)
    private Department department;

}
