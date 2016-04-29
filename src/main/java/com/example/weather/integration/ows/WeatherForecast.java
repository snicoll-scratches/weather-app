package com.example.weather.integration.ows;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class WeatherForecast {

	private List<WeatherEntry> entries;

	@JsonProperty("entries")
	public List<WeatherEntry> getEntries() {
		return this.entries;
	}

	@JsonSetter("list")
	public void setEntries(List<WeatherEntry> entries) {
		this.entries = entries;
	}

}
