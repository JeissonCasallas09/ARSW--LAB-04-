package edu;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

@SpringBootApplication
public class BlueprintsApp implements CommandLineRunner {
    @Autowired
    BlueprintsServices blueprintsServices;

    public static void main(String arg[]) {
        SpringApplication.run(BlueprintsApp.class, arg);
    }

    @Override
    public void run(String... args){
        boolean running = true;
        while(running){
            System.out.println("<========== GESTOR DE PLANOS ==========>");
            System.out.println("");
            System.out.println("Digite una opción:\n1.Buscar plano por autor y nombre\n2.Consultar planos de un autor\n3.Registrar un nuevo plano");

            Scanner scanner = new Scanner(System.in);
            String selection1 = scanner.nextLine();


            if(selection1.equals("2")){
                System.out.println("Digite el nombre del autor");
                String selecion2 = scanner.nextLine();

                try {
                    System.out.println(blueprintsServices.getBlueprintsByAuthor(selecion2).toString());
                } catch (BlueprintNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
