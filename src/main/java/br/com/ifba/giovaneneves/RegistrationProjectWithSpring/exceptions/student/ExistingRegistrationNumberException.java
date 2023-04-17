package br.com.ifba.giovaneneves.RegistrationProjectWithSpring.exceptions.student;

public class ExistingRegistrationNumberException extends Exception{

    public ExistingRegistrationNumberException(String message){
        super(message);
    }

}
