package com.study.jpa.chap01.repository;

import com.study.jpa.chap01.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository를 상속한 후 첫번째 제네릭에 엔터티클래스 타입,
// 두번째에 PK의 타입을 작성합니다.
public interface ProductRepository extends JpaRepository<Product, Long> {
}
