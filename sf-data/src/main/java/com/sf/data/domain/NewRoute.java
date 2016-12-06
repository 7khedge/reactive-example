package com.sf.data.domain;

import java.util.Arrays;
import java.util.List;

/**
 * Created by adityasofat on 01/12/2016.
 */
public class NewRoute {
    private final Airline airline;
    private final Airport sourceAirport;
    private final Airport destinationAirport;
    private final String codeShare;
    private final String numberOfStops;
    private final List<String> plainTypes;

    public NewRoute(Airline airline, Airport sourceAirport, Airport destinationAirport, String codeShare, String numberOfStops, List<String> plainTypes) {
        this.airline = airline;
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
        this.codeShare = codeShare;
        this.numberOfStops = numberOfStops;
        this.plainTypes = plainTypes;
    }

    public Airline getAirline() {
        return airline;
    }

    public Airport getSourceAirport() {
        return sourceAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public String getCodeShare() {
        return codeShare;
    }

    public String getNumberOfStops() {
        return numberOfStops;
    }

    public List<String> getPlainTypes() {
        return plainTypes;
    }

    public boolean isValid(){
        return this.airline != null && this.sourceAirport != null && this.destinationAirport != null;
    }

    public String getName(){
        if ( !isValid() )
            return "";
        return airline.getName() + ":" + sourceAirport.getName() + "->" + destinationAirport.getName();
    }

    public static final class NewRouteBuilder {
        private Airline airline;
        private Airport sourceAirport;
        private Airport destinationAirport;
        private String codeShare;
        private String numberOfStops;
        private List<String> plainTypes;

        private NewRouteBuilder() {
        }

        public static NewRouteBuilder aNewRoute() {
            return new NewRouteBuilder();
        }

        public NewRouteBuilder withAirline(Airline airline) {
            this.airline = airline;
            return this;
        }

        public NewRouteBuilder withSourceAirport(Airport sourceAirport) {
            this.sourceAirport = sourceAirport;
            return this;
        }

        public NewRouteBuilder withDestinationAirport(Airport destinationAirport) {
            this.destinationAirport = destinationAirport;
            return this;
        }

        public NewRouteBuilder withCodeShare(String codeShare) {
            this.codeShare = codeShare;
            return this;
        }

        public NewRouteBuilder withNumberOfStops(String numberOfStops) {
            this.numberOfStops = numberOfStops;
            return this;
        }

        public NewRouteBuilder withPlainTypes(String[] plainTypes) {
            this.plainTypes = Arrays.asList(plainTypes);
            return this;
        }

        public NewRoute build() {
            NewRoute newRoute = new NewRoute(airline, sourceAirport, destinationAirport, codeShare, numberOfStops, plainTypes);
            return newRoute;
        }
    }
}
