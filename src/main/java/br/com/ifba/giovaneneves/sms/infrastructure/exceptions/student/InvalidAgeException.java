package br.com.ifba.giovaneneves.sms.infrastructure.exceptions.student;

public class InvalidAgeException extends Exception{

    public InvalidAgeException(String message){
        super(message);
    }
}
