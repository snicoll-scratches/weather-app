package com.example.weather.integration.ows;

import java.net.URI;

import com.example.weather.WeatherAppProperties;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

@Service
public class WeatherService {

	private static final String FORECAST_URL = "http://api.openweathermap.org/data/2.5/forecast?q={city},{country}&APPID={key}";

	private final RestTemplate restTemplate;

	private final String apiKey;

	public WeatherService(RestTemplate restTemplate, WeatherAppProperties properties) {
		this.restTemplate = restTemplate;
		this.apiKey = properties.getApi().getKey();
	}

	public WeatherForecast getWeatherForecast(String country, String city) {
		URI url = new UriTemplate(FORECAST_URL).expand(city, country, this.apiKey);
		RequestEntity<?> request = RequestEntity.get(url)
				.accept(MediaType.APPLICATION_JSON).build();

		ResponseEntity<WeatherForecast> exchange = this.restTemplate
				.exchange(request, WeatherForecast.class);

		return exchange.getBody();
	}

}
