package com.sf.data.service;

import com.sf.data.domain.NodeLabel;
import org.neo4j.graphdb.GraphDatabaseService;

/**
 * Created by adityasofat on 02/12/2016.
 */
public class GraphMessageProcessor {

    private final GraphDatabaseService graphDatabaseService;

    public GraphMessageProcessor(GraphDatabaseService graphDatabaseService) {
        this.graphDatabaseService = graphDatabaseService;
    }

    public MessageListener getMessageListener(NodeLabel nodeLabel) {
        return (message -> nodeLabel.persistToGraph(graphDatabaseService, message));
    }

}
