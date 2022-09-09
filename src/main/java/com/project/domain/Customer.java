package com.project.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

@NoArgsConstructor

@Setter
@Getter

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @NotNull
    @NotBlank(message = "required data")
    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "customerOfProject") //, orphanRemoval = true
    List<Project> projects;

    public void addProject(Project project) {
        projects.add(project);
        project.setCustomerOfProject(this);
    }

    public void removeProject(Project project) {
        projects.remove(project);
        project.setCustomerOfProject(null);
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId && Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(projects, customer.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, name, email, projects);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", projects=" + projects +
                '}';
    }
}
