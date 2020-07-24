package com.rs.hybrid.weatherforecast.controller;

import com.rs.hybrid.weatherforecast.model.City;
import com.rs.hybrid.weatherforecast.model.Coordinate;
import com.rs.hybrid.weatherforecast.service.WeatherForecastService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = WeatherForecastController.class)
public class WeatherForecastControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherForecastService weatherForecastServiceMock;

    @Test
    public void testSortByAverageTemp_EmptyList() throws Exception {
        when(weatherForecastServiceMock.sortByAverageTemperature()).thenReturn(null);
        mockMvc.perform(get("/sort-by-avg-temp"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAverageTemp_EmptyList() throws Exception {
        Date startDate = new Date(1595560610000L);
        Date endDate = new Date(1595580610000L);
        when(weatherForecastServiceMock.validDates(startDate, endDate)).thenReturn(true);
        when(weatherForecastServiceMock.averageTemp(startDate, endDate)).thenReturn(null);
        mockMvc.perform(get("/{startDate}/{endDate}", "2020-07-24T05:16:50", "2020-07-24T10:50:10"))
                .andExpect(status().isNoContent());

    }

    @Test
    public void testGetAverageTemp_dateIsInvalid() throws Exception {
        when(weatherForecastServiceMock.validDates(new Date(1595560610000L), new Date(1595580610000L))).thenReturn(false);
        mockMvc.perform(get("/{startDate}/{endDate}", "2020-07-24T03:16:50", "2020-07-24T10:50:10"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetCityAverageTemp_idDoesNotExist() throws Exception {
        Date startDate = new Date(1595560610000L);
        Date endDate = new Date(1595580610000L);
        when(weatherForecastServiceMock.validDates(startDate, endDate)).thenReturn(true);
        when(weatherForecastServiceMock.cityAverageTemp(startDate, endDate, 1L)).thenReturn(null);
        mockMvc.perform(get("/1L/{startDate}/{endDate}", "2020-07-24T05:16:50", "2020-07-24T10:50:10"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetCityAverageTemp_isOk() throws Exception {
        Date startDate = new Date(1595560610000L);
        Date endDate = new Date(1595580610000L);
        City city = new City().builder()
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
                .avg_temp(25.5)
                .build();
        when(weatherForecastServiceMock.validDates(startDate, endDate)).thenReturn(true);

        when(weatherForecastServiceMock.cityAverageTemp(startDate, endDate, 1L)).thenReturn(city);

        mockMvc.perform(get("/{id}/{startDate}/{endDate}", 1L, "2020-07-24T05:16:50", "2020-07-24T10:50:10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ruma"))
                .andExpect(jsonPath("$.country").value("RS"))
                .andExpect(jsonPath("$.timezone").value(7200))
                .andExpect(jsonPath("$.sunrise").value(1595579))
                .andExpect(jsonPath("$.sunset").value(1595579))
                .andExpect(jsonPath("$.avg_temp").value(25.5));
    }
}
