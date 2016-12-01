package com.sf.data.service;

import com.sf.data.domain.Airport;
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


/**
 * Created by adityasofat on 01/12/2016.
 */
public class FileProcessorTest {

    @Test
    public void shouldReadMessages() throws Exception {
        //Given
        URL resource = SFClassUtils.getClassLoader().getResource("airports-sample.dat");
        MatcherAssert.assertThat("Could not find file",resource, Matchers.notNullValue());
        Path path = Paths.get(resource.toURI());
        FileProcessor fileProcessor = new FileProcessor();
        List<Airport> airports = new ArrayList<>();

        //When
        fileProcessor.processFile(path, line -> {
            String[] split = line.split(",");
            Airport airport = Airport.AirportBuilder.anAirport()
                    .withId(split[0])
                    .withName(removeQuotes(split[1]))
                    .withCity(removeQuotes(split[2]))
                    .withCountry(removeQuotes(split[3]))
                    .withIATACode(removeQuotes(split[4]))
                    .withICAOCode(removeQuotes(split[5]))
                    .withLatitude(new BigDecimal(split[6]))
                    .withLongitude(new BigDecimal(split[7]))
                    .withAltitude(Integer.valueOf(split[8]))
                    .withTimeOffset(Integer.valueOf(split[9]))
                    .withDstCode(removeQuotes(split[10]))
                    .withTimeZone(removeQuotes(split[11]))
                    .build();
            airports.add(airport);
        });
        Airport airport = airports.get(0);
        //Then
        MatcherAssert.assertThat(airports.size(),Matchers.equalTo(1));
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

    private String removeQuotes(String string){
        return string.substring(1,string.length()-1);
    }
}