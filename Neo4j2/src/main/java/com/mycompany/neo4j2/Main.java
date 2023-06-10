/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.neo4j2;
import java.util.Scanner;
import org.neo4j.driver.*;
//import com.mycompany.neo4j.CRUD;

/**
 *
 * @author Emily Barrientos
 */
public class Main {
    
    private static final Scanner scanner = new Scanner(System.in);
    private static final String URI = "bolt://localhost:7687";
    private static final String USER = "neo4j";
    private static final String PASSWORD = "barrientos";

    private static CRUD crudExample;
    private static Conexion relationshipManager;
    
    public static void main(String[] args) {
            
        crudExample = new CRUD(URI, USER, PASSWORD);
        relationshipManager = new Conexion(URI, USER, PASSWORD);

        boolean exit = false;

        while (!exit) {
            System.out.println("=== Neo4j Menu ===");
            System.out.println("1. Crear nodo");
            System.out.println("2. Actualizar nodo");
            System.out.println("3. Eliminar nodo");
            System.out.println("4. Leer nodos");
            System.out.println("5. Crear relación");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opción: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createNode();
                    break;
                case 2:
                    updateNode();
                    break;
                case 3:
                    deleteNode();
                    break;
                case 4:
                    readNodes();
                    break;
                case 5:
                    createRelationship();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, selecciona una opción válida.");
                    break;
            }

            System.out.println();
        }

        crudExample.close();
        relationshipManager.close();
        scanner.close();
    }

    private static void createNode() {
        System.out.print("Ingrese la etiqueta del nodo: ");
        String label = scanner.nextLine();
        System.out.print("Ingrese la clave de la propiedad: ");
        String propertyKey = scanner.nextLine();
        System.out.print("Ingrese el valor de la propiedad: ");
        String propertyValue = scanner.nextLine();

        crudExample.createNode(label, propertyKey, propertyValue);
        System.out.println("Nodo creado exitosamente.");
    }

    private static void updateNode() {
        System.out.print("Ingrese la etiqueta del nodo: ");
        String label = scanner.nextLine();
        System.out.print("Ingrese la clave de la propiedad: ");
        String propertyKey = scanner.nextLine();
        System.out.print("Ingrese el valor de la propiedad: ");
        String propertyValue = scanner.nextLine();
        System.out.print("Ingrese la clave de la propiedad actualizada: ");
        String updatedKey = scanner.nextLine();
        System.out.print("Ingrese el valor de la propiedad actualizada: ");
        String updatedValue = scanner.nextLine();

        crudExample.updateNode(label, propertyKey, propertyValue, updatedKey, updatedValue);
        System.out.println("Nodo actualizado exitosamente.");
    }

    private static void deleteNode() {
        System.out.print("Ingrese la etiqueta del nodo: ");
        String label = scanner.nextLine();
        System.out.print("Ingrese la clave de la propiedad: ");
        String propertyKey = scanner.nextLine();
        System.out.print("Ingrese el valor de la propiedad: ");
        String propertyValue = scanner.nextLine();

        crudExample.deleteNode(label, propertyKey, propertyValue);
        System.out.println("Nodo eliminado exitosamente.");
    }

    private static void readNodes() {
        System.out.print("Ingrese la etiqueta del nodo: ");
        String label = scanner.nextLine();

        crudExample.readNodes(label);
    }

    private static void createRelationship() {
        System.out.print("Ingrese la etiqueta del nodo de inicio: ");
        String startNodeLabel = scanner.nextLine();
        System.out.print("Ingrese la clave de la propiedad del nodo de inicio: ");
        String startNodePropertyKey = scanner.nextLine();
        System.out.print("Ingrese el valor de la propiedad del nodo de inicio: ");
        String startNodePropertyValue = scanner.nextLine();
        System.out.print("Ingrese el tipo de relación: ");
        String relationshipType = scanner.nextLine();
        System.out.print("Ingrese la etiqueta del nodo final: ");
        String endNodeLabel = scanner.nextLine();
        System.out.print("Ingrese la clave de la propiedad del nodo final: ");
        String endNodePropertyKey = scanner.nextLine();
        System.out.print("Ingrese el valor de la propiedad del nodo final: ");
        String endNodePropertyValue = scanner.nextLine();

        relationshipManager.createRelationship(startNodeLabel, startNodePropertyKey, startNodePropertyValue,
                relationshipType, endNodeLabel, endNodePropertyKey, endNodePropertyValue);
        System.out.println("Relación creada exitosamente.");
    }
    
}
    
    

//        // Crear una relación entre nodos
//        example.createRelationship("Tom Hanks", "Forrest Gump");
//
//        example.close();
//        
        

        
//        String username = "neo4j";
//        String password = "barrientos";
        
//        CRUD neo4jCRUD = new CRUD("bolt://localhost:7687", "neo4j", "barrientos");
//
//        // Crear un nodo
//        neo4jCRUD.createNode("Person", "name", "Edgar");
//
//        // Actualizar un nodo
//        neo4jCRUD.updateNode("Person", "name", "Xioma", "age", 30);
////
//        // Eliminar un nodo
//        neo4jCRUD.deleteNode("Person", "name", "Edgar");
//
//        // Leer nodos
//        neo4jCRUD.readNodes("Person");
//
//        neo4jCRUD.close();
//    }


   


