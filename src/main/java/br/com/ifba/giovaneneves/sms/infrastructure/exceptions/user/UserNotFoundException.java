package br.com.ifba.giovaneneves.sms.infrastructure.exceptions.user;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(String message){

        super(message);

    }
}
