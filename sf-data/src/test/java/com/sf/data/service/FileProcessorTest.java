package com.sf.data.service;

import com.sf.data.domain.Airline;
import com.sf.data.domain.Airport;
import com.sf.data.domain.Route;
import com.sf.util.file.SFClassUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.sf.data.service.MessageStringCleaner.cleanString;

/**
 * Created by adityasofat on 01/12/2016.
 */

public class FileProcessorTest {

    private FileProcessor fileProcessor = new FileProcessor();

    @Test
    public void shouldReadAnAirportMessage() throws Exception {
        //Given
        URL resource = SFClassUtils.getClassLoader().getResource("airports-sample.dat");
        MatcherAssert.assertThat("Could not find file", resource, Matchers.notNullValue());
        Path path = Paths.get(resource.toURI());
        FileProcessor fileProcessor = new FileProcessor();
        List<Airport> airports = new ArrayList<>();

        //When
        fileProcessor.processFile(path, line -> {
            String[] split = line.split(",");
            Airport airport = Airport.AirportBuilder.anAirport()
                    .from(line)
                    .build();
            airports.add(airport);
        });
        Airport airport = airports.get(0);
        //Then
        MatcherAssert.assertThat(airports.size(), Matchers.equalTo(1));
        MatcherAssert.assertThat(path.toFile().exists(), Matchers.equalTo(true));
        MatcherAssert.assertThat(airport.getId(), Matchers.equalTo("1"));
        MatcherAssert.assertThat(airport.getName(), Matchers.equalTo("Goroka"));
        MatcherAssert.assertThat(airport.getCity(), Matchers.equalTo("Goroka"));
        MatcherAssert.assertThat(airport.getCountry(), Matchers.equalTo("Papua New Guinea"));
        MatcherAssert.assertThat(airport.getIATACode(), Matchers.equalTo("GKA"));
        MatcherAssert.assertThat(airport.getICAOCode(), Matchers.equalTo("AYGA"));
        MatcherAssert.assertThat(airport.getLatitude(), Matchers.equalTo(new BigDecimal("-6.081689")));
        MatcherAssert.assertThat(airport.getLongitude(), Matchers.equalTo(new BigDecimal("145.391881")));
        MatcherAssert.assertThat(airport.getAltitude(), Matchers.equalTo(5282));
        MatcherAssert.assertThat(airport.getTimeOffset(), Matchers.equalTo(10));
        MatcherAssert.assertThat(airport.getDstCode(), Matchers.equalTo("U"));
        MatcherAssert.assertThat(airport.getTimeZone(), Matchers.equalTo("Pacific/Port_Moresby"));
    }

    @Test
    public void shouldReadAirlineMessage() throws Exception {
        //Given
        URL resource = SFClassUtils.getClassLoader().getResource("airlines-sample.dat");
        MatcherAssert.assertThat("Could not find file", resource, Matchers.notNullValue());
        Path path = Paths.get(resource.toURI());

        List<Airline> airlines = new ArrayList<>();
        //When
        //When
        fileProcessor.processFile(path, line -> {
            Airline airline = Airline.AirlineBuilder.anAirline()
                    .from(line)
                    .build();
            airlines.add(airline);
        });
        //Then
        Airline airline = airlines.get(0);
        MatcherAssert.assertThat(path.toFile().exists(), Matchers.equalTo(true));
        MatcherAssert.assertThat(airlines.size(), Matchers.equalTo(1));
        MatcherAssert.assertThat(airline.getId(), Matchers.equalTo("2"));
        MatcherAssert.assertThat(airline.getName(), Matchers.equalTo("135 Airways"));
        MatcherAssert.assertThat(airline.getAlias(), Matchers.equalTo(""));
        MatcherAssert.assertThat(airline.getIATACode(), Matchers.equalTo(""));
        MatcherAssert.assertThat(airline.getICAOCode(), Matchers.equalTo("GNL"));
        MatcherAssert.assertThat(airline.getCallSign(), Matchers.equalTo("GENERAL"));
        MatcherAssert.assertThat(airline.getCountry(), Matchers.equalTo("United States"));
        MatcherAssert.assertThat(airline.getActive(), Matchers.equalTo("N"));
    }


    @Test
    public void shouldReadRouteMessage() throws Exception {
        //Given
        URL resource = SFClassUtils.getClassLoader().getResource("routes-sample.dat");
        MatcherAssert.assertThat("Could not find file", resource, Matchers.notNullValue());
        Path path = Paths.get(resource.toURI());

        List<Route> routes = new ArrayList<>();
        //When
        //When
        fileProcessor.processFile(path, line -> {
            String[] split = line.split(",");
            Route route = Route.RouteBuilder.aRoute()
            .withAirlineIATACode(split[0])
            .withAirlineId(split[1])
            .withSourceAirportIATACode(split[2])
            .withSourceAirportId(split[3])
            .withDestinationAirportIATACode(split[4])
            .withDestinationAirportId(split[5])
            .withCodeShare(split[6])
            .withNumberOfStops(split[7])
            .withPlainTypes(split[8].split(" "))
                    .build();
            routes.add(route);
        });
        //Then
        Route route = routes.get(0);
        MatcherAssert.assertThat(path.toFile().exists(), Matchers.equalTo(true));
        MatcherAssert.assertThat(routes.size(), Matchers.equalTo(1));
        MatcherAssert.assertThat(route.getAirlineIATACode(), Matchers.equalTo("3U"));
        MatcherAssert.assertThat(route.getAirlineId(), Matchers.equalTo("4608"));
        MatcherAssert.assertThat(route.getSourceAirportIATACode(), Matchers.equalTo("CAN"));
        MatcherAssert.assertThat(route.getSourceAirportId(), Matchers.equalTo("3370"));
        MatcherAssert.assertThat(route.getDestinationAirportIATACode(), Matchers.equalTo("CTU"));
        MatcherAssert.assertThat(route.getDestinationAirportId(), Matchers.equalTo("3395"));
        MatcherAssert.assertThat(route.getCodeShare(), Matchers.equalTo(""));
        MatcherAssert.assertThat(route.getNumberOfStops(), Matchers.equalTo("0"));
        MatcherAssert.assertThat(route.getPlainTypes(), Matchers.contains("321","320","330"));
    }


}