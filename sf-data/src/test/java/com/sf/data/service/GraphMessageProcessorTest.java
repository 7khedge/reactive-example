package com.sf.data.service;

import com.sf.data.domain.NodeLabel;
import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;

import static com.sf.data.service.FileUtil.getPath;

/**
 * Created by adityasofat on 02/12/2016.
 */
@Ignore("long running test")
public class GraphMessageProcessorTest {

    private static File graphDB = new File("/Users/adityasofat/dev/flightdata7");
    private static FileProcessor fileProcessor = new FileProcessor();
    private static GraphDatabaseService graphDatabaseService = new GraphDatabaseFactory().newEmbeddedDatabase(graphDB);
    private static GraphMessageProcessor graphMessageProcessor = new GraphMessageProcessor(graphDatabaseService);

    @Test
    public void shouldAddAirlineNodes() throws Exception {
        fileProcessor.processFile(getPath("airlines.dat"), graphMessageProcessor.getMessageListener(NodeLabel.AIRLINE));
    }

    @Test
    public void shouldAddAirportNodes() throws Exception {
        fileProcessor.processFile(getPath("airports.dat"), graphMessageProcessor.getMessageListener(NodeLabel.AIRPORT));
    }

    @Test
    public void shouldAddRouteNodes() throws Exception {
        fileProcessor.processFile(getPath("routes.dat"), graphMessageProcessor.getMessageListener(NodeLabel.ROUTE));
    }

    @Test
    public void shouldRemoveGraphDB() throws Exception {
        FileUtils.deleteDirectory(graphDB);
    }

}