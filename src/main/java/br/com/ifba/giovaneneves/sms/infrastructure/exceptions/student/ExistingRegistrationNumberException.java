package br.com.ifba.giovaneneves.sms.infrastructure.exceptions.student;

public class ExistingRegistrationNumberException extends Exception{

    public ExistingRegistrationNumberException(String message){
        super(message);
    }

}
