package com.myHome.myrest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class AD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=2, max=30, message = "제목은 2자이상 30자 이하입니다.")
    private String ad_title;

    private String ad_content;

    @ManyToOne(fetch = FetchType.EAGER)
    // referencedColumnName은 User 테이블의 PK 이므로 생략가능
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;
}
