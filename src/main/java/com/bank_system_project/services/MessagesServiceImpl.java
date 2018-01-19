/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.services;


import com.bank_system_project.exceptions.MessageNotFoundException;
import com.bank_system_project.models.Messages;
import com.bank_system_project.repositories.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessagesServiceImpl implements MessagesService {

    @Autowired
    MessagesRepository messagesRepository;

    /**
     * Zapisuje wiadomość do bazy
     * @param messages wiadomość do zapisania
     */
    @Override
    public void save(Messages messages) {
        messagesRepository.save(messages);
    }

    /**
     * Usuwa wiadomość z bazy
     * @param id wiadomości do usunięcia
     */
    @Override
    public void delete(long id) {
        messagesRepository.deleteById(id);
    }

    /**
     * Pobiera liste wiadomości
     * @param username nazwa aktualnie zalogowanego użtkownika
     * @return lista wiadomości
     */
    @Override
    public List<Messages> getAllUserMessages(String username) {
        return messagesRepository.findAllByUserUsername(username);
    }

    /**
     * Pobiera wiadomość o zadanym id
     * @param id id wiadomości
     * @throws MessageNotFoundException nie znaleziono wiadomości o danym id
     * @return wiadomość
     */
    @Override
    public Messages getMessage(long id) {
        Optional<Messages> optionalMessages = messagesRepository.findById(id);
        com.bank_system_project.models.Messages messages = optionalMessages.orElseThrow(() -> new MessageNotFoundException(id));
        return messages;
    }
}
