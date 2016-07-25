package com.example.weather;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.weather")
public class WeatherAppProperties {

	private final Api api = new Api();

	/**
	 * Comma-separated list of locations to display. Each entry should have the
	 * form "Country/City".
	 */
	private List<String> locations = Arrays.asList("UK/London", "USA/Las Vegas");

	public Api getApi() {
		return this.api;
	}

	public List<String> getLocations() {
		return this.locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public static class Api {

		/**
		 * API key of the OpenWeatherMap service.
		 */
		@NotNull
		private String key;

		public String getKey() {
			return this.key;
		}

		public void setKey(String key) {
			this.key = key;
		}

	}

}
