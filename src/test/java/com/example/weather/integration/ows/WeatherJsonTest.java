package com.example.weather.integration.ows;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@JsonTest
public class WeatherJsonTest {

	private JacksonTester<Weather> json;

	@Test
	public void loadWeather() throws Exception {
		Weather weather = this.json.read("weather-barcelona.json").getObject();
		assertThat(weather.getName()).isEqualTo("Barcelona");
		assertThat(weather.getWeatherIcon()).isEqualTo("01d");
	}

}
