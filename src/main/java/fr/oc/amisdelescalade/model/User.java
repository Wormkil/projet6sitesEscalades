package fr.oc.amisdelescalade.model;

import lombok.Data;

import javax.persistence.*;



@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(name="password_confirm")
    private String passwordConfirm;

    @Column(name="full_name")
    private String fullName;

    @Column(name="user_name", unique = true)
    private String userName;

    private String country;

    @Column(name="creation_account")
    private String creationAccount;

    private String officialMember;


}
