package com.example.weather.web;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.weather.WeatherAppProperties;
import com.example.weather.integration.ows.Weather;
import com.example.weather.integration.ows.WeatherService;

@Controller
@RequestMapping("/")
public class WeatherSummaryController {

	private final WeatherService weatherService;

	private final WeatherAppProperties properties;

	@Autowired
	public WeatherSummaryController(WeatherService weatherService, WeatherAppProperties properties) {
		this.weatherService = weatherService;
		this.properties = properties;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView conferenceWeather() {
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		model.put("summary", getSummary());
		return new ModelAndView("summary", model);
	}

	private Object getSummary() {
		List<WeatherSummary> summary = new ArrayList<>();
		for (String location : this.properties.getLocations()) {
			String country = location.split("/")[0];
			String city = location.split("/")[1];
			Weather weather = this.weatherService.getWeather(country, city);
			summary.add(new WeatherSummary(country, city, weather));
		}
		return summary;
	}

}
