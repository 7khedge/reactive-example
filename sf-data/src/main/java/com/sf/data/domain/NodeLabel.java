package com.sf.data.domain;

import org.neo4j.graphdb.*;

/**
 * Created by adityasofat on 02/12/2016.
 */
public enum NodeLabel implements Label {
    AIRLINE {
        @Override
        public void persistToGraph(GraphDatabaseService graphDatabaseService, String message) {
            try (Transaction tx = graphDatabaseService.beginTx()) {
                Node javaNode = graphDatabaseService.createNode(AIRLINE);
                Airline airline = Airline.AirlineBuilder.anAirline()
                        .from(message)
                        .build();
                javaNode.setProperty("Id",airline.getId());
                javaNode.setProperty("Name", airline.getName());
                javaNode.setProperty("Alias", airline.getAlias());
                javaNode.setProperty("IATACode", airline.getIATACode());
                javaNode.setProperty("ICAOCode", airline.getICAOCode());
                javaNode.setProperty("CallSign", airline.getCallSign());
                javaNode.setProperty("Country", airline.getCountry());
                javaNode.setProperty("Active", airline.getActive());
                tx.success();
            }
        }
    },
    AIRPORT {
        @Override
        public void persistToGraph(GraphDatabaseService graphDatabaseService, String message) {
            try (Transaction tx = graphDatabaseService.beginTx()) {
                Node javaNode = graphDatabaseService.createNode(AIRPORT);
                Airport airport = Airport.AirportBuilder.anAirport()
                        .from(message)
                        .build();
                javaNode.setProperty("Id",airport.getId());
                javaNode.setProperty("Name", airport.getName());
                javaNode.setProperty("City", airport.getCity());
                javaNode.setProperty("Country", airport.getCountry());
                javaNode.setProperty("IATACode", airport.getIATACode());
                javaNode.setProperty("Latitude", airport.getLatitude());
                javaNode.setProperty("Longitude", airport.getLongitude());
                javaNode.setProperty("Altitude", airport.getAltitude());
                javaNode.setProperty("TimeOffset", airport.getTimeOffset());
                javaNode.setProperty("DstCode", airport.getDstCode());
                javaNode.setProperty("TimeZone", airport.getTimeZone());
                tx.success();
            }
        }
    },
    ROUTE {
        @Override
        public void persistToGraph(GraphDatabaseService graphDatabaseService, String message) {
            try (Transaction tx = graphDatabaseService.beginTx()) {
                Route route = Route.RouteBuilder.aRoute()
                        .from(message)
                        .build();
                Node airline = graphDatabaseService.findNode(AIRLINE, "Id", route.getAirlineId());
                Node sourceAirport = graphDatabaseService.findNode(AIRPORT, "Id", route.getSourceAirportId());
                Node destinationAirport = graphDatabaseService.findNode(AIRPORT, "Id", route.getDestinationAirportId());
                Relationship routeRelationship = sourceAirport.createRelationshipTo(destinationAirport, RelationshipLabel.ROUTE);
                routeRelationship.setProperty("Airline",airline);
                routeRelationship.setProperty("CodeShare", route.getCodeShare());
                routeRelationship.setProperty("NumberOfStops", route.getNumberOfStops());
                route.getPlainTypes().forEach(plainType -> routeRelationship.setProperty("PlainType", plainType));
                tx.success();
            }
        }
    };


    public abstract void persistToGraph(GraphDatabaseService graphDatabaseService, String message);


}
