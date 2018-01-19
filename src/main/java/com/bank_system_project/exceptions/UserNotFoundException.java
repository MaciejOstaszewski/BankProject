/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such user")
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super(String.format("Użytkownik nie istnieje"));
    }

    public UserNotFoundException(Long id){
        super(String.format("Użytkownik o id #d nie istnieje", id));
    }
}
