package edu.mum.cs545.ws;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirplaneService;
import cs545.airline.service.AirportService;
import cs545.airline.service.FlightService;

@Named
@Path("flight")
public class FlightWebService {
	private static final String SUCCESS_RESULT = "success";
	private static final String FAILURE_RESULT = "fail";
	@Inject
	private FlightService flightService;
	@Inject
	private AirlineService airlineService;

	@Inject
	private AirportService airportService;

	@Inject
	private AirplaneService airplaneService;

	@GET
	@Path("/findByAirline")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findByAirline(@DefaultValue("") @QueryParam("airline") String airline) throws ParseException {
	
		if(!airline.equals(null)){
			Airline a = airlineService.findByName(airline);
			System.out.println("param: find:"+airline);
			if(a.equals(null)){
				return flightService.findAll();
			}
			return flightService.findByAirline(a);
		}
		return flightService.findAll();
		
		
	}

	@GET
	@Path("/findByDestination")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findByDestination(@DefaultValue("") @QueryParam("airport") String airportId)
			throws ParseException {
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
	public List<Flight> findByArrivalBetween(@DefaultValue("") @QueryParam("datetimeForm") String datetimeForm,
			@DefaultValue("") @QueryParam("datetimeTo") String datetimeTo) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat();
		SimpleDateFormat formatter2 = new SimpleDateFormat();
		List<Flight> filtered = flightService.findByArrivalBetween(formatter.parse(datetimeForm),
				formatter2.parse(datetimeTo));
		return filtered;
	}

	@GET
	@Path("/findByDepartureBetween")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findByDepartureBetween(@DefaultValue("") @QueryParam("datetimeForm") String datetimeForm,
			@DefaultValue("") @QueryParam("datetimeTo") String datetimeTo) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat();
		SimpleDateFormat formatter2 = new SimpleDateFormat();
		List<Flight> filtered = flightService.findByDepartureBetween(formatter.parse(datetimeForm),
				formatter2.parse(datetimeTo));
		return filtered;
	}

	@GET
	@Path("/findByArrival")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findByArrival(@DefaultValue("") @QueryParam("dateTime") String dateTime) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat();
		List<Flight> filtered = flightService.findByArrival(formatter.parse(dateTime));
		return filtered;
	}

	@GET
	@Path("/findByDeparture")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findByDeparture(@DefaultValue("") @QueryParam("dateTime") String dateTime)
			throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat();
		List<Flight> filtered = flightService.findByDeparture(formatter.parse(dateTime));
		return filtered;
	}

	@POST
	@Path("/createFlight")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String post(@FormParam("flightnr") String flightnr, @FormParam("departureDate") String departureDate,
			@FormParam("departureTime") String departureTime, @FormParam("arrivalDate") String arrivalDate,
			@FormParam("arrivalTime") String arrivalTime, @FormParam("airline") String a, @FormParam("origin") String o,
			@FormParam("destination") String d, @FormParam("airplane") String airplaneNumber) throws IOException {
//
		//Airline airline = airlineService.findByName(a);
//		Airport origin = airportService.findByCode(o);
//		Airport destination = airportService.findByCode(d);
//		Airplane airplane = airplaneService.findBySrlnr(airplaneNumber);
		// Flight flight = new Flight(flightnr, departureDate, departureTime,
		// arrivalDate, arrivalTime, airline, origin,
		// destination, airplane);
		// flightService.create(flight);
		// System.out.println("Crreated:"+flight.getFlightnr());
		
		Flight f = new Flight(flightnr, departureDate, departureTime, arrivalDate, arrivalTime);
//		f.setAirline(airline);
//		f.setOrigin(origin);
//		f.setDestination(destination);
//		f.setAirplane(airplane);
//	System.out.println(f.getAirline().getName());
	//	Airline airlinea =airlineService.findAll().get(0);
//	System.out.println(airlinea.getName());
	//Airline airline2 =airlineService.findByName("SkyTeam");
		//System.out.println("skyyy: ");f.setAirline(airline2);
//		System.out.println("param"+a);
//		System.out.println("param:" + flightnr);
//		System.out.println("param:" + departureDate);
//		System.out.println("param:" + departureTime);
//
//		System.out.println("param:" + arrivalDate);
//		System.out.println("param:" + arrivalTime);
//
//		System.out.println("param:" + a);
//		System.out.println("param:" + o);
//		System.out.println("param:" + d);
		
		if(!f.equals(null)){
		return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;

	}

}
