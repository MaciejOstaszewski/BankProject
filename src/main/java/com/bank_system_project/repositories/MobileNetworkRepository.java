/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.repositories;

import com.bank_system_project.models.MobileNetwork;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobileNetworkRepository extends JpaRepository<MobileNetwork, Long> {
}
