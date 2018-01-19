/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.services;

import com.bank_system_project.models.TopUp;
import com.bank_system_project.models.TransactionsHistory;
import com.bank_system_project.models.Transfer;

import java.util.Date;
import java.util.List;

public interface TransactionsHistoryService {
    void save(Transfer transfer);

    void save(TopUp topUp);

    List<TransactionsHistory> getAll(String name);

    List<TransactionsHistory> getAllByTimeInterval(String name, Date date1, Date date2);
}
