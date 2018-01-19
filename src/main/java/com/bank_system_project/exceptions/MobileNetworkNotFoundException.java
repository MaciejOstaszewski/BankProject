/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such mobileNetwork")
public class MobileNetworkNotFoundException extends RuntimeException {
    public MobileNetworkNotFoundException(){
        super(String.format("Sieć nie istnieje"));
    }

    public MobileNetworkNotFoundException(Long id){
        super(String.format("Sieć o id #d nie istnieje", id));
    }
}
