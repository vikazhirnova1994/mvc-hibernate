package com.project.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project")
@NoArgsConstructor
@ToString(of= {"project_id", "customer_id", "name_project", "createat", "finishat"})
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    // Соединение с помощью столбца внешнего ключа
    // Обязательно для поддержки отложенной загрузки с помощью прокси-объектов
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "position_ID", nullable = false, unique = true)
    private Position position;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "employee_project",
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )
    private Set<Project> projects = new HashSet<>();


}