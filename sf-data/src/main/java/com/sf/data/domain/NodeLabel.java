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
                javaNode.setProperty("Latitude", airport.getLatitude().floatValue());
                javaNode.setProperty("Longitude", airport.getLongitude().floatValue());
                javaNode.setProperty("Altitude", airport.getAltitude().floatValue());
                javaNode.setProperty("TimeOffset", airport.getTimeOffset().floatValue());
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
                Node airlineNode = graphDatabaseService.findNode(AIRLINE, "Id", route.getAirlineId());
                Node sourceAirportNode = graphDatabaseService.findNode(AIRPORT, "Id", route.getSourceAirportId());
                Node destinationAirportNode = graphDatabaseService.findNode(AIRPORT, "Id", route.getDestinationAirportId());
                if ( airlineNode != null && sourceAirportNode != null && destinationAirportNode != null) {
                    Node routeNode = graphDatabaseService.createNode(ROUTE);
                    routeNode.setProperty("CodeShare", route.getCodeShare());
                    routeNode.setProperty("NumberOfStops", route.getNumberOfStops());
                    if (route.getPlainTypes() != null && !route.getPlainTypes().isEmpty() ) {
                        route.getPlainTypes().forEach(plainType -> routeNode.setProperty("PlainType", plainType));
                    }
                    Relationship fromRouteRelationship = routeNode.createRelationshipTo(sourceAirportNode, RelationshipLabel.FROM);
                    Relationship toRouteRelationship = routeNode.createRelationshipTo(destinationAirportNode, RelationshipLabel.TO);
                    Relationship airlineRouteRelationship = routeNode.createRelationshipTo(airlineNode, RelationshipLabel.OF);
                    tx.success();
                }
            }
        }
    };

    public abstract void persistToGraph(GraphDatabaseService graphDatabaseService, String message);

}
