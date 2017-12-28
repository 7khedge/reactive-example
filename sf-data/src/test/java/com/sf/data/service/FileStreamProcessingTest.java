package com.sf.data.service;

import com.sf.data.domain.Airline;
import com.sf.data.domain.Airport;
import com.sf.data.domain.NewRoute;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;

import static com.sf.data.domain.Airline.AirlineBuilder.anAirline;
import static com.sf.data.domain.Airport.AirportBuilder.anAirport;
import static com.sf.data.domain.NewRoute.NewRouteBuilder.aNewRoute;
import static com.sf.data.service.FileUtil.getPath;
import static com.sf.data.service.MessageStringCleaner.cleanString;

/**
 * Created by adityasofat on 06/12/2016.
 */
public class FileStreamProcessingTest {

    @Test
    public void shouldProcessFlightData() throws Exception {

        Path airportPath = getPath("airports.dat");
        Map<String, Airport> airportMap = Files.lines(airportPath)
                .parallel()
                .map(line -> anAirport()
                        .from(line)
                        .build())
                .collect(Collectors.toMap(Airport::getId, airport -> airport));
        MatcherAssert.assertThat(airportMap.size(), Matchers.equalTo(8107));

        Path airlinePath = getPath("airlines.dat");
        Map<String, Airline> airlineMap = Files.lines(airlinePath)
               .parallel()
                .map(line -> anAirline()
                        .from(line)
                        .build())
                .filter(airline -> airline.getActive().equals("Y"))
                .collect(Collectors.toMap(Airline::getId, airline -> airline));
        MatcherAssert.assertThat(airlineMap.size(), Matchers.equalTo(1161));

        Path routePath = getPath("routes.dat");
        Map<String, NewRoute> routeMap = Files.lines(routePath)
                .parallel()
                .map(line -> line.split(","))
                .map(stringArray -> {
                    NewRoute.NewRouteBuilder newRouteBuilder = aNewRoute()
                            .withAirline(airlineMap.get(cleanString(stringArray[1])))
                            .withSourceAirport(airportMap.get(cleanString(stringArray[3])))
                            .withDestinationAirport(airportMap.get(cleanString(stringArray[5])))
                            .withCodeShare(cleanString(stringArray[6]))
                            .withNumberOfStops(cleanString(stringArray[7]));
                    if (stringArray.length > 8) {
                        newRouteBuilder.withPlainTypes(cleanString(stringArray[8]).split(" "));
                    }
                    return newRouteBuilder.build();
                })
                .filter(NewRoute::isValid)
                .collect(Collectors.toMap(NewRoute::getName, newRoute -> newRoute));

        MatcherAssert.assertThat(routeMap.size(), Matchers.equalTo(65674));
    }



}