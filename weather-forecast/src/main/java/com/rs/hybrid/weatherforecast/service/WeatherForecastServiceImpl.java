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

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        logger.info("Getting data for city id {}...", cityId);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiCall)
                .queryParam("id", cityId)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric");

        HttpEntity<APIResponse> response = restTemplate.getForEntity(
                builder.toUriString(),
                APIResponse.class);
        System.out.println(builder.toUriString());
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
            cityService.save(city);
        }
    }

    public List<City> sortByAverageTemperature() {
        logger.info("Sort by average temperature...");
        List<City> cities = cityService.getAll();

        if(cities.isEmpty()) {
            logger.warn("List of cities is empty.");
            return null;
        }

        for (City city : cities) {
            city.setAvg_temp(city.getWeatherForecastList().stream()
                    .mapToDouble(WeatherForecastList::getTemp)
                    .average()
                    .getAsDouble());
        }

        return cities.stream()
                .sorted(Comparator.comparing(City::getAvg_temp).reversed())
                .collect(Collectors.toList());
    }

    public boolean validDates(Date startDate, Date endDate) {
        Date today = new Date();
        Date todayPlus5days = new Date(today.getTime() + 5 * 86400000);
        return (startDate.after(today) && endDate.before(todayPlus5days));
    }

    public List<City> averageTemp(Date startDate, Date endDate) {
        logger.info("Average temperature from {} to {}", startDate, endDate);
        List<City> cities = cityService.getAll();

        if(cities.isEmpty()) {
            logger.warn("List of cities is empty.");
            return null;
        }

        for (City city : cities) {
            city.setAvg_temp(city.getWeatherForecastList().stream()
                    .filter(w -> w.getDt_txt().after(startDate) && w.getDt_txt().before(endDate))
                    .mapToDouble(WeatherForecastList::getTemp)
                    .average()
                    .getAsDouble());
        }
        return cities.stream()
                .sorted(Comparator.comparing(City::getAvg_temp).reversed())
                .collect(Collectors.toList());
    }

    public City cityAverageTemp(Date startDate, Date endDate, Long id) {
        logger.info("Average temperature from {} to {}, city id {}", startDate, endDate, id);
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()) {
            city.get().setAvg_temp(city.get().getWeatherForecastList().stream()
                    .filter(w -> w.getDt_txt().after(startDate) && w.getDt_txt().before(endDate))
                    .mapToDouble(WeatherForecastList::getTemp)
                    .average()
                    .getAsDouble());
            return city.get();
        }
        return null;
    }
}
