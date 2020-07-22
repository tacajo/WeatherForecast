package com.rs.hybrid.weatherforecast.service;

import com.rs.hybrid.weatherforecast.model.MainPart;
import com.rs.hybrid.weatherforecast.repository.MainPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainPartServiceImpl implements MainPartService {

    @Autowired
    private MainPartRepository mainPartRepository;

    public MainPart save(MainPart mainPart) {
        return mainPartRepository.save(mainPart);
    }
}
