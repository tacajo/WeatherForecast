package com.rs.hybrid.weatherforecast.service;

import com.rs.hybrid.weatherforecast.dto.APIResponse;
import com.rs.hybrid.weatherforecast.dto.WeatherDTO;
import com.rs.hybrid.weatherforecast.dto.WeatherForecastListDTO;
import com.rs.hybrid.weatherforecast.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class WeatherForecastServiceImpl implements WeatherForecastService {

    Logger logger = LoggerFactory.getLogger(WeatherForecastServiceImpl.class);


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private CoordinateService coordinateService;

    @Autowired
    private CityService cityService;

    @Autowired
    private WeatherForecastListService weatherForecastListService;

    @Autowired
    private MainPartService mainPartService;

    @Autowired
    private CloudsService cloudsService;

    @Autowired
    private WindService windService;

    @Autowired
    private WeatherService weatherService;

    @Value("${api-key}")
    private String apiKey;

    @Value("${api-call-forecast}")
    private String apiCall;

    @Value("${weather-forecast-city1}")
    private String city1;

    @Value("${weather-forecast-city2}")
    private String city2;

    @Value("${weather-forecast-city3}")
    private String city3;

    public void getData() {
        getCityData(city1);
        getCityData(city2);
        getCityData(city3);
    }

    private void getCityData(String cityId) {
        logger.info(String.format("Getting data for city id %s...", cityId));

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiCall)
                .queryParam("id", cityId)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric");

        HttpEntity<APIResponse> response = restTemplate.getForEntity(
                builder.toUriString(),
                APIResponse.class);

        City city = conversionService.convert(response.getBody().getCity(), City.class);
        city.setCoord(coordinateService.save(conversionService.convert(response.getBody().getCity().getCoord(), Coordinate.class)));


        List<WeatherForecastListDTO> list = response.getBody().getList();

        for (WeatherForecastListDTO weatherForecastListDTO : list) {
            MainPart mainPart = mainPartService.save(conversionService.convert(weatherForecastListDTO.getMain(), MainPart.class));
            Wind wind = windService.save(conversionService.convert(weatherForecastListDTO.getWind(), Wind.class));
            Clouds clouds = cloudsService.save(conversionService.convert(weatherForecastListDTO.getClouds(), Clouds.class));
            WeatherForecastList weatherForecastList = conversionService.convert(weatherForecastListDTO, WeatherForecastList.class);
            weatherForecastList.setMain(mainPart);
            weatherForecastList.setWind(wind);
            weatherForecastList.setClouds(clouds);
            for (WeatherDTO weatherDTO : weatherForecastListDTO.getWeather()) {
                Weather weather = weatherService.save(conversionService.convert(weatherDTO, Weather.class));
                weatherForecastList.getWeather().add(weather);
            }
            weatherForecastList = weatherForecastListService.save(weatherForecastList);
            city.getWeatherForecastList().add(weatherForecastList);
            city.getMainParts().add(mainPart);
            addAverageTemp(city);
            cityService.save(city);
        }
    }

    private void addAverageTemp(City city) {
        city.setAvg_temp(city.getMainParts().stream()
                .mapToDouble(MainPart::getTemp)
                .average()
                .getAsDouble());
    }

}
