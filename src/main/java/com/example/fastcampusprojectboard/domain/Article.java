package com.example.fastcampusprojectboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class Article  extends AuditingFields{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter private Long id;

    @Setter @Column(nullable = false) private String title;   //제목
    @Setter @Column(nullable = false, length = 10000) private String content; //본문
    //@Column은 true가 기본값이고 아무 옵션이 없을때는 생략 가능
    @Setter private String hashtag; //해시태그
    //전체 클레스가 아니라 특정 필드에 setter를 주는 이유 : 사용자가 특정 필드를 세팅할 수 없도록 하려고
    //자동으로 jpa가 세팅할 수 있게 만들기 위함

    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @ToString.Exclude    //tostring을 끊어주는 방식
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();
    //mappedBy를 안하면 두 엔티티의 이름을 합쳐서 자동생성

    protected Article() {}

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(String title, String content, String hashtag) {
        // of == 도메인 Article을 생성하고자 할때 어떤 값이 필요한지 가이드....
        return new Article(title, content, hashtag);
        //factory method???
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;  //pattern matching?? 사용 가능
        return id != null && id.equals(article.id);
        //id != null의 의미 = 새로만든, 아직 영속화되지 않은 엔티티는 모두 동등성 검사를 탈락(다 다른값으로 보겠다는 의미)
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    //Collection을 사용하여 리스트로 내보내거나 정렬, 중복제거 등을 할 때 비교가 필요 == 동일성, 동등성을 검사해야함
    //equals() and hashcode()를 사용

}

