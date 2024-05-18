/*
 *   Spring Data JPA의 CrudRepository 인터페이스를 상속받아
 *   기본적인 NaverRealTimeNews 테이블의 CRUD 작업을 수행할 수 있는 메소드를 제공합니다.
 *
 *   작성자: 이준영
 *
 * */

package com.example.infoweb.repository;

import com.example.infoweb.entity.NaverRealTimeNews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface RealTimeNewsRepository extends CrudRepository<NaverRealTimeNews, String> {

    @Override
    ArrayList<NaverRealTimeNews> findAll();

    // category 문자열과 일치하는 모든 NaverRealTimeNews 엔티티 인스턴스를 반환
    ArrayList<NaverRealTimeNews> findByCategory(String category);

    // 특정 카테고리의 뉴스를 페이징하여 반환하는 메서드
    Page<NaverRealTimeNews> findByCategory(String category, Pageable pageable);

}
