package br.com.ifba.giovaneneves.registrationprojectwithspringframework.exceptions.student;

public class ExistingRegistrationNumberException extends Exception{

    public ExistingRegistrationNumberException(String message){
        super(message);
    }

}
