package com.example.fastcampusprojectboard.repository;

import com.example.fastcampusprojectboard.domain.Article;
import com.example.fastcampusprojectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@Repository 어노테이션을 이미 가지고 있어서 붙이지 않는다.
@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>, // 입력한 엔티티의 검색기능 추가
        QuerydslBinderCustomizer<QArticle> {

    @Override
    default void customize(QuerydslBindings bindings, QArticle root){
        //사용자지정된 검색을 구현
        //alt + insert -> override method에서 찾아서 추가
        //repository 레이어에서 직접 구현체를 만들지 않음(jpa로 인터페이스만 이용해서 기능을 사용하게끔 접근하기 때문에 default가 적절
        //원래 인터페이스에서는 기능 구현 불가능 한데 자바 8이후에 가능해짐??
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
//        bindings.bind(root.title).first(StringExpression::likeIgnoreCase);  //like '${V}'
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);  //like '%${v}%'
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}