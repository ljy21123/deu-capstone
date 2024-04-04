
package com.example.infoweb.repository;

import com.example.infoweb.entity.NaverNews;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<NaverNews, String> {



}
