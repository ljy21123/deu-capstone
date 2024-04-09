
package com.example.infoweb.repository;

import com.example.infoweb.entity.NaverNews;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface NaverNewsRepository extends CrudRepository<NaverNews, String> {

    @Override
    ArrayList<NaverNews> findAll();

    // category 문자열과 일치하는 모든 NaverNews 엔티티 인스턴스를 반환
    ArrayList<NaverNews> findByCategory(String category);
}
