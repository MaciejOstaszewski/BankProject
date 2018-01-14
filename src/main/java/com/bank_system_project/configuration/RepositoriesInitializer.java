package com.bank_system_project.configuration;



import com.bank_system_project.models.MobileNetwork;
import com.bank_system_project.models.Role;
import com.bank_system_project.models.User;

import com.bank_system_project.repositories.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
public class RepositoriesInitializer {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MobileNetworkRepository mobileNetworkRepository;

    @Bean
    InitializingBean init() {

        return () -> {


            if (roleRepository.findAll().isEmpty()) {
                try {
                    Role roleUser = roleRepository.save(new Role(Role.Types.ROLE_USER));
                    Role roleAdmin = roleRepository.save(new Role(Role.Types.ROLE_ADMIN));



                    User admin = new User("admin", true);
                    admin.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
                    admin.setPassword(passwordEncoder.encode("admin"));



                    userRepository.save(admin);

                }catch(Exception e){
                    e.printStackTrace();
                }
            }

            if (mobileNetworkRepository.findAll().isEmpty()) {
                try {

                    mobileNetworkRepository.save(new MobileNetwork("Play"));
                    mobileNetworkRepository.save(new MobileNetwork("Orange"));
                    mobileNetworkRepository.save(new MobileNetwork("T-Mobile"));
                    mobileNetworkRepository.save(new MobileNetwork("Plus"));
                    mobileNetworkRepository.save(new MobileNetwork("New-Mobile"));

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
    }

}