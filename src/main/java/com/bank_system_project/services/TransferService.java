/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.services;

import com.bank_system_project.models.Transfer;

import java.util.List;

public interface TransferService {

    void save(Transfer transfer);

    void delete(long id);

    List<Transfer> getCurrentLoggedUserTransfers(String username, boolean repeat);

    Transfer getOne(long id);
}
