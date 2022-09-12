package com.project.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */

@org.hibernate.annotations.NamedQuery(
        name = "Customer_FindByName",
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
