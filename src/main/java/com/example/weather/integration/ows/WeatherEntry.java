package com.example.weather.integration.ows;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class WeatherEntry {

	private LocalDateTime timestamp;

	private double temperature;

	private Integer weatherId;

	private String weatherIcon;

	@JsonProperty("timestamp")
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	@JsonSetter("dt")
	public void setTimestamp(long epoch) {
		this.timestamp = LocalDateTime.ofEpochSecond(epoch, 0, ZoneOffset.UTC);
	}

	public double getTemperature() {
		return this.temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	@JsonProperty("main")
	public void setMain(Map<String, Object> main) {
		double kelvinTemp = Double.parseDouble(main.get("temp").toString());
		this.temperature = kelvinTemp - 273.0;
	}

	public Integer getWeatherId() {
		return this.weatherId;
	}

	public void setWeatherId(Integer weatherId) {
		this.weatherId = weatherId;
	}

	public String getWeatherIcon() {
		return this.weatherIcon;
	}

	public void setWeatherIcon(String weatherIcon) {
		this.weatherIcon = weatherIcon;
	}

	@JsonProperty("weather")
	public void setWeather(List<Map<String, Object>> weatherEntries) {
		Map<String, Object> weather = weatherEntries.get(0);
		this.weatherId = (Integer) weather.get("id");
		this.weatherIcon = (String) weather.get("icon");
	}

}
