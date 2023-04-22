package br.com.ifba.giovaneneves.registrationprojectwithspringframework.model;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name="grades")
@Data
public class Grade extends AbstractEntity{

    private float grade1;
    private float grade2;

    private float grade3;
}
