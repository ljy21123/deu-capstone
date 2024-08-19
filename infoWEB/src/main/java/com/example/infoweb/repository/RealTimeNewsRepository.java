/*
 *   Spring Data JPA의 CrudRepository 인터페이스를 상속받아
 *   기본적인 NaverRealTimeNews 테이블의 CRUD 작업을 수행할 수 있는 메소드를 제공합니다.
 *
 *   작성자: 이준영
 *
 * */

package com.example.infoweb.repository;

import com.example.infoweb.entity.NaverRealTimeNews;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface RealTimeNewsRepository extends CrudRepository<NaverRealTimeNews, String> {

    // (종합) 오늘 날짜 뉴스를 반환
    @Query("SELECT n FROM NaverRealTimeNews n WHERE DATE(n.created_at) = :targetDate")
    List<NaverRealTimeNews> findLatestNews(LocalDate targetDate);

    // category 문자열과 일치하는 오늘 날짜 NaverRealTimeNews 엔티티 인스턴스를 내림차순으로 반환
    @Query("SELECT n FROM NaverRealTimeNews n WHERE n.category = :category AND DATE(n.created_at) = :targetDate ORDER BY n.id DESC")
    List<NaverRealTimeNews> findByCategoryAndDate(String category, LocalDate targetDate);

    // (종합) 가장 최근 날짜 뉴스를 반환
    @Query("SELECT MAX(DATE(n.created_at)) FROM NaverRealTimeNews n")
    LocalDate findMaxDate();

    // (카테고리별) 가장 최근 날짜 뉴스를 반환
    @Query("SELECT MAX(DATE(n.created_at)) FROM NaverRealTimeNews n WHERE n.category = :category")
    LocalDate findMaxDateByCategory(String category);

    @Override
    default List<NaverRealTimeNews> findAll() {
        LocalDate today = LocalDate.now();
        List<NaverRealTimeNews> newsList = findLatestNews(today);
        if (newsList.isEmpty()) {
            // 오늘 데이터가 없는 경우 가장 최근 데이터를 조회
            LocalDate latestDate = findMaxDate();
            if (latestDate != null) {
                newsList = findLatestNews(latestDate);
            }
        }
        return newsList;
    }

    default List<NaverRealTimeNews> findByCategory(String category) {
        LocalDate today = LocalDate.now();
        List<NaverRealTimeNews> newsList = findByCategoryAndDate(category, today);
        if (newsList.isEmpty()) {
            // 오늘 데이터가 없는 경우 해당 카테고리의 가장 최근 데이터를 조회
            LocalDate latestDate = findMaxDateByCategory(category);
            if (latestDate != null) {
                newsList = findByCategoryAndDate(category, latestDate);
            }
        }
        return newsList;
    }

}
