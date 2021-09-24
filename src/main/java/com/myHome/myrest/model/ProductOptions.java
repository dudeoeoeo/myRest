package com.myHome.myrest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ProductOptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    private int stock;

    @ManyToOne(fetch = FetchType.EAGER)
    // referencedColumnName은 User 테이블의 PK 이므로 생략가능
    @JoinColumn(name = "options_id")
    private Stores stores;
}
