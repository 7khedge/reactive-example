package com.sf.data.domain;

import org.neo4j.graphdb.*;

/**
 * Created by adityasofat on 02/12/2016.
 */
public enum MessageLabel implements Label {
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

        }
    },
    ROUTE {
        @Override
        public void persistToGraph(GraphDatabaseService graphDatabaseService, String message) {

        }
    };


    public abstract void persistToGraph(GraphDatabaseService graphDatabaseService, String message);


}
