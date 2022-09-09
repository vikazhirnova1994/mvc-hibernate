package com.project.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Victoria Zhirnova
 * @project mvc-hibernate
 */


@NoArgsConstructor

@Getter
@Setter

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @NotNull
    @NotBlank(message = "required")
    @Column(name = "name_project", nullable = false, length = 100)
    private String name;

    @NotNull
    @NotBlank(message = "required date")
    @DateTimeFormat(pattern = "yy-MM-dd")
    @Column(name = "createat", nullable = false)
    private String createAt;

    @DateTimeFormat(pattern = "yy-MM-dd")
    @Column(name = "finishat")
    private String finishAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customerOfProject;

    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees = new HashSet<>();

    public Long getProjectId() {
        return projectId;
    }

    public void setCustomerOfProject(Customer customerOfProject) {
        this.customerOfProject = customerOfProject;
    }

    public Customer getCustomerOfProject() {
        return customerOfProject;
    }

    public String getName() {
        return name;
    }

    public String getCreateAt() {
        return createAt;
    }

    public String getFinishAt() {
        return finishAt;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", name='" + name + '\'' +
                ", createAt='" + createAt + '\'' +
                ", finishAt='" + finishAt + '\'' +
                ", customerOfProject=" + customerOfProject +
                ", employees=" + employees +
                '}';
    }
}
