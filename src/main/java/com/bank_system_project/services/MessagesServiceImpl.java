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


    @Override
    public void save(Messages messages) {
        messagesRepository.save(messages);
    }

    @Override
    public List<Messages> getAllUserMessages(String username) {
        return messagesRepository.findAllByUserUsername(username);
    }

    @Override
    public Messages getMessage(long id) {
        Optional<Messages> optionalMessages = messagesRepository.findById(id);
        com.bank_system_project.models.Messages messages = optionalMessages.orElseThrow(() -> new MessageNotFoundException(id));
        return messages;
    }
}
