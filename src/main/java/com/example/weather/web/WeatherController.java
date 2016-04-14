package com.example.weather.web;

import com.example.weather.integration.ows.WeatherForecast;
import com.example.weather.integration.ows.WeatherService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

	private final WeatherService weatherService;

	public WeatherController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	@RequestMapping("/weekly/{country}/{city}")
	public WeatherForecast getWeatherForecast(@PathVariable String country,
			@PathVariable String city) {
		return this.weatherService.getWeatherForecast(city, country);
	}
}
