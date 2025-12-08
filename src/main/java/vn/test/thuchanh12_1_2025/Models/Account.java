package vn.test.thuchanh12_1_2025.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="account")

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private  String Password;

    @Column(name = "first_name")
    private String FirstName;

    @Column(name = "last_name")
    private String LastName;

    private String role;

    @Column(name = "department_id")
    private Integer departmentId;
}
