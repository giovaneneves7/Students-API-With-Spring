package br.com.ifba.giovaneneves.sms.student.grade.model;

import br.com.ifba.giovaneneves.sms.infrastructure.model.AbstractEntity;
import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name="grades")
@Data
public class Grade extends AbstractEntity {

    private float grade1;
    private float grade2;

    private float grade3;
}
