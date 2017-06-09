package edu.mum.cs545.ws;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import cs545.airline.model.Airline;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirportService;
import cs545.airline.service.FlightService;

@Named
@Path("flight")
public class FlightWebService {
	@Inject
	private FlightService flightService;
	@Inject
	private AirlineService airlineService;
	
	@GET
	@Path("/findByAirline")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findByAirline(@DefaultValue("") @QueryParam("airline") String airline) throws ParseException {
		Airline a = airlineService.findByName(airline);
		return flightService.findByAirline(a);
	}
	
	@GET
	@Path("/findByDestination")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findByDestination(@DefaultValue("") @QueryParam("airport") String airportId) throws ParseException {
		Airport a = new Airport();
		a.setId(Long.valueOf(airportId));
		return flightService.findByDestination(a);
	}

	@GET
	@Path("/findAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findAll() {
		return flightService.findAll();
	}
	
	
	@GET
	@Path("/findByArrivalBetween")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findByArrivalBetween(@DefaultValue("") @QueryParam("datetimeForm") String datetimeForm, @DefaultValue("")  @QueryParam("datetimeTo") String datetimeTo) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat();
		SimpleDateFormat formatter2 = new SimpleDateFormat();
		List<Flight> filtered =  flightService.findByArrivalBetween(formatter.parse(datetimeForm), formatter2.parse(datetimeTo));
		return filtered;
	}

	@GET
	@Path("/findByDepartureBetween")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findByDepartureBetween(@DefaultValue("") @QueryParam("datetimeForm") String datetimeForm, @DefaultValue("")  @QueryParam("datetimeTo") String datetimeTo) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat();
		SimpleDateFormat formatter2 = new SimpleDateFormat();
		List<Flight> filtered =  flightService.findByDepartureBetween(formatter.parse(datetimeForm), formatter2.parse(datetimeTo));
		return filtered;
	}
	
	@GET
	@Path("/findByArrival")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findByArrival(@DefaultValue("") @QueryParam("dateTime") String dateTime) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat();
		List<Flight> filtered =  flightService.findByArrival(formatter.parse(dateTime));
		return filtered;
	}
	
	@GET
	@Path("/findByDeparture")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findByDeparture(@DefaultValue("") @QueryParam("dateTime") String dateTime) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat();
		List<Flight> filtered =  flightService.findByDeparture(formatter.parse(dateTime));
		return filtered;
	}
	


}
