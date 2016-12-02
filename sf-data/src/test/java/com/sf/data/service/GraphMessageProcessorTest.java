package com.sf.data.service;

import com.sf.data.domain.MessageLabel;
import com.sf.util.file.SFClassUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by adityasofat on 02/12/2016.
 */
public class GraphMessageProcessorTest {


    @Test
    public void shouldAddVertexToGraph() throws Exception {
        Path path = getPath("airlines-sample.dat");
        FileProcessor fileProcessor = new FileProcessor();
        GraphDatabaseService graphDatabaseService = new GraphDatabaseFactory().newEmbeddedDatabase(new File("/Users/adityasofat/dev/flightdata7"));
        GraphMessageProcessor graphMessageProcessor = new GraphMessageProcessor(graphDatabaseService);
        MessageListener airLineMessageListener = graphMessageProcessor.getMessageListener(MessageLabel.AIRLINE);
        fileProcessor.processFile(path,airLineMessageListener);
    }

    private Path getPath(String fileName) throws URISyntaxException {
        URL resource = SFClassUtils.getClassLoader().getResource(fileName);
        MatcherAssert.assertThat("Could not find file", resource, Matchers.notNullValue());
        return Paths.get(resource.toURI());
    }
}