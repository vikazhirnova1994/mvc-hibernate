package com.project.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@NoArgsConstructor
@org.hibernate.annotations.NamedQuery(name = "Position_FindByNamePosition",
        query = "from Position p where p.position = :position")
//@ToString(of= {"position_id","position"})
@Getter
@Setter

@Entity
@Table(name = "position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Long positionId;


    @Column(name = "position", nullable = false)
    private String position;
}