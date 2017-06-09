package edu.mum.cs545.ws;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import cs545.airline.model.Airline;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.FlightService;

@Named
@Path("hello")
public class HelloRest {

	@Inject
	private AirlineService airlineService;
	
	@Inject
	private FlightService flightService;

	@GET
	public String helloWorld(@DefaultValue("Bindy") @QueryParam("name") String name) {
		return "Hello " + name + "!";
	}

	
	@Path("airline")
	@GET
	public String getAirlineTest() {

		String result = "{";
		
		List<Flight> flights = flightService.findAll();

		if (!flights.isEmpty()) {
			for (Flight f : flights) {
				result +=f.getId()+" "
						+f.getAirline().getId();
			}
		}
		return result;

	}

}
