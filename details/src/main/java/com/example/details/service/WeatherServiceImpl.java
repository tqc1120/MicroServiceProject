package com.example.details.service;



import com.example.details.config.EndpointConfig;
import com.example.details.pojo.City;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class WeatherServiceImpl implements WeatherService{

    private final RestTemplate restTemplate;


    public WeatherServiceImpl(RestTemplate getRestTemplate) {
        this.restTemplate = getRestTemplate;
    }

    @Override
    @Retryable(include = IllegalAccessError.class)
    public List<Integer> findCityIdByName(String city) {
//        City[] cities = restTemplate.getForObject(EndpointConfig.queryWeatherByCity + city, City[].class);
//        List<Integer> ans = new ArrayList<>();
//        for(City c: cities) {
//            if(c != null && c.getWoeid() != null) {
//                ans.add(c.getWoeid());
//            }
//        }
//        return ans;
        ArrayList res = new ArrayList();
        res.add(1);
        res.add(2);
        res.add(3);
        return res;
    }

    @Override
    //change findcitynamebyid => find weather details by id
    public Map<String, Map> findCityNameById(int id) {
        Map<String, Map> ans = restTemplate.getForObject(EndpointConfig.queryWeatherById + id, HashMap.class);
        return ans;
    }
}
