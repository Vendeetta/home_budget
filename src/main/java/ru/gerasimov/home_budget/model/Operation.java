package ru.gerasimov.home_budget.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * Сущность описывает финансовую операцию.
 *
 * author Evgeniy_Gerasimov
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Operation", schema = "home_budget")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operation_id")
    private Integer operationId;
    @JoinColumn(name = "category", referencedColumnName = "category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;
    @Column(name = "amount", nullable = false)
    private Integer amount;
    @Column(name = "comment")
    private String comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operation operation = (Operation) o;

        return Objects.equals(operationId, operation.operationId);
    }

    @Override
    public int hashCode() {
        return operationId != null ? operationId.hashCode() : 0;
    }
}
