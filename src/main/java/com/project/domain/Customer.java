package com.project.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

@org.hibernate.annotations.NamedQuery(name = "Customer_FindByName",
        query = "from Customer customer where customer.name = :name")
@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "customer") //, orphanRemoval = true
    List<Project> projects = new ArrayList<>();


    public void addProject(Project project) {
        projects.add(project);
        project.setCustomer(this);
    }

    public void removeProject(Project project) {
        projects.remove(project);
        project.setCustomer(null);
    }
}
