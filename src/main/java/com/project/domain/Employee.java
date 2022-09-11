package com.project.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */


@Entity
@Table(name = "employee")
@NoArgsConstructor
@Setter
@Getter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = false)
    @JoinColumn(name = "position_id", unique = true, nullable = false) // insertable = false, updatable = false
    private Position position;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "employee_project",
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )
    private Set<Project> projects = new HashSet<>();
}
