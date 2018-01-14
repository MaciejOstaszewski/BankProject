package com.bank_system_project.services;


import com.bank_system_project.models.MobileNetwork;
import com.bank_system_project.models.TopUp;
import com.bank_system_project.repositories.MobileNetworkRepository;
import com.bank_system_project.repositories.TopUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopUpServiceImpl implements TopUpService{

    @Autowired
    TopUpRepository topUpRepository;

    @Autowired
    MobileNetworkRepository mobileNetworkRepository;

    @Override
    public void save(TopUp topUp) {
        topUpRepository.save(topUp);
    }

    @Override
    public List<MobileNetwork> getAllMobileNetwork() {
        return mobileNetworkRepository.findAll();
    }
}
