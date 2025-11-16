package com.souvik.autocomplete.model;

import jakarta.persistence.*;

/**
 * JPA entity representing a stored name in the H2 database.
 * Each name receives an auto-generated primary key.
 */
@Entity
@Table(name = "names", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class NameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Auto-incremented primary key

    @Column(nullable = false)
    private String name; // Original name string

    public NameEntity() {}

    public NameEntity(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
}
