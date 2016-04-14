package com.example.weather.integration.ows;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class WeatherForecast {

	private List<WeatherForecastEntry> entries;

	@JsonProperty("entries")
	public List<WeatherForecastEntry> getEntries() {
		return entries;
	}

	@JsonSetter("list")
	public void setEntries(List<WeatherForecastEntry> entries) {
		this.entries = entries;
	}

}
