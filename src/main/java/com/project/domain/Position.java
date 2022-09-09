package com.project.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@NoArgsConstructor

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

    @NotNull
    @NotBlank(message = "required")
    @Column(name = "position", nullable = false)
    private String position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position1 = (Position) o;
        return Objects.equals(positionId, position1.positionId) && Objects.equals(position, position1.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionId, position);
    }

    @Override
    public String toString() {
        return "Position{" +
                "positionId=" + positionId +
                ", position='" + position + '\'' +
                '}';
    }
}