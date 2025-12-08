package vn.test.thuchanh12_1_2025.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String name;

    @Column(name = "total_number")
    private Integer TotalMember;

    private String type;

    @Column(name = "created_at")
    private Instant createdDate;

}
