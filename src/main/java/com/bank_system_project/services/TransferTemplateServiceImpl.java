/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.services;

import com.bank_system_project.exceptions.MessageNotFoundException;
import com.bank_system_project.exceptions.TemplateNotFoundException;
import com.bank_system_project.models.Messages;
import com.bank_system_project.models.TransferTemplate;
import com.bank_system_project.repositories.TransferTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransferTemplateServiceImpl implements TransferTemplateService {

    @Autowired
    TransferTemplateRepository transferTemplateRepository;

    @Override
    public void save(TransferTemplate transferTemplate) {
        transferTemplateRepository.save(transferTemplate);
    }

    @Override
    public List<TransferTemplate> getAllTransferTemplates(String name) {
        return transferTemplateRepository.findAllByUserUsername(name);
    }

    @Override
    public TransferTemplate getOne(long id) {
        Optional<TransferTemplate> optionalTemplate = transferTemplateRepository.findById(id);
        TransferTemplate template = optionalTemplate.orElseThrow(() -> new TemplateNotFoundException(id));
        return template;

    }


}
