package com.sf.data.domain;

import com.sf.data.service.MessageStringCleaner;

import java.util.Arrays;
import java.util.List;

import static com.sf.data.service.MessageStringCleaner.cleanString;

/**
 * Created by adityasofat on 01/12/2016.
 */
public class Route {
    private final String airlineIATACode;
    private final String airlineId;
    private final String sourceAirportIATACode;
    private final String sourceAirportId;
    private final String destinationAirportIATACode;
    private final String destinationAirportId;
    private final String codeShare;
    private final String numberOfStops;
    private final List<String> plainTypes;

    public Route(String airlineIATACode, String airlineId, String sourceAirportIATACode, String sourceAirportId, String destinationAirportIATACode, String destinationAirportId, String codeShare, String numberOfStops, List<String> plainTypes) {
        this.airlineIATACode = airlineIATACode;
        this.airlineId = airlineId;
        this.sourceAirportIATACode = sourceAirportIATACode;
        this.sourceAirportId = sourceAirportId;
        this.destinationAirportIATACode = destinationAirportIATACode;
        this.destinationAirportId = destinationAirportId;
        this.codeShare = codeShare;
        this.numberOfStops = numberOfStops;
        this.plainTypes = plainTypes;
    }

    public String getAirlineIATACode() {
        return airlineIATACode;
    }

    public String getAirlineId() {
        return airlineId;
    }

    public String getSourceAirportIATACode() {
        return sourceAirportIATACode;
    }

    public String getSourceAirportId() {
        return sourceAirportId;
    }

    public String getDestinationAirportIATACode() {
        return destinationAirportIATACode;
    }

    public String getDestinationAirportId() {
        return destinationAirportId;
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

    public static final class RouteBuilder {
        private String airlineIATACode;
        private String airlineId;
        private String sourceAirportIATACode;
        private String sourceAirportId;
        private String destinationAirportIATACode;
        private String destinationAirportId;
        private String codeShare;
        private String numberOfStops;
        private List<String> plainTypes;

        private RouteBuilder() {
        }

        public static RouteBuilder aRoute() {
            return new RouteBuilder();
        }

        public RouteBuilder withAirlineIATACode(String airlineIATACode) {
            this.airlineIATACode = airlineIATACode;
            return this;
        }

        public RouteBuilder withAirlineId(String airlineId) {
            this.airlineId = airlineId;
            return this;
        }

        public RouteBuilder withSourceAirportIATACode(String sourceAirportIATACode) {
            this.sourceAirportIATACode = sourceAirportIATACode;
            return this;
        }

        public RouteBuilder withSourceAirportId(String sourceAirportId) {
            this.sourceAirportId = sourceAirportId;
            return this;
        }

        public RouteBuilder withDestinationAirportIATACode(String destinationAirportIATACode) {
            this.destinationAirportIATACode = destinationAirportIATACode;
            return this;
        }

        public RouteBuilder withDestinationAirportId(String destinationAirportId) {
            this.destinationAirportId = destinationAirportId;
            return this;
        }

        public RouteBuilder withCodeShare(String codeShare) {
            this.codeShare = codeShare;
            return this;
        }

        public RouteBuilder withNumberOfStops(String numberOfStops) {
            this.numberOfStops = numberOfStops;
            return this;
        }

        public RouteBuilder withPlainTypes(String[] plainTypes) {
            this.plainTypes = Arrays.asList(plainTypes);
            return this;
        }

        public RouteBuilder from(String line) {
            String[] split = line.split(",");
            withAirlineIATACode(cleanString(split[0]));
            withAirlineId(cleanString(split[1]));
            withSourceAirportIATACode(cleanString(split[2]));
            withSourceAirportId(cleanString(split[3]));
            withDestinationAirportIATACode(cleanString(split[4]));
            withDestinationAirportId(cleanString(split[5]));
            withCodeShare(cleanString(split[6]));
            withNumberOfStops(cleanString(split[7]));
            withPlainTypes(split[8].split(" "));
            return this;
        }

        public Route build() {
            Route route = new Route(airlineIATACode, airlineId, sourceAirportIATACode, sourceAirportId, destinationAirportIATACode, destinationAirportId, codeShare, numberOfStops, plainTypes);
            return route;
        }

    }
}
