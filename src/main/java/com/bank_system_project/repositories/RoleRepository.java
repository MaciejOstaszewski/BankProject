package com.bank_system_project.repositories;


import com.bank_system_project.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByType(Role.Types type);
}
