package com.rs.hybrid.weatherforecast.controller;

import com.rs.hybrid.weatherforecast.model.City;
import com.rs.hybrid.weatherforecast.model.Coordinate;
import com.rs.hybrid.weatherforecast.service.CityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CityController.class)
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityServiceMock;

    @Test
    public void testGetCities() throws Exception {
        City city1 = new City().builder()
                .id(1L)
                .country("RS")
                .name("Ruma")
                .coord(new Coordinate().builder()
                        .lat(45.0081f)
                        .lon(19.8222f)
                        .build())
                .sunset(new Date(1595579191))
                .sunrise(new Date(1595579191))
                .timezone(7200)
                .build();

        City city2 = new City().builder()
                .id(1L)
                .country("RS")
                .name("Novi Sad")
                .coord(new Coordinate().builder()
                        .lat(45.2517f)
                        .lon(19.8369f)
                        .build())
                .sunset(new Date(1595579191))
                .sunrise(new Date(1595579191))
                .timezone(7200)
                .build();

        when(cityServiceMock.getSortedCities()).thenReturn(Arrays.asList(city1, city2));

        mockMvc.perform(get("/city"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[*].name").value(hasItem("Ruma")))
                .andExpect(jsonPath("$.[*].country").value(hasItem("RS")))
                .andExpect(jsonPath("$.[*].timezone").value(hasItem(7200)))
                .andExpect(jsonPath("$.[*].sunrise").value(hasItem(1595579)))
                .andExpect(jsonPath("$.[*].sunset").value(hasItem(1595579)))
                .andExpect(jsonPath("$.[*].name").value(hasItem("Novi Sad")))
                .andExpect(jsonPath("$.[*].country").value(hasItem("RS")))
                .andExpect(jsonPath("$.[*].timezone").value(hasItem(7200)))
                .andExpect(jsonPath("$.[*].sunrise").value(hasItem(1595579)))
                .andExpect(jsonPath("$.[*].sunset").value(hasItem(1595579)));
    }
}
