package com.rs.hybrid.weatherforecast.service;

import com.rs.hybrid.weatherforecast.model.City;
import com.rs.hybrid.weatherforecast.model.Coordinate;
import com.rs.hybrid.weatherforecast.model.MainPart;
import com.rs.hybrid.weatherforecast.model.WeatherForecastList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WeatherForecastServiceTest {

    @InjectMocks
    public WeatherForecastServiceImpl weatherForecastServiceMock;

    @Mock
    public CityService cityServiceMock;

    @Test
    public void testSortByAverageTemperature() {
        WeatherForecastList weatherForecastList1 = new WeatherForecastList().builder()
                .main(new MainPart().builder()
                        .temp(20)
                        .build())
                .build();
        WeatherForecastList weatherForecastList2 = new WeatherForecastList().builder()
                .main(new MainPart().builder()
                        .temp(21)
                        .build())
                .build();
        WeatherForecastList weatherForecastList3 = new WeatherForecastList().builder()
                .main(new MainPart().builder()
                        .temp(22)
                        .build())
                .build();

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
                .weatherForecastList(Arrays.asList(weatherForecastList1,weatherForecastList2,weatherForecastList3))
                .build();

        City city2 = new City().builder()
                .id(2L)
                .country("RS")
                .name("Novi Sad")
                .coord(new Coordinate().builder()
                        .lat(45.2517f)
                        .lon(19.8369f)
                        .build())
                .sunset(new Date(1595579191))
                .sunrise(new Date(1595579191))
                .timezone(7200)
                .weatherForecastList(Arrays.asList(weatherForecastList1,weatherForecastList2))
                .build();

        when(cityServiceMock.getAll()).thenReturn(Arrays.asList(city2, city1));
        List<City> cities = weatherForecastServiceMock.sortByAverageTemperature();
        assertEquals(cities.indexOf(city1), 0);
    }

    @Test
    public void testAverageTemp_ListIsEmpty() {
        when(cityServiceMock.getAll()).thenReturn(Arrays.asList());
        List<City> cities = weatherForecastServiceMock.sortByAverageTemperature();
        assertEquals(cities, null);
    }

    @Test
    public void testAverageTemp() {
        WeatherForecastList weatherForecastList1 = new WeatherForecastList().builder()
                .dt_txt(new Date(1595585841906L))
                .main(new MainPart().builder()
                        .temp(20)
                        .build())
                .build();
        WeatherForecastList weatherForecastList2 = new WeatherForecastList().builder()
                .dt_txt(new Date(1595585841907L))
                .main(new MainPart().builder()
                        .temp(21)
                        .build())
                .build();
        WeatherForecastList weatherForecastList3 = new WeatherForecastList().builder()
                .dt_txt(new Date(1595585841908L))
                .main(new MainPart().builder()
                        .temp(22)
                        .build())
                .build();

        City city1 = new City().builder()
                .id(1L)
                .weatherForecastList(Arrays.asList(weatherForecastList1,weatherForecastList2,weatherForecastList3))
                .build();

        City city2 = new City().builder()
                .id(2L)
                .weatherForecastList(Arrays.asList(weatherForecastList1,weatherForecastList2))
                .build();

        when(cityServiceMock.getAll()).thenReturn(Arrays.asList(city2, city1));
        List<City> cities = weatherForecastServiceMock.averageTemp(new Date(1595585841905L), new Date(1595585841909L));

        assertEquals(cities.get(0).getAvg_temp(), 21);
        assertEquals(cities.get(1).getAvg_temp(), 20.5);
    }

    @Test
    public void testCityAverageTemp_idDoesNotExist() {
        when(cityServiceMock.findById(1L)).thenReturn(Optional.empty());
        City city = weatherForecastServiceMock.cityAverageTemp(new Date(1595585841905L), new Date(1595585841909L), 1L);
        assertNull(city);

    }

    @Test
    public void testCityAverageTemp() {
        WeatherForecastList weatherForecastList1 = new WeatherForecastList().builder()
                .dt_txt(new Date(1595585841906L))
                .main(new MainPart().builder()
                        .temp(20)
                        .build())
                .build();
        WeatherForecastList weatherForecastList2 = new WeatherForecastList().builder()
                .dt_txt(new Date(1595585841907L))
                .main(new MainPart().builder()
                        .temp(21)
                        .build())
                .build();

        City city = new City().builder()
                .id(1L)
                .weatherForecastList(Arrays.asList(weatherForecastList1,weatherForecastList2))
                .build();

        when(cityServiceMock.findById(1L)).thenReturn(Optional.of(city));
        City city1 = weatherForecastServiceMock.cityAverageTemp(new Date(1595585841905L), new Date(1595585841909L), 1L);
        assertEquals(city1.getAvg_temp(), 20.5);
    }
}
