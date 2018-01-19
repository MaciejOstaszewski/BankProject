/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.services;

import com.bank_system_project.models.Messages;

import java.util.List;

public interface MessagesService {

    void save(Messages messages);

    void delete(long id);

    List<Messages> getAllUserMessages(String username);

    Messages getMessage(long id);
}
