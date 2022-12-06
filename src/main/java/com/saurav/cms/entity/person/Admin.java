package com.saurav.cms.entity.person;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "person")
    private MyUser myUser;
}
