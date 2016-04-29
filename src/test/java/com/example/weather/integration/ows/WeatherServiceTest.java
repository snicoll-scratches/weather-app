package com.example.weather.integration.ows;

import com.example.weather.WeatherAppProperties;
import org.assertj.core.data.Offset;
import org.junit.Before;
import org.junit.Test;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
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
	public void getWeather() {
		this.server.expect(requestTo("http://api.openweathermap.org/data/2.5/weather?q=barcelona,es&APPID=test-ABC"))
				.andRespond(withSuccess(new ClassPathResource("weather/weather-barcelona.json"),
						MediaType.APPLICATION_JSON));
		Weather forecast = this.weatherService.getWeather("es", "barcelona");
		assertThat(forecast.getName()).isEqualTo("Barcelona");
		assertThat(forecast.getTemperature()).isEqualTo(13.7, Offset.offset(0.1));
		assertThat(forecast.getWeatherId()).isEqualTo(800);
		assertThat(forecast.getWeatherIcon()).isEqualTo("01d");
		this.server.verify();
	}

	@Test
	public void getWeatherForecast() {
		this.server.expect(requestTo("http://api.openweathermap.org/data/2.5/forecast?q=barcelona,es&APPID=test-ABC"))
				.andRespond(withSuccess(new ClassPathResource("weather/forecast-barcelona.json"),
						MediaType.APPLICATION_JSON));
		this.weatherService.getWeatherForecast("es", "barcelona");
		this.server.verify();
	}

}
