package org.datko.diplom_grekov.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "company_t")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_f", nullable = false)
    private String name;                                            //название

    @Column(name = "field_f", nullable = false)
    private String field;                                           //сфера деятельности

    @Column(name = "website_f", nullable = false)
    private String website;                                         //веб-сайт

    @Column(name = "email_f", nullable = false)
    private String email;                                           //эл.почта

    /*@Column(name = "password_f", nullable = false)
    private String password;*/                                        //пароль от профиля компании

    /*@Column(name = "date_of_registration_f", nullable = false)
    private Date dateOfRegistration;*/                                //дата регистрации компании НА САЙТЕ - ???

    /*@OneToMany(mappedBy = "company")
    @JsonIgnore
    private Set<Survey> survey;*/                                     //список опросов, которые создала данная компания
}