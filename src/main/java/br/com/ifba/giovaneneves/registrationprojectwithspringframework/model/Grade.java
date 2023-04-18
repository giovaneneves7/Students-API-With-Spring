package br.com.ifba.giovaneneves.registrationprojectwithspringframework.model;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name="grades")
@Data
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = true)
    private float grade1;

    @Column(nullable = true)
    private float grade2;

    @Column(nullable = true)
    private float grade3;
}
