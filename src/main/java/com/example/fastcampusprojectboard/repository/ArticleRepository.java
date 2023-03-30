package com.example.fastcampusprojectboard.repository;

import com.example.fastcampusprojectboard.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@Repository 어노테이션을 이미 가지고 있어서 붙이지 않는다.
@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article, Long> {
}