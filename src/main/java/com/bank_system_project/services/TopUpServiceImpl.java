/**
 * <h1>Bank Project</h1>
 * @author  Maciej Ostaszewski
 * @version 1.0
 * @since   2017-12-01
 */
package com.bank_system_project.services;


import com.bank_system_project.exceptions.MobileNetworkNotFoundException;
import com.bank_system_project.models.MobileNetwork;
import com.bank_system_project.models.TopUp;
import com.bank_system_project.repositories.MobileNetworkRepository;
import com.bank_system_project.repositories.TopUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopUpServiceImpl implements TopUpService{

    @Autowired
    TopUpRepository topUpRepository;

    @Autowired
    MobileNetworkRepository mobileNetworkRepository;


    /**
     * Zapisuje doładowanie do bazy
     * @param topUp doładowanie do zapisania
     */
    @Override
    public void save(TopUp topUp) {
        topUpRepository.save(topUp);
    }


    /**
     * Pobiera listę sieci komórkowych
     * @return lista sieci komórkowych
     */
    @Override
    public List<MobileNetwork> getAllMobileNetwork() {
        return mobileNetworkRepository.findAll();
    }

    /**
     * Pobiera nazwe sieci komórkowej
     * @param id id wybranej sieci
     * @throws MobileNetworkNotFoundException nie znaleziono sieci o danym id
     * @return nazwa sieci
     */
    @Override
    public String getMobileNetworkName(long id) {
        Optional<MobileNetwork> optionalMobileNetwork = mobileNetworkRepository.findById(id);
        MobileNetwork mobileNetwork = optionalMobileNetwork.orElseThrow(() -> new MobileNetworkNotFoundException(id));
        return mobileNetwork.getName();
    }


}
