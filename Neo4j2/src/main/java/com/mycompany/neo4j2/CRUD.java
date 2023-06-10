/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.neo4j2;
import org.neo4j.driver.*;
import static org.neo4j.driver.Values.parameters;
/**
 *
 * @author Emily Barrientos
 */
public class CRUD {
        private final Driver driver;

    public CRUD(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    public void close() {
        driver.close();
    }

    public void createNode(String label, String propertyKey, Object propertyValue) {
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> {
                tx.run("CREATE (n:" + label + ") SET n." + propertyKey + " = $value",
                        parameters("value", propertyValue));
                return null;
            });
        }
    }

    public void updateNode(String label, String propertyKey, Object propertyValue, String updatedKey, Object updatedValue) {
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> {
                tx.run("MATCH (n:" + label + ") WHERE n." + propertyKey + " = $value SET n." + updatedKey + " = $updatedValue",
                        parameters("value", propertyValue, "updatedValue", updatedValue));
                return null;
            });
        }
    }

    public void deleteNode(String label, String propertyKey, Object propertyValue) {
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> {
                tx.run("MATCH (n:" + label + ") WHERE n." + propertyKey + " = $value DELETE n",
                        parameters("value", propertyValue));
                return null;
            });
        }
    }

    public void readNodes(String label) {
        try (Session session = driver.session()) {
            session.readTransaction(tx -> {
                Result result = tx.run("MATCH (n:" + label + ") RETURN n");
//                while (result.hasNext()) {
//                    Record record = result.next();
//                    System.out.println(record.get("n").asNode().asMap());
//                }
                return null;
            });
        }
    }
}
