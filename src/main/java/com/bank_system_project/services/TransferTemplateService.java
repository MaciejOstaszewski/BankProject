/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.services;

import com.bank_system_project.models.TopUp;
import com.bank_system_project.models.TransferTemplate;

import java.util.List;

public interface TransferTemplateService {

    void save(TransferTemplate transferTemplate);



    List<TransferTemplate> getAllTransferTemplates(String name);

    TransferTemplate getOne(long id);
}
