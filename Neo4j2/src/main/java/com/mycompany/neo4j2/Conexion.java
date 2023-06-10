/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.neo4j2;
import org.neo4j.driver.*;
import static org.neo4j.driver.Values.parameters;
import org.neo4j.driver.exceptions.Neo4jException;
/**
 *
 * @author Emily Barrientos
 */
public class Conexion {
    private Driver driver;

    public Conexion(String uri, String username, String password) {
        this.driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
    }

    public void close() {
        this.driver.close();
    }
    
public void createRelationship(String startNodeLabel, String startNodePropertyKey, Object startNodePropertyValue,
                               String relationshipType, String endNodeLabel, String endNodePropertyKey,
                               Object endNodePropertyValue) {
    try (Session session = driver.session()) {
        session.writeTransaction(tx -> {
            tx.run("MERGE (start:" + startNodeLabel + " {" + startNodePropertyKey + ": $startValue}) " +
                    "MERGE (end:" + endNodeLabel + " {" + endNodePropertyKey + ": $endValue}) " +
                    "CREATE (start)-[r:" + relationshipType + "]->(end) " +
                    "RETURN start, end, r",
                    parameters("startValue", startNodePropertyValue, "endValue", endNodePropertyValue));
            return null;
        });
    }
}

    public void executeQuery(String query) {
        try (Session session = driver.session()) {
            session.writeTransaction(new TransactionWork<Void>() {
                @Override
                public Void execute(Transaction tx) {
                    tx.run(query);
                    return null;
                }
            });
        } catch (Neo4jException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
    }

    public Value executeQueryAndReturnResult(String query) {
        try (Session session = driver.session()) {
            return session.writeTransaction(new TransactionWork<Value>() {
                @Override
                public Value execute(Transaction tx) {
                    return tx.run(query).single().get(0);
                }
            });
        } catch (Neo4jException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        return null;
    }
   
}

