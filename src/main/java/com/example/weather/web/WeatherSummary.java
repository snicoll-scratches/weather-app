package com.example.weather.web;

import com.example.weather.integration.ows.Weather;

class WeatherSummary {

	private final String country;

	private final String city;

	private final Integer code;

	private final String icon;

	private final String temperature;

	WeatherSummary(String country, String city, Weather weather) {
		this.country = country;
		this.city = city;
		this.code = weather.getWeatherId();
		this.icon = weather.getWeatherIcon();
		this.temperature = String.format("%4.2f", weather.getTemperature());
	}

	public String getCountry() {
		return this.country;
	}

	public String getCity() {
		return this.city;
	}

	public Integer getCode() {
		return this.code;
	}

	public String getIcon() {
		return this.icon;
	}

	public String getTemperature() {
		return this.temperature;
	}

}
