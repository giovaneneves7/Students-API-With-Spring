package br.com.ifba.giovaneneves.sms.student.service;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DataConversor {

    public static Date convertToDate(String stringToBeConverted){

        Date convertedDate;

        try{

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            convertedDate = dateFormat.parse(stringToBeConverted);
            return convertedDate;

        } catch(ParseException ex){

            ex.printStackTrace();
            return null;
        }
        
    }
}

