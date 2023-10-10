package ru.gerasimov.home_budget.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Сущность описывает финансовую операцию.
 *
 * author Evgeniy_Gerasimov
 */
@Entity
@Getter
@Setter
@Table(name = "Operatiion", schema = "home_budget")
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
}
