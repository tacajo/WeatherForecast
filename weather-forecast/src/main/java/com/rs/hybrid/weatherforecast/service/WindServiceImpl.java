package com.rs.hybrid.weatherforecast.service;

import com.rs.hybrid.weatherforecast.model.Wind;
import com.rs.hybrid.weatherforecast.repository.WindRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WindServiceImpl implements WindService {

    @Autowired
    private WindRepository windRepository;

    @Override
    public Wind save(Wind wind) {
        return windRepository.save(wind);
    }
}
