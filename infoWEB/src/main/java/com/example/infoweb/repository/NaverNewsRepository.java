/*
 *   Spring Data JPA의 CrudRepository 인터페이스를 상속받아
 *   기본적인 NaverNewsRepository 테이블의 CRUD 작업을 수행할 수 있는 메소드를 제공합니다.
 *
 *   작성자: 이준영
 *
 * */

package com.example.infoweb.repository;

import com.example.infoweb.entity.NaverNews;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface NaverNewsRepository extends CrudRepository<NaverNews, String> {

    // (종합) 오늘 날짜 뉴스를 반환
    @Query("SELECT n FROM NaverNews n WHERE DATE(n.created_at) = :targetDate")
    List<NaverNews> findLatestNews(LocalDate targetDate);

    // category 문자열과 일치하는 오늘 날짜 NaverNews 엔티티 인스턴스를 내림차순으로 반환
    @Query("SELECT n FROM NaverNews n WHERE n.category = :category AND DATE(n.created_at) = :targetDate ORDER BY n.id DESC")
    List<NaverNews> findByCategoryAndDate(String category, LocalDate targetDate);

    // (종합) 가장 최근 날짜 뉴스를 반환
    @Query("SELECT MAX(DATE(n.created_at)) FROM NaverNews n")
    LocalDate findMaxDate();

    // (카테고리별) 가장 최근 날짜 뉴스를 반환
    @Query("SELECT MAX(DATE(n.created_at)) FROM NaverNews n WHERE n.category = :category")
    LocalDate findMaxDateByCategory(String category);

    @Override
    default List<NaverNews> findAll() {
        LocalDate today = LocalDate.now();
        List<NaverNews> newsList = findLatestNews(today);
        if (newsList.isEmpty()) {
            // 오늘 데이터가 없는 경우 가장 최근 데이터를 조회
            LocalDate latestDate = findMaxDate();
            if (latestDate != null) {
                newsList = findLatestNews(latestDate);
            }
        }
        return newsList;
    }

    default List<NaverNews> findByCategory(String category) {
        LocalDate today = LocalDate.now();
        List<NaverNews> newsList = findByCategoryAndDate(category, today);
        if (newsList.isEmpty()) {
            // 오늘 데이터가 없는 경우 해당 카테고리의 가장 최근 데이터를 조회
            LocalDate latestDate = findMaxDateByCategory(category);
            if (latestDate != null) {
                newsList = findByCategoryAndDate(category, latestDate);
            }
        }
        return newsList;
    }

    /**
     * findBy: 이 메서드가 쿼리 메서드임을 나타내며, 결과를 찾기 위해 데이터베이스를 조회할 것을 나타냅니다.
     * Title: NaverNews 엔티티의 title 속성에 대한 조건을 나타냅니다.
     * Containing: LIKE '%keyword%'와 유사한 SQL 조건을 생성합니다. 즉, 주어진 문자열을 포함하는 title을 찾습니다.
     * IgnoreCase: 대소문자를 구분하지 않고 검색합니다.
     */
    ArrayList<NaverNews> findByTitleContainingIgnoreCase(String title);
}
