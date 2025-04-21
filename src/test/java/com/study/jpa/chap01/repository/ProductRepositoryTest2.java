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

    @Test
    @DisplayName("3번 상품의 이름과 가격을 변경해야 한다.")
    void updateTest() {
        // given
        Long id = 3L;
        String newName = "삼겹살";
        int newPrice = 14000;

        // when
        Optional<Product> optional = productRepository.findById(id);

        // optional이 감싸고 있는 객체가 비어있을 경우 예외가 발생.
        // 존재한다면 해당 객체를 리턴.
        Product product
                = optional.orElseThrow(() -> new RuntimeException("조회된 객체가 없음!"));
        product.setName(newName);
        product.setPrice(newPrice);

        // jpa는 따로 update 메서드를 제공하지 않습니다.
        // 조회한 객체의 필드를 setter로 변경하면 자동으로 update가 나갑니다. (Dirty check)
        productRepository.save(product);


        // then
    }

}








