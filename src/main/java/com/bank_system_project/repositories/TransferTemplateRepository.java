/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.repositories;

import com.bank_system_project.models.Transfer;
import com.bank_system_project.models.TransferTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferTemplateRepository extends JpaRepository<TransferTemplate, Long> {
    List<TransferTemplate> findAllByUserUsername(String username);



}
