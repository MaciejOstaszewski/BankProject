package com.bank_system_project.services;

import com.bank_system_project.models.Messages;

import java.util.List;

public interface MessagesService {

    void save(Messages messages);

    List<Messages> getAllUserMessages(String username);

    Messages getMessage(long id);
}
