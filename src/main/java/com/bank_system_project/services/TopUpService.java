package com.bank_system_project.services;

import com.bank_system_project.models.MobileNetwork;
import com.bank_system_project.models.TopUp;

import java.util.List;
import java.util.Optional;

public interface TopUpService {

    void save(TopUp topUp);

    List<MobileNetwork> getAllMobileNetwork();

    String getMobileNetworkName(long id);
}
