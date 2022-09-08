package com.project.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

@Entity
@Table(name = "project")
@NoArgsConstructor
@ToString(of= {"project_id", "customer_id", "name_project", "createat", "finishat"})
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customerOfProject;

    @Column(name = "name_project", nullable = false, length = 100)
    private String name;

    @Column(name = "createat", nullable = false)
    private LocalDate createAt;

    @Column(name = "finishat")
    private LocalDate finishAt;

    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees = new HashSet<>();
}
