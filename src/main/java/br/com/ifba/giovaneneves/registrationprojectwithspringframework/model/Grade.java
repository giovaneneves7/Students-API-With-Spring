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

    private float grade1;
    private float grade2;

    private float grade3;
}
