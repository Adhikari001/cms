package com.saurav.cms.entity.person;

import javax.persistence.*;


import com.saurav.cms.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "person")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private String salt;

    @OneToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "role", referencedColumnName = "id")
    private Role role;
}
