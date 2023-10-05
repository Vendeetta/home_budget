package ru.gerasimov.home_budget.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
    @OneToOne(fetch = FetchType.LAZY)
    private Category category;
    @Column(name = "type")
    private Type type;
    @Column(name = "amount", nullable = false)
    private Double amount;
    @Column(name = "comment")
    private String comment;
}
