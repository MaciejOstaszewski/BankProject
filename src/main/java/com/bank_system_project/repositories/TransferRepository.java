/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.repositories;

import com.bank_system_project.models.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Long> {




    @Query("SELECT t FROM Transfer t WHERE (t.status NOT LIKE 'Wysłany') AND (t.repeat = :repeat) AND (t.user.username LIKE :username)")
    List<Transfer> findAllByUserUsername(@Param("username") String username, @Param("repeat") boolean a);

}
