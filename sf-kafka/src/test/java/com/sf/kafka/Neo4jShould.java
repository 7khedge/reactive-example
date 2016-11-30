package com.sf.kafka;

import org.junit.Test;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.io.File;

/**
 * Created by adityasofat on 30/11/2016.
 */

public class Neo4jShould {

    @Test
    public void shouldAddNode() throws Exception {
        GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
        GraphDatabaseService db = dbFactory.newEmbeddedDatabase(new File("/Users/adityasofat/dev/flightdata1"));
        try (Transaction tx = db.beginTx()) {
            Node javaNode = db.createNode(NodeLabel.JAVA);
            javaNode.setProperty("TutorialID", "JAVA001");
            javaNode.setProperty("Title", "Learn Java");
            javaNode.setProperty("NoOfChapters", "25");
            javaNode.setProperty("Status", "Completed");

            Node scalaNode = db.createNode(NodeLabel.SCALA);
            scalaNode.setProperty("TutorialID", "SCALA001");
            scalaNode.setProperty("Title", "Learn Scala");
            scalaNode.setProperty("NoOfChapters", "20");
            scalaNode.setProperty("Status", "Completed");

            Relationship relationship = javaNode.createRelationshipTo
                    (scalaNode, RelationshipLabel.JVM_LANGIAGES);
            relationship.setProperty("Id", "1234");
            relationship.setProperty("OOPS", "YES");
            relationship.setProperty("FP", "YES");
            tx.success();
            ResourceIterable<Label> allLabels = db.getAllLabels();
            allLabels.stream().forEach(System.out::println);
            ResourceIterable<Node> allNodes = db.getAllNodes();
            allNodes.stream().forEach(System.out::println);
        } finally {
            db.shutdown();
        }


    }
}
