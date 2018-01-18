package com.bank_system_project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such transfer")
public class TransferNotFoundException extends RuntimeException {
    public TransferNotFoundException(){
        super(String.format("przelew nie istnieje"));
    }

    public TransferNotFoundException(Long id){
        super(String.format("przelew o id #d nie istnieje", id));
    }
}
