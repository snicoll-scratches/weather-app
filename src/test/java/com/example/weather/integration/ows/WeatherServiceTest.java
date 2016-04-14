package com.example.weather.integration.ows;

import com.example.weather.WeatherAppProperties;
import org.junit.Before;
import org.junit.Test;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class WeatherServiceTest {

	private WeatherService weatherService;

	private MockRestServiceServer server;

	@Before
	public void setup() {
		RestTemplate restTemplate = new RestTemplate();
		WeatherAppProperties properties = new WeatherAppProperties();
		properties.getApi().setKey("test-ABC");
		this.weatherService = new WeatherService(restTemplate, properties);
		this.server = MockRestServiceServer.createServer(restTemplate);
	}

	@Test
	public void getWeatherForecast() {
		this.server.expect(requestTo("http://api.openweathermap.org/data/2.5/forecast?q=barcelona,es&APPID=test-ABC"))
				.andRespond(withSuccess(new ClassPathResource("weather/2016-04-barcelona-forecast.json"),
						MediaType.APPLICATION_JSON));
		WeatherForecast forecast = weatherService.getWeatherForecast("es", "barcelona");
		this.server.verify();
	}

}
