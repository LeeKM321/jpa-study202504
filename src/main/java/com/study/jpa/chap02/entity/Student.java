package com.study.jpa.chap02.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tbl_student")
public class Student {

    @Id
    @Column(name = "stu_id")
    // UUID: Universally Unique Identifier
    // 소프트웨어에서 각각의 객체를 고유하게 식별하기 위해 사용하는 영문과 숫자의 조합.(랜덤값)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "stu_name",  nullable = false)
    private String name;

    private String city;

    private String major;

}










