package com.example.aornayapi.models;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "REVIEWS")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID")
    private Long user_id;

    @Column(name = "CAMIS")
    private String camis;

    @Column(name = "DBA")
    private String dba;

    @Column(name = "BLDG")
    private String bldg;

    @Column(name = "STREET")
    private String street;

    @Column(name = "BORO")
    private String boro;

    @Column(name = "ZIP")
    private String zip;

    @Column(name = "REVIEW")
    private String review;

    @Column(name = "RATING")
    private Integer rating;

    @Column(name = "GRADE")
    private String grade;

    @Column(name = "CATEGORY")
    private String category;

    public Review(Long user_id, String camis, String dba, String bldg, String street, String boro, String zip, String review, Integer rating, String grade, String category) {
        this.user_id = user_id;
        this.camis = camis;
        this.dba = dba;
        this.bldg = bldg;
        this.street = street;
        this.boro = boro;
        this.zip = zip;
        this.review = review;
        this.rating = rating;
        this.grade = grade;
        this.category = category;
    }

}

