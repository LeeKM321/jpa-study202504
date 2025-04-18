package com.study.jpa.chap01.repository;

import com.study.jpa.chap01.entity.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class ProductRepositoryTest2 {

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("상품을 데이터베이스에 저장한다.")
    void saveTest() {
        // given

        // Builder 패턴을 이용한 객체 생성. (Lombok 사용)
        // 필드명과 동일한 메서드를 제공하기 때문에 원하는 필드만 초기화 하는 것이 가능.
        Product p = Product.builder()
                .name("떡볶이")
                .price(3000)
                .category(Product.Category.FOOD)
                .build();

        // when
        // INSERT 후 저장된 데이터의 객체 반환.
        Product saved = productRepository.save(p);

        // then
    }

    @Test
    @DisplayName("2번 상품을 삭제한다.")
    void deleteTest() {
        // given
        Long id = 2L;
        // when
        productRepository.deleteById(id);

        // then
        Optional<Product> optional = productRepository.findById(id);
        System.out.println(optional.isPresent());
        optional.ifPresent(p -> {
            System.out.println(p);
        });

    }
    
    @Test
    @DisplayName("상품 전체 조회를 하면 개수는 4개여야 한다.")
    void selectAllTest() {
        // given
        
        // when
        List<Product> products = productRepository.findAll();

        // then
        products.forEach(System.out::println);
        assertEquals(4, products.size());
    }

}








