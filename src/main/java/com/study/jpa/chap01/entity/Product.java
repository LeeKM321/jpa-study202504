package com.study.jpa.chap01.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter @ToString
@EqualsAndHashCode(of = "id") // id 필드가 같으면 같은 객체로 처리하겠다.
@NoArgsConstructor
@AllArgsConstructor

@Entity // 이 클래스는 JPA가 관리한다. 이 클래스는 데이터베이스의 한 행에 정확히 대응한다.
@Table(name = "tbl_product")
public class Product {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name = "prod_id")
    private Long id;

    @Column(name = "prod_name", length = 30, nullable = false)
    private String name;

    @Column(name = "prod_price")
    private int price;

    @Column(nullable = false) // not null
    @Enumerated(EnumType.STRING)
    private Category category;

    @CreationTimestamp // INSERT 시에 자동으로 서버 시간 저장
    @Column(updatable = false) // 수정 불가
    private LocalDateTime createAt;

    @UpdateTimestamp // update문 실행 시 자동으로 시간이 저장
    private LocalDateTime updateAt;

    // 데이터베이스에는 저장 안하고 클래스 내부에서만 사용할 필드
    @Transient
    private String nickName;

    public enum Category {
        FOOD, FASHION, ELECTRONIC
    }

}
