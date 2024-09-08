package edu;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

@SpringBootApplication
public class BlueprintsApp implements CommandLineRunner {
    @Autowired
    BlueprintsServices blueprintsServices;

    Scanner scanner = new Scanner(System.in);

    public static void main(String arg[]) {
        SpringApplication.run(BlueprintsApp.class, arg);
    }

    @Override
    public void run(String... args){
        boolean running = true;

        int[][] puntos = {{1,2}, {5,6}, {7,8}};
        Blueprint bp = new Blueprint("leo", "xd", puntos);
        System.out.println(bp);

        while(running){
            System.out.println("<========== GESTOR DE PLANOS ==========>");
            System.out.println("");
            System.out.println("Digite una opción:\n1.Buscar plano por autor y nombre\n2.Consultar planos de un autor\n3.Registrar un nuevo plano");
            System.out.println("");
            String selection0 = scanner.nextLine();

            if(selection0.equals("1")){
                System.out.println("Digite el nombre del autor");
                String selection1 = scanner.nextLine();
                System.out.println("Digite el nombre del plano");
                String selection2 = scanner.nextLine();
                getBlueprint(selection1, selection2);
            }

            else if(selection0.equals("2")){
                System.out.println("Digite el nombre del autor");
                String selection3 = scanner.nextLine();
                getBlueprintsByAuthor(selection3);
            }

            else if(selection0.equals("3")){
                System.out.println("Digite el nombre del autor");
                String selection3 = scanner.nextLine();
                System.out.println("Digite el nombre del plano");
                String selection4 = scanner.nextLine();
                System.out.println("Digite el numero de puntos que tendrá su plano");
                String selection5= scanner.nextLine();
                int numberOfPoints= Integer.parseInt(selection5);
                addBlueprint(selection3, selection4, getPointsFromUser(numberOfPoints));
            }
        }
    }
    
    private int[][] getPointsFromUser(int numberOfPoints) {
        int[][] points = new int[numberOfPoints][2];

        for (int i = 0; i < numberOfPoints; i++) {
            System.out.println("Digite las coordenadas del punto " + (i + 1) + " en el formato 'x y':");
            String[] coordinates = scanner.nextLine().split(" ");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            points[i] = new int[]{x,y};
        }

        return points;
    }

    private void getBlueprint(String author,String name){
        try {
            System.out.println(blueprintsServices.getBlueprint(author, name));
        } catch (BlueprintNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private void getBlueprintsByAuthor(String author){
        try {
            System.out.println(blueprintsServices.getBlueprintsByAuthor(author).toString());
        } catch (BlueprintNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addBlueprint(String author, String name, int[][]points){
        try {
            blueprintsServices.addNewBlueprint(author,name,points);
        } catch (BlueprintPersistenceException e) {
            e.printStackTrace();
        }
    }
}










