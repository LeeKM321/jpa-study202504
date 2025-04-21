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
    @OneToMany(mappedBy = "department",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Employee> employees;

        /*
        CascadeType.PERSIST
        엔터티를 영속화할 때, 연관된 하위 엔터티도 함께 영속화 한다.
        연관 관계의 주인은 Employee. Department의 list는 가상 데이터.
        주인이 아닌 부모가 자식의 생명 주기에 영향을 주고 싶을 때 사용.

        CascadeType.ALL
        모든 Cascade를 적용한다.

        CascadeType.MERGE
        엔티티 상태를 병합(Merge)할 때, 연관된 하위 엔티티도 모두 병합한다.

        CascadeType.REMOVE
        엔티티를 제거할 때, 연관된 하위 엔티티도 모두 제거한다.

        CascadeType.DETACH
        영속성 컨텍스트에서 엔티티 제거
        부모 엔티티를 detach() 수행하면, 연관 하위 엔티티도 detach()상태가 되어 변경 사항을 반영하지 않는다.

        CascadeType.REFRESH
        상위 엔티티를 새로고침(Refresh)할 때, 연관된 하위 엔티티도 모두 새로고침한다.

     */

}








