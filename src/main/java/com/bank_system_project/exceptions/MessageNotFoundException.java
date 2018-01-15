package com.bank_system_project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such messages")
public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(){
        super(String.format("Wiadomość nie istnieje"));
    }

    public MessageNotFoundException(Long id){
        super(String.format("Wiadomość o id #d nie istnieje", id));
    }
}
