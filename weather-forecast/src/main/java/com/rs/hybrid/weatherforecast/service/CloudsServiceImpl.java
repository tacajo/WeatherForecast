package com.rs.hybrid.weatherforecast.service;

import com.rs.hybrid.weatherforecast.model.Clouds;
import com.rs.hybrid.weatherforecast.repository.CloudsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CloudsServiceImpl implements CloudsService {

    @Autowired
    private CloudsRepository cloudsRepository;

    public Clouds save(Clouds clouds) {
        return cloudsRepository.save(clouds);
    }
}
