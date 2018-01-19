/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.repositories;

import com.bank_system_project.models.TransactionsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TransactionsHistoryRepository extends JpaRepository<TransactionsHistory, Long> {
    List<TransactionsHistory> findAllByUserUsername(String username);


    @Query("SELECT t from TransactionsHistory t WHERE (t.realizationDate between :dstart AND :dstop) AND (t.user.username LIKE :username)")
    List<TransactionsHistory> findAllTransactionsHistory(@Param("username")String username, @Param("dstart") Date start, @Param("dstop") Date fin);
}
