package br.com.ifba.giovaneneves.sms.infrastructure.exceptions.student;

public class StudentNotFoundException extends Exception{

    public StudentNotFoundException(String message){
        super(message);
    }
}
