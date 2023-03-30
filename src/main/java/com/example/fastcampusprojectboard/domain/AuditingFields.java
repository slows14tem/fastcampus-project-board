package com.example.fastcampusprojectboard.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)  //entity에서도 auditing을 쓴다는 표시
@MappedSuperclass
public class AuditingFields {
    //4가지 항목이 Article, ArticleComment 두 엔티티에 모두 사용되었기 때문에 추출하여 사용하도록 변경하여 사용
    //개인의 취향이나 조직의 룰에 따라서 사용하지 않을 수도 있다(하나의 엔티티에는 엔티티에 포함되는 모든 필드를 넣어주는 경우를 선호하는 경우도 있음)

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) //파싱에 대한 룰 정의
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;    //생성일시

    @CreatedBy
    @Column(nullable = false, updatable = false, length = 100)
    private String createdBy;   //생성자

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;   //수정일시

    @LastModifiedBy
    @Column(nullable = false, length = 100)
    private String modifiedBy;  //수정자
    //CreatedDate, CreatedBy, LastModifiedDate, LastModifiedBy = Jpa Auditing 기능
}
